package com.stone.lib.common.router

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.min

/**
 * 线程池
 */
object DefaultPoolExecutor {

    private val sThreadFactory = object : ThreadFactory {
        private val mCount = AtomicInteger(1)

        override fun newThread(r: Runnable): Thread {
            return Thread(r, "SRouter #" + mCount.getAndIncrement())
        }
    }

    //核心线程和最大线程都是cpu核心数+1
    private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
    private val MAX_CORE_POOL_SIZE = CPU_COUNT + 1
    //存活30秒 回收线程
    private const val KEEP_ALIVE_TIME = 30L

    fun newDefaultPoolExecutor(corePoolSize: Int): ThreadPoolExecutor? {
        var corePoolSize = corePoolSize
        if (corePoolSize == 0) {
            return null
        }
        corePoolSize = min(corePoolSize, MAX_CORE_POOL_SIZE)
        val threadPoolExecutor = ThreadPoolExecutor(
            corePoolSize,
            corePoolSize, KEEP_ALIVE_TIME, TimeUnit.SECONDS, ArrayBlockingQueue(64), sThreadFactory
        )
        //核心线程也会被销毁
        threadPoolExecutor.allowCoreThreadTimeOut(true)
        return threadPoolExecutor
    }

}
