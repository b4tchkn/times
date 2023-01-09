package com.b4tchkn.times.util

fun urlFromHtmlTag(html: String): String {
    val tag = "<a href=\""
    if (Regex(tag).containsMatchIn(html)) {
        val startIndex = html.indexOf(tag)
        val endIndex = html.indexOf("\"", startIndex + tag.length)
        if (startIndex == -1) return ""
        return html.substring(startIndex = startIndex + tag.length, endIndex = endIndex)
    }
    return ""
}
