package com.sierra.common.local.mapper

import com.sierra.common.domain.model.Category
import com.sierra.common.network.mapper.toDomain
import com.sierra.common.network.model.RemoteCategory
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class CategoryMappersTest {

    @Test
    fun `should map from remote category to domain category`() = runTest {
        val input = RemoteCategory(
            id = "his",
            name = "Maureen Roth",
        )
        val expected = Category(
            id = "his",
            name = "Maureen Roth",
        )

        input.toDomain() shouldBeEqualTo expected
    }
}
