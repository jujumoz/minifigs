package com.sierra.common.domain.model

import java.io.Serializable

data class Minifig(
    val type: String,
    val id: String,
    val name: String,
    val categoryId: String,
    val categoryName: String = "",
    val year: String,
    val weight: String,
) : Serializable {
    fun image(): String = IMAGE_URL.format(id)

    fun shortName(): String =
        name.split(NAME_AND_DETAIL_DELIMITER, DETAIL_DELIMITER, AND_SYMBOL).first().trim()

    fun fullName(): String =
        name.split(NAME_AND_DETAIL_DELIMITER).first().clean()

    fun details(): String? {
        val nameAndDetails = name.split(NAME_AND_DETAIL_DELIMITER)
        return if (nameAndDetails.size > 1) {
            val details = nameAndDetails[1]
            details.replace(DETAIL_DELIMITER, "\n")
                .clean()
                .lineSequence()
                .map { it.trim() }
                .joinToString("\n")
        } else {
            null
        }
    }

    private fun String.clean(): String = replace(Regex("&amp;#[0-9]*;"), "\n").trim()

    companion object {
        private const val NAME_AND_DETAIL_DELIMITER = "-"
        private const val DETAIL_DELIMITER = ","
        private const val AND_SYMBOL = "&amp;"
        private const val IMAGE_URL = "https://img.bricklink.com/ItemImage/MN/0/%s.png"
    }
}
