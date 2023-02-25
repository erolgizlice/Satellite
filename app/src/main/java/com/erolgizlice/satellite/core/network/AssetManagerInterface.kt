package com.erolgizlice.satellite.core.network

import java.io.InputStream

fun interface AssetManagerInterface {
    fun open(fileName: String): InputStream
}