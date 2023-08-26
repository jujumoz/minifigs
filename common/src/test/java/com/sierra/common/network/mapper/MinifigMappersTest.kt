package com.sierra.common.network.mapper

import com.sierra.common.domain.model.Minifig
import com.sierra.common.network.model.RemoteMinifig
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MinifigMappersTest {

    @Test
    fun `should map from remote minifig to domain minifig`() = runTest {
        val input = RemoteMinifig(
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
}
