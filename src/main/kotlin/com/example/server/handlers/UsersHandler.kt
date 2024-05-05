package com.example.server.handlers

import com.example.data.database.dao.impl.UsersDaoImpl
import com.example.domain.models.Addition
import com.example.domain.models.LogIn
import com.example.domain.usecase.Authentication
import com.example.domain.usecase.CreateNewUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

private val impl = UsersDaoImpl()

private val createNewUserUseCase = CreateNewUser(impl = impl)
private val logInUseCase = Authentication(impl = impl)
internal suspend fun PipelineContext<Unit, ApplicationCall>.addNewUserHandler() {
    call.receive<Addition>().also { addition ->
        if (addition.serverKey == "my_private_key") { // ключ для создания пользователей
            createNewUserUseCase(addition)?.let {
                call.respond(HttpStatusCode.Created)
            } ?: call.respond(HttpStatusCode.InternalServerError)
        } else {
            call.respond(HttpStatusCode.Forbidden, "Ошибка аутентификации")
        }
    }
}

internal suspend fun PipelineContext<Unit, ApplicationCall>.logInHandler() {
    call.receive<LogIn>().also { logIn ->
        logInUseCase(logIn)?.let {
            call.respond(HttpStatusCode.Created, message = it)
        } ?: call.respond(HttpStatusCode.InternalServerError)
    }
}
