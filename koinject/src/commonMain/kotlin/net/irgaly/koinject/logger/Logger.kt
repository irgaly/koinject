package net.irgaly.koinject.logger

abstract class Logger(val level: LogLevel = LogLevel.Info) {

    abstract fun log(level: LogLevel, message: String)

    inline fun debug(message: () -> String) {
        if (level <= LogLevel.Debug) {
            log(LogLevel.Debug, message())
        }
    }

    inline fun info(message: () -> String) {
        if (level <= LogLevel.Info) {
            log(LogLevel.Info, message())
        }
    }

    inline fun error(message: () -> String) {
        if (level <= LogLevel.Error) {
            log(LogLevel.Error, message())
        }
    }
}
