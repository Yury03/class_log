package com.example.server.plugins

import com.example.domain.models.Addition
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/authentication") {
            call.respondText("Hello World!")
        }
        post("/authentication/addUser") {
            call.receive<Addition>().also { addition ->
                if (addition.serverKey.equals("my_private_key")) {
                    //create user
                } else {
                    call.respond(HttpStatusCode.Forbidden, "Ошибка аутентификации")
                }
            }
        }

        get("/schedule") {
            call.respondText("Hello World!")
        }
        get("/schedule/myClass") {
            call.respondText("Hello World!")
        }
    }
}
