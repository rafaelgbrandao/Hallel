package com.hallel.core.extensions

private const val TXT_CLEANER_REGEX = "[\\s/)(.-]"

fun String.clearText(): String {
    return this.replace(Regex(TXT_CLEANER_REGEX), "")
}