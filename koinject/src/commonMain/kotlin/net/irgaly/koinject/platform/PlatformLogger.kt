package net.irgaly.koinject.platform

import net.irgaly.koinject.logger.LogLevel
import net.irgaly.koinject.logger.Logger

expect class PlatformLogger(level: LogLevel = LogLevel.Info): Logger
