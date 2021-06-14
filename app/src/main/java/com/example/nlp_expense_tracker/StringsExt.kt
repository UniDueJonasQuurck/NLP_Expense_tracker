package com.example.nlp_expense_tracker.fragments

import okio.Utf8.size
import java.util.ArrayList

fun String.findFloat(): ArrayList<Float> { //Eine Array aus all den float zahlen wird erstellt
    //get digits from result
    if (this.isEmpty()) return ArrayList<Float>()
    val originalResult = ArrayList<Float>()
    val matchedResults = Regex(pattern = "[+-]?([0-9]*[.])?[0-9]+").findAll(this)
    for (txt in matchedResults) {
        if (txt.value.isFloatAndWhole())
            originalResult.add(txt.value.toFloat())
    }
    return originalResult
}

fun String.getBetrag(): String {
    if (this.isEmpty()) return "" ///Aus dem Input wird mit .split eine Liste von Strings erstellt, wir lassen uns die betimmte stelle aus dem Array gegeben
    return this.split("\n").let {it[it.size -2]}
}

fun String.getGeschäft(): String { ///Aus dem Input wird mit .split eine Liset von Strigns erstellt, wir lassen uns die betimmte stelle aus dem Array gegeben
    if (this.isEmpty()) return ""
    return this.split("\n").get(0)
}



private fun String.isFloatAndWhole() = this.matches("\\d*\\.\\d*".toRegex())
private fun String.isDate() = this.matches("^\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*\$".toRegex()) // \d Zahl zwischen 0-9, \\. = Komma

