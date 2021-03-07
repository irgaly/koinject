package net.irgaly.koinject

import net.irgaly.koinject.definition.Definitions
import net.irgaly.koinject.logger.EmptyLogger
import net.irgaly.koinject.logger.Logger
import net.irgaly.koinject.module.Module
import net.irgaly.koinject.registory.ScopeRegistry
import net.irgaly.koinject.scope.Scope
import net.irgaly.koinject.scope.ScopeId
import net.irgaly.koinject.scope.ScopeKey

class Koinject {
    val logger: Logger = EmptyLogger()

    val globalDefinitions: Definitions = Definitions()

    private val scopeRegistry: ScopeRegistry = ScopeRegistry(this)

    fun loadModule(module: Module) {
        scopeRegistry.loadModule(module)
    }

    fun unloadModule(module: Module) {
        scopeRegistry.unloadModule(module)
    }

    fun createScope(scopeId: ScopeId, parentScope: Scope? = null): Scope {
        return scopeRegistry.createScope(ScopeKey(scopeId), parentScope)
    }

    fun createScope(scopeId: ScopeId, owner: Any, parentScope: Scope? = null): Scope {
        return scopeRegistry.createScope(ScopeKey(scopeId, owner), parentScope)
    }

    fun closeScope(scope: Scope) {
        scopeRegistry.removeScope(scope)
    }
}
