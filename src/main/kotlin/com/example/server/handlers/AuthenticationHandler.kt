package com.example.server.handlers

import com.example.data.database.dao.impl.KeysDaoImpl
import com.example.data.database.dao.impl.UsersDaoImpl
import com.example.domain.models.LogInPost
import com.example.domain.usecase.Authentication
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

private val usersImpl = UsersDaoImpl()
private val keysImpl = KeysDaoImpl()

private val logInUseCase = Authentication(usersImpl = usersImpl, keysImpl = keysImpl)

internal suspend fun PipelineContext<Unit, ApplicationCall>.logInHandler() {
    call.receive<LogInPost>().also { logIn ->
        logInUseCase(logIn)?.let {
            call.respond(HttpStatusCode.Created, message = it)
        } ?: call.respond(HttpStatusCode.InternalServerError)
    }
}