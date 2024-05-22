package com.example.server.handlers

import com.example.data.database.dao.impl.KeysDaoImpl
import com.example.data.database.dao.impl.UsersDaoImpl
import com.example.domain.models.AdditionPost
import com.example.domain.models.LogInPost
import com.example.domain.usecase.Authentication
import com.example.domain.usecase.CreateNewUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

private val usersImpl = UsersDaoImpl()
private val keysImpl = KeysDaoImpl()

private val createNewUserUseCase = CreateNewUser(usersImpl = usersImpl)
private val logInUseCase = Authentication(usersImpl = usersImpl, keysImpl = keysImpl)
internal suspend fun PipelineContext<Unit, ApplicationCall>.addNewUserHandler() {
    call.receive<AdditionPost>().also { addition ->
        if (addition.serverKey == "my_private_key") { // ключ для создания пользователей
            createNewUserUseCase(addition)?.let {
                call.respond(HttpStatusCode.Created)
            } ?: call.respond(HttpStatusCode.InternalServerError)
        } else {
            call.respond(HttpStatusCode.Forbidden, "Ошибка аутентификации")
        }
    }
}

