package io.appoutlet.flux.common.test

import com.appmattus.kotlinfixture.kotlinFixture
import kotlin.test.BeforeTest

abstract class BaseUnitTest<SubjectUnderTest : Any> {
    protected lateinit var sut: SubjectUnderTest
    protected val fixture = kotlinFixture()

    abstract fun buildSut(): SubjectUnderTest

    @BeforeTest
    fun setup() {
        sut = buildSut()
    }
}
