package dev.miran.entity.error

import dev.miran.entity.error.HttpStatusCode

data class ResponseException(val code: HttpStatusCode, val errors:List<String> = emptyList()) :
    Exception() {

    override val message: String
        get() = "${code.code} ${code.name}"
}
