package com.stone.lib.common.util

import android.graphics.Color

import java.util.Random

/**
 * desc     :
 * author   : stone
 *
 * email    : aa86799@163.com
 * time     : 2019/3/6 18 41
 */
object ColorsUtil {

    val color: Int
        get() {
            val sb = StringBuilder()
            val random = Random()
            var temp: String
            for (i in 0..2) {
                temp = Integer.toHexString(random.nextInt(0xFF))
                if (temp.length == 1) {
                    temp = "0$temp"
                }
                sb.append(temp)
            }
            return Color.parseColor("#$sb")
        }

}
