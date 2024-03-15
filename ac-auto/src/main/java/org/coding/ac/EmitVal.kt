package org.coding.ac

import lombok.Getter
import lombok.ToString


@Getter
@ToString
data class EmitVal<String>(
    private val beginIndex: Int,
    private val endIndex: Int,
    private val value: String?,
)