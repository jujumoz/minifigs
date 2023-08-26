package com.sierra.common.local.mapper

import com.sierra.common.domain.model.Minifig
import com.sierra.common.local.model.LocalMinifig
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MInifigMappersTest {

    @Test
    fun `should map from local minifig to domain minifig`() = runTest {
        val input = LocalMinifig(
            type = "cursus",
            id = "iusto",
            name = "Eli Flores",
            categoryId = "ad",
            year = "singulis",
            weight = "natoque",
        )
        val expected = Minifig(
            type = "cursus",
            id = "iusto",
            name = "Eli Flores",
            categoryId = "ad",
            categoryName = "",
            year = "singulis",
            weight = "natoque",
        )

        input.toDomain() shouldBeEqualTo expected
    }

    @Test
    fun `should map from domain minifig to local minifig`() = runTest {
        val input = Minifig(
            type = "cursus",
            id = "iusto",
            name = "Eli Flores",
            categoryId = "ad",
            categoryName = "",
            year = "singulis",
            weight = "natoque",
        )
        val expected = LocalMinifig(
            type = "cursus",
            id = "iusto",
            name = "Eli Flores",
            categoryId = "ad",
            year = "singulis",
            weight = "natoque",
        )

        input.toLocal() shouldBeEqualTo expected
    }
}
