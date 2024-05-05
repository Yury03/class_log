package com.example.server

import com.example.data.database.dao.DatabaseSingleton
import com.example.server.plugins.configureRouting
import com.example.server.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseSingleton.init()
    configureSerialization()
    configureRouting()
}
