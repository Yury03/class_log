package com.example.server.plugins

import com.example.server.handlers.addNewUserHandler
import com.example.server.handlers.logsHandler
import com.example.server.handlers.logInHandler
import com.example.server.handlers.schedulesHandler
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/authentication") {
            logInHandler()
        }
        post("/authentication/addUser") {
            addNewUserHandler()
        }
        route("schedule") {
            post {
                schedulesHandler()
            }
            post("/{id}") {
                val id = call.parameters["id"]!!.toInt()
                logsHandler(id)
            }
        }
        get("/schedule/myClass") {
            call.respondText("this is shedule for your class")
        }
    }
}