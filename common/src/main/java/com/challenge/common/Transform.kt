package com.challenge.common

interface Transform<T1, T2> {
    fun map(value: T1): T2
}