package com.example.composemovieapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Schedule(
    val days: List<String>,
    val time: String
): Parcelable