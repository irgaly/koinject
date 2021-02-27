package net.irgaly.koinject.logger

class EmptyLogger(): Logger(LogLevel.None) {
    override fun log(level: LogLevel, message: String) {
        throw NotImplementedError("no implementation for EmptyLogger")
    }
}
