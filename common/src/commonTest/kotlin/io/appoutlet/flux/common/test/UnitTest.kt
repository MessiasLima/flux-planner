package io.appoutlet.flux.common.test

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import kotlin.test.BeforeTest

abstract class UnitTest<SubjectUnderTest : Any> {
    protected lateinit var sut: SubjectUnderTest
    protected val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    abstract fun buildSut(): SubjectUnderTest

    @BeforeTest
    fun setup() {
        sut = buildSut()
    }
}
