package com.dnd.sixth.lmsservice.presentation.extensions

import android.content.res.ColorStateList
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.data.network.base.NetworkCommons
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.custom.DateColor
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.type.DayOfWeek
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection

fun List<String>.visibleIfContains(text: String, view: View) {
    if (this.contains(text)) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

/*
* @param dowBinary : 0000001, 0110011 (요일을 비트화 해놓은 형태, 오른쪽부터 왼쪽으로 '월화수목금토일')
* */
fun visibleViewListIfContain(dayOfWeekBit: String, views: List<View>) {
    val containDate = '1'
    val notContainDate = '0'

    val dayOfWeekCharList = dayOfWeekBit.toCharArray() // 전달받은 요일(dayOfWeekBit)

    for (i in dayOfWeekCharList.size downTo 0) {
        if (dayOfWeekCharList[i] == containDate) {
            views[i].visibility = View.VISIBLE
        } else {
            views[i].visibility = View.GONE
        }
    }
}

fun applyDowColor(color: Int, views: List<TextView>) {

    when (color) {
        DateColor.RED.ordinal -> {
            views.forEach { dowView ->
                dowView.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            App.instance.context, DateColor.RED.getTextColorResId()
                        )
                    )
                dowView.setTextColor(ContextCompat.getColor(
                    App.instance.context, DateColor.RED.getBgColorResId()
                ))
            }
        }

        DateColor.ORANGE.ordinal -> {
            views.forEach { dowView ->
                dowView.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            App.instance.context, DateColor.ORANGE.getTextColorResId()
                        )
                    )
                dowView.setTextColor(ContextCompat.getColor(
                    App.instance.context, DateColor.ORANGE.getBgColorResId()
                ))
            }
        }

        DateColor.YELLOW.ordinal -> {
            views.forEach { dowView ->
                dowView.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            App.instance.context, DateColor.YELLOW.getTextColorResId()
                        )
                    )
                dowView.setTextColor(ContextCompat.getColor(
                    App.instance.context, DateColor.YELLOW.getBgColorResId()
                ))
            }
        }

        DateColor.GREEN.ordinal -> {
            views.forEach { dowView ->
                dowView.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            App.instance.context, DateColor.GREEN.getTextColorResId()
                        )
                    )
                dowView.setTextColor(ContextCompat.getColor(
                    App.instance.context, DateColor.GREEN.getBgColorResId()
                ))
            }
        }

        DateColor.BLUE.ordinal -> {
            views.forEach { dowView ->
                dowView.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            App.instance.context, DateColor.BLUE.getTextColorResId()
                        )
                    )
                dowView.setTextColor(ContextCompat.getColor(
                    App.instance.context, DateColor.BLUE.getBgColorResId()
                ))
            }
        }

        DateColor.DARK_BLUE.ordinal -> {
            views.forEach { dowView ->
                dowView.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            App.instance.context, DateColor.DARK_BLUE.getTextColorResId()
                        )
                    )
                dowView.setTextColor(ContextCompat.getColor(
                    App.instance.context, DateColor.DARK_BLUE.getBgColorResId()
                ))
            }
        }

        DateColor.PURPLE.ordinal -> {
            views.forEach { dowView ->
                dowView.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            App.instance.context, DateColor.PURPLE.getTextColorResId()
                        )
                    )
                dowView.setTextColor(ContextCompat.getColor(
                    App.instance.context, DateColor.PURPLE.getBgColorResId()
                ))
            }
        }


    }
}

// Request Header에 Access Token 또는 Refresh Token 추가
fun Request.putToken(token: String): Request {
    return this.newBuilder().addHeader(NetworkCommons.AUTH_HEADER, token).build()
}

fun Response.isTokenInvalid(): Boolean {
    val response = this // Access 토큰 유효 검사

    // 401 Error 반환시 (Access Token이 유효하지 않거나, 없는 경우)
    return response.code == HttpURLConnection.HTTP_UNAUTHORIZED
}


// Boolean 값을 토글시켜 반환한다.
fun Boolean.toggle(): Boolean = this.not()


// DayOfWeek HashMap으로 부터 유저가 선택한 요일을 비트로 변환
fun Map<DayOfWeek, Boolean>.convertDowBit(): String {
    // 순서대로 (일토금목수화월)
    val dayOfWeekCharList = mutableListOf<Char>('0', '0', '0', '0', '0', '0', '0')

    var cursor = dayOfWeekCharList.size - 1 // 맨 오른쪽 인덱스부터 접근
    this.forEach { (_, isContains) ->
        if (isContains) { // 유저가 해당 날짜를 과외 요일에 포함했다면
            // 1로 갱신
            dayOfWeekCharList[cursor] = '1'
        }
        cursor -= 1
    }

    return dayOfWeekCharList.joinToString("")
}


// DayOfWeek HashMap의 Value가 모두 False인지 반환
fun Map<DayOfWeek, Boolean>.isAllFalse(): Boolean {

    // Value들 중 하나라도 참이면 False반환
    this.values.forEach { isChecked ->
        if (isChecked) {
            return false
        }
    }
    return true
}