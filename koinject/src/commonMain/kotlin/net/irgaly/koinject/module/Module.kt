package net.irgaly.koinject.module

import net.irgaly.koinject.Koinject
import net.irgaly.koinject.definition.InstanceDefinitions

abstract class Module(protected val builder: InstanceDefinitions.() -> Unit) {
    fun buildDefinitions(koinject: Koinject): InstanceDefinitions {
        return InstanceDefinitions(koinject.globalDefinitions).apply {
            builder()
        }
    }
}
