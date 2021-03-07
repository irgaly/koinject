package net.irgaly.koinject.scope

import net.irgaly.koinject.module.Module

data class ScopeDefinition(
    val scopeId: ScopeId,
    val modules: MutableSet<Module> = mutableSetOf()
)
