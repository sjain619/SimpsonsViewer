package com.sample.codebase_sdk.util

/**
 * This is the class that will drive the type of data needed
 *
 * If we require to support more items,
 * we can simply add the new ENUMS to the class
 *
 */
enum class AppType(val endpoint: String) {
    SIMPSONS("simpsons characters"),
    THE_WIRE("the wire characters")
}