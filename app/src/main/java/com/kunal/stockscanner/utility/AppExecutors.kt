package com.kunal.stockscanner.utility

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kunal on 2019-07-26.
 */
@Singleton
class AppExecutors @Inject constructor() {

    private val diskIO: Executor
    private val mainThread: Executor
    private val networkIO: Executor

    private class MainThreadExecutor : Executor {

        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    init {
        this.diskIO = Executors.newSingleThreadExecutor()
        this.networkIO = Executors.newFixedThreadPool(THREAD_COUNT)
        this.mainThread = MainThreadExecutor()
    }

    fun diskIO(): Executor {
        return diskIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    fun networkIO(): Executor {
        return networkIO
    }

    companion object {
        private val THREAD_COUNT = 5
    }
}