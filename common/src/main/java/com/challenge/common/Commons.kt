package com.challenge.common

fun Int.toMoneyFormat(): String {
    val format = String.format("%,d", this).replace(",", ".")
    return "$ $format"
}
