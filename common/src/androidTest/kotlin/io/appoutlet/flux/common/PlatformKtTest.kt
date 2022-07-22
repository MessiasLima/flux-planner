package io.appoutlet.flux.common

import kotlin.test.Test
import kotlin.test.assertEquals


class PlatformKtTest {

    @Test
    fun getPlatformNameTest() {
        assertEquals("Android", getPlatformName())
    }
}
