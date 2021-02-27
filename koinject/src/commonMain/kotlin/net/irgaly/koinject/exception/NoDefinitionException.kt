package net.irgaly.koinject.exception

class NoDefinitionException : Exception {
    constructor(message: String, cause: Throwable? = null) : super(message, cause)
    constructor(cause: Throwable? = null) : super(cause)
}
