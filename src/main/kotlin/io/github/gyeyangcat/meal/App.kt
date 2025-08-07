package io.github.gyeyangcat.meal

import com.github.instagram4j.instagram4j.requests.media.MediaCommentRequest
import kotlin.system.exitProcess
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    publish()
}

fun publish() {
    print("start publishing")
    val lunch = getMyLunch(getNowDate())
    val client = login()
    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))
    val img = createImg(lunch, formattedDate)
    client.actions()
        .timeline()
        .uploadPhoto(img, "${formattedDate}의 급식!\n\n${lunch}\n\n#계양중학교 #급식 #알리미 #봇")
        .join() // block current thread until complete
    print("i uploaded timeline")
    client.actions()
        .story()
        .uploadPhoto(img)
        .join() // block current thread until complete
    print("i uploaded story")
    exitProcess(0)
}

