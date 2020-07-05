package com.javadsh98.news.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun rawDateToPretty(date: String): String{

    val oldSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    var sdfDate : Date? = null
    try{
        sdfDate = oldSimpleDateFormat.parse(date)
    }catch (e: ParseException){}

    val newSimpleDateFormat = SimpleDateFormat("E, d MMM yyyy")
    return newSimpleDateFormat.format(sdfDate)
}