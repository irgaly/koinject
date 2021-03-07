package net.irgaly.koinject.module

import net.irgaly.koinject.Koinject
import net.irgaly.koinject.definition.InstanceDefinitions
import net.irgaly.koinject.scope.ScopeId

abstract class Module(
    val scopeId: ScopeId? = null,
    protected val builder: InstanceDefinitions.() -> Unit
) {
    fun buildDefinitions(koinject: Koinject): InstanceDefinitions {
        return InstanceDefinitions(koinject.globalDefinitions).apply {
            builder()
        }
    }
}
