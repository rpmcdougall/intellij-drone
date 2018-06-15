package io.sheltek.intellij.drone.model

import javax.annotation.Nullable

data class Id(
        val name: String,
        @Nullable
        val value: String
)