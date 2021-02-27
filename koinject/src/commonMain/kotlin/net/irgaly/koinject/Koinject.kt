package net.irgaly.koinject

import net.irgaly.koinject.definition.Definitions
import net.irgaly.koinject.logger.EmptyLogger
import net.irgaly.koinject.logger.Logger

class Koinject {
    val logger: Logger = EmptyLogger()

    val globalDefinitions: Definitions = Definitions()
}
