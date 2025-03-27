package com.app.hackernews.core

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.longToDate(): String {
    val instant = Instant.ofEpochMilli(this)
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}
