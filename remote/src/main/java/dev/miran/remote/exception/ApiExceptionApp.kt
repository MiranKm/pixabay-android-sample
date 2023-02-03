package dev.miran.remote.exception


class ApiExceptionApp(
    message: String = "",
    type: ExceptionType = ExceptionType.ERROR
) : AppIOException(message, type)
