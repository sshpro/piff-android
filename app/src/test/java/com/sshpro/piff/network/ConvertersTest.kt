package com.sshpro.piff.network

import com.sshpro.piff.business.cache.Converters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.*

class ConvertersTest {

    private val timeInMillis = System.currentTimeMillis()
    private val date = Date(timeInMillis)

    @Test
    fun dateToTimeStamp() {
        assertEquals(Converters().dateToTimestamp(date), timeInMillis)
    }

    @Test
    fun timeStampToDate() {
        assertEquals(Converters().fromTimestamp(timeInMillis), date)
    }

    @Test
    fun nullDateToTimeStamp() {
        assertNull(Converters().dateToTimestamp(null))
    }

    @Test
    fun nullTimeStampToDate() {
        assertNull(Converters().fromTimestamp(null))
    }
}