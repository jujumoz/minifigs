package com.sierra.common.domain.model

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class MinifigTest {

    @ParameterizedTest
    @MethodSource("provideNameAndShortName")
    fun `should return correct short name`(
        name: String,
        shortName: String,
    ) {
        val input = SOME_MINIFIG.copy(name = name)

        input.shortName() shouldBeEqualTo shortName
    }

    @ParameterizedTest
    @MethodSource("provideNameAndFullName")
    fun `should return correct full name`(
        name: String,
        fullName: String,
    ) {
        val input = SOME_MINIFIG.copy(name = name)

        input.fullName() shouldBeEqualTo fullName
    }

    @ParameterizedTest
    @MethodSource("provideNameAndDetails")
    fun `should return correct details`(
        name: String,
        details: String?,
    ) {
        val input = SOME_MINIFIG.copy(name = name)

        input.details() shouldBeEqualTo details
    }

    companion object {
        @JvmStatic
        private fun provideNameAndShortName(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("name", "name"),
                Arguments.of("some name", "some name"),
                Arguments.of("name - detail1", "name"),
                Arguments.of("some name - detail1", "some name"),
                Arguments.of("name - detail1, detail2", "name"),
                Arguments.of("name&amp;detail", "name"),
            )
        }

        @JvmStatic
        private fun provideNameAndFullName(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("name", "name"),
                Arguments.of("some name", "some name"),
                Arguments.of("name - detail1", "name"),
                Arguments.of("some name - detail1", "some name"),
                Arguments.of("name - detail1, detail2", "name"),
                Arguments.of("name&amp;detail", "name&amp;detail"),
                Arguments.of("name, detail", "name, detail"),
                Arguments.of("name, detail", "name, detail"),
            )
        }

        @JvmStatic
        private fun provideNameAndDetails(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("name", null),
                Arguments.of("name - detail1", "detail1"),
                Arguments.of("name - detail1, detail2", "detail1\ndetail2"),
                Arguments.of("name - detail1 &amp;#43; detail2", "detail1\ndetail2"),
            )
        }
    }
}
