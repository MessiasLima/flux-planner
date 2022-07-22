package io.appoutlet.flux.common

import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformTest {
    @Test
    fun getPlatformNameTest() {
        assertEquals("Desktop", getPlatformName())
    }
}
