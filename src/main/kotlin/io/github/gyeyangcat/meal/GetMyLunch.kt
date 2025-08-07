 package io.github.gyeyangcat.meal

import com.google.gson.JsonParser
import okhttp3.*
import java.text.SimpleDateFormat
import java.util.*

fun getMyLunch(date: String): String {
    val http = OkHttpClient()
    val url = "https://open.neis.go.kr/hub/mealServiceDietInfo" +
        "?KEY=" + System.getenv("NEIS_KEY") +
        "&TYPE=json" +
        "&pIndex=1" +
        "&pSize=1" +
        "&ATPT_OFCDC_SC_CODE=E10" +
        "&SD_SCHUL_CODE=7331213" +
        "&MLSV_YMD=$date"

    val request = Request.Builder()
        .url(url)
        .get()
        .build()

    val res = http.newCall(request).execute()
    val body = res.body!!.string()

    println(body)

    val json = JsonParser.parseString(body)
        .asJsonObject

    val info = json.getAsJsonArray("mealServiceDietInfo")[1]
        .asJsonObject
        .getAsJsonArray("row")[0]
        .asJsonObject

    return info.get("DDISH_NM").asString
        .replace(" ", "")
        .replace("\\(\\d+(\\.\\d+)*\\)".toRegex(), "")
        .replace("<br/>", "\n")
}

fun getDateArray(): List<String> = run {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"))
    var dt1 = SimpleDateFormat("YYYY/MM/dd")
    dt1.format(cal.time).toString().split("/")

    //return listOf("2023", "09", "28")
}

fun getNowDate(): String = run {
    val cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"))
    val dt1 = SimpleDateFormat("YYYYMMdd")
    dt1.format(cal.time).toString()
}

fun removeNumbersInString(input: String): String {
    val strBuilder = StringBuilder()
    input.split("\n")
        .map { txt -> txt.substring(0,
            0.coerceAtLeast(txt
                .indexOfFirst { Character.isDigit(it) || it == '(' }).run { if (this <= 0) txt.length else this }) }
        .forEach { strBuilder.append(it) ; strBuilder.append("\n")}
    return strBuilder.toString()
        .replace("+", "")
        .replace("-", "")
        .replace("*", "")
        .replace(";", "")
        .replace("&", "")
}

fun assertIsLunch(input: String) = input.apply { if (input.length < 3) { throw Exception("No Lunch Found!") } }
