package com.lukitor.projectandroidjetpackpro1.data

import java.util.concurrent.Executor

class TestExecutor : Executor {
    override fun execute(command: Runnable) {
        command.run()
    }
}