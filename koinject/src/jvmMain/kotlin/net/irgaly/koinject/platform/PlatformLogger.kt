package net.irgaly.koinject.platform

import net.irgaly.koinject.logger.LogLevel
import net.irgaly.koinject.logger.Logger

actual class PlatformLogger actual constructor(level: LogLevel): Logger(level) {
    override fun log(level: LogLevel, message: String) {
        val formatted = "[$level] $message"
        if (LogLevel.Error <= level) {
            System.err.println(formatted)
        } else {
            println(formatted)
        }
    }
}
