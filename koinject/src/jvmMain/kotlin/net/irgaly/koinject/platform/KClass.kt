package net.irgaly.koinject.platform

import kotlin.reflect.KClass

actual val KClass<*>.fullName: String
    get() {
        return this.java.name
    }
