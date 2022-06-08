package com.sshpro.piff.compose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.sshpro.piff.PhotoScreen
import org.junit.Rule
import org.junit.Test

class PhotoTopViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testTopAppBarShowsPhotoGallery() {
        composeTestRule.setContent { 
            PhotoTopView(currentScreen = PhotoScreen.Gallery)
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule
            .onNodeWithText(PhotoScreen.Gallery.name)
            .assertIsDisplayed()
    }

    @Test
    fun testTopAppBarShowsPhotoDetail() {
        composeTestRule.setContent {
            PhotoTopView(currentScreen = PhotoScreen.Detail)
        }

        composeTestRule
            .onNodeWithText(PhotoScreen.Detail.name)
            .assertIsDisplayed()
    }
}