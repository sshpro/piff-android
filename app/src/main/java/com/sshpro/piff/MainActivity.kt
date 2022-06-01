package com.sshpro.piff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sshpro.piff.compose.DefaultSnackbar
import com.sshpro.piff.compose.GalleryView
import com.sshpro.piff.compose.PhotoDetailView
import com.sshpro.piff.compose.PhotoTopView
import com.sshpro.piff.ui.theme.PiffTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            R.dimen.card_bottom_margin
            PiffTheme {
                val navController = rememberNavController()
                val backstackEntry = navController.currentBackStackEntryAsState()
                val currentScreen = PhotoScreen.fromRoute(backstackEntry.value?.destination?.route)
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    topBar = {
                        PhotoTopView(currentScreen = currentScreen)
                    },
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        scaffoldState.snackbarHostState
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.background(color = MaterialTheme.colors.surface)) {
                        NavigationComponent(
                            innerPadding = innerPadding,
                            navController = navController
                        ) { error ->
                            showError(
                                snackbarHostState = scaffoldState.snackbarHostState,
                                message = error
                            )
                        }
                        DefaultSnackbar(
                            snackbarHostState = scaffoldState.snackbarHostState,
                            modifier = Modifier.align(Alignment.BottomCenter),
                            onDismiss = {
                                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                            })
                    }
                }
            }
        }
    }

    @Composable
    private fun NavigationComponent(
        innerPadding: PaddingValues,
        navController: NavHostController,
        onError: (String) -> Unit
    ) {
        NavHost(
            navController = navController,
            startDestination = PhotoScreen.Gallery.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(PhotoScreen.Gallery.name) {
                viewModel.getPhotos()
                val dataState = viewModel.getPhotosDataState.value

                GalleryView(dataState = dataState, onPhotoClick = { photoUrl ->
                    val encodedUrl = URLEncoder.encode(photoUrl, StandardCharsets.UTF_8.toString())
                    navigateToPhotoDetail(navController, encodedUrl)
                }, onError = onError)
            }
            val photoName = PhotoScreen.Detail.name
            composable(
                route = "$photoName/{encodedUrl}",
                arguments = listOf(
                    navArgument("encodedUrl") {
                        // Make argument type safe
                        type = NavType.StringType
                    }
                )
            ) { entry ->
                val encodedUrl = entry.arguments?.getString("encodedUrl") ?: ""
                val decodedUrl = URLDecoder.decode(
                    encodedUrl, StandardCharsets.UTF_8
                        .toString()
                )
                viewModel.getPhoto(decodedUrl)
                PhotoDetailView(dataState = viewModel.getPhotoDataState.value, onError = onError)
            }
        }
    }

    private fun showError(
        snackbarHostState: SnackbarHostState,
        message: String
    ) {
        lifecycleScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = getString(R.string.dismiss)
            )
        }
    }
}


private fun navigateToPhotoDetail(navController: NavHostController, photoUrl: String) {
    navController.navigate("${PhotoScreen.Detail.name}/$photoUrl")
}


