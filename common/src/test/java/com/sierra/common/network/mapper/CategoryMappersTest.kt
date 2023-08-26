package com.sierra.common.network.mapper

import com.sierra.common.domain.model.Category
import com.sierra.common.local.mapper.toDomain
import com.sierra.common.local.mapper.toLocal
import com.sierra.common.local.model.LocalCategory
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class CategoryMappersTest {

    @Test
    fun `should map from local category to domain category`() = runTest {
        val input = LocalCategory(
            id = "his",
            name = "Maureen Roth",
        )
        val expected = Category(
            id = "his",
            name = "Maureen Roth",
        )

        input.toDomain() shouldBeEqualTo expected
    }

    @Test
    fun `should map from domain category to local category`() = runTest {
        val input = Category(
            id = "his",
            name = "Maureen Roth",
        )
        val expected = LocalCategory(
            id = "his",
            name = "Maureen Roth",
        )

        input.toLocal() shouldBeEqualTo expected
    }
}
