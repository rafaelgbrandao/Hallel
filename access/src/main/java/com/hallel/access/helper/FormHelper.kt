package com.hallel.access.helper

import com.hallel.core.extensions.clearText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val EMAIL_REGEX = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9-](?!.*?\\.\\.)[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
const val PHONE_MASK = "{([00]) [00000]-[0000]"
const val DATE_MASK = "[00]{/}[00]{/}[9900]"
private val locale = Locale("pt", "BR")

fun isValidBirthday(birthday: String): Boolean {
    return birthday.replace("/", "").length == 8 && isDateConversionValid(birthday)
}

fun isValidPhone(phone: String): Boolean {
    return phone.clearText().length == 11
}

fun isValidEmail(email: String): Boolean {
    return Regex(EMAIL_REGEX).matches(email)
}

fun isValidName(name: String): Boolean {
    return name.isNotEmpty() && name.length >= 2
}

private fun isDateConversionValid(
    date: String,
    datePattern: String = "dd/MM/yyyy",
    pattern: SimpleDateFormat = SimpleDateFormat(datePattern, locale)
): Boolean {
    return try {
        val allowedMinUserDate = Calendar.getInstance().apply { add(Calendar.YEAR, -18) }
        val allowedMaxUserDate = Calendar.getInstance().apply { add(Calendar.YEAR, -100) }
        pattern.isLenient = false
        val dateFormat = pattern.parse(date)
        dateFormat?.let {
            it.before(allowedMinUserDate.time) && it.after(allowedMaxUserDate.time)
        } ?: false
    } catch (e: ParseException) {
        false
    }
}