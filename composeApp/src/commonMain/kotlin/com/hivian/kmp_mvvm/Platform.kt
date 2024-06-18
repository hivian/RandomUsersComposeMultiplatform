package com.hivian.kmp_mvvm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
