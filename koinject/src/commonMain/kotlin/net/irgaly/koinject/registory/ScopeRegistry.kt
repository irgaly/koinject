package net.irgaly.koinject.registory

import co.touchlab.stately.collections.IsoMutableMap
import co.touchlab.stately.concurrency.Lock
import co.touchlab.stately.concurrency.withLock
import net.irgaly.koinject.Koinject
import net.irgaly.koinject.module.Module
import net.irgaly.koinject.scope.Scope
import net.irgaly.koinject.scope.ScopeDefinition
import net.irgaly.koinject.scope.ScopeId
import net.irgaly.koinject.scope.ScopeKey

/**
 * Koinject の Scope コンテナ
 * スレッドセーフ
 */
internal class ScopeRegistry(private val koinject: Koinject) {
    val rootScope = Scope(koinject, ScopeKey.rootKey)

    private val lock = Lock()
    private val logger = koinject.logger
    private val scopeDefinitions: MutableMap<ScopeId, ScopeDefinition> = IsoMutableMap()
    private val scopes: MutableMap<ScopeId, MutableMap<ScopeKey, Scope>> = IsoMutableMap()

    init {
        scopes[rootScope.scopeKey.scopeId] = IsoMutableMap {
            mutableMapOf(rootScope.scopeKey to rootScope)
        }
    }

    fun loadModule(module: Module) {
        lock.withLock {
            val scopeId = module.scopeId
            if (scopeId == null) {
                rootScope.loadModule(module)
            } else {
                val definition = scopeDefinitions.getOrPut(scopeId) {
                    ScopeDefinition(scopeId)
                }
                definition.modules += module
                scopes[scopeId]?.values?.forEach {
                    it.loadModule(module)
                }
            }
        }
    }

    fun unloadModule(module: Module) {
        lock.withLock {
            val scopeId = module.scopeId
            if (scopeId == null) {
                rootScope.unloadModule(module)
            } else {
                val definition = scopeDefinitions[scopeId] ?: error("no scope definition: $scopeId")
                if (!definition.modules.contains(module)) {
                    error("no module in scope definition: $definition")
                }
                definition.modules.remove(module)
                if (definition.modules.isEmpty()) {
                    scopeDefinitions.remove(scopeId)
                }
                scopes[scopeId]?.values?.forEach {
                    it.unloadModule(module)
                }
            }
        }
    }

    fun createScope(key: ScopeKey, parentScope: Scope? = null): Scope {
        lock.withLock {
            val parent = parentScope ?: rootScope
            if (scopes[parent.scopeKey.scopeId]?.get(parent.scopeKey) == null) {
                error("Parent Scope is not registered: $parent")
            }
            if (scopes[key.scopeId]?.get(key) != null) {
                error("Scope already exists: $key")
            }
            val scope = Scope(koinject, key, parentScope)
            val definition = scopeDefinitions[key.scopeId]
            if (definition != null) {
                scope.applyDefinition(definition)
            }
            parent.addChild(scope)
            scopes.getOrPut(key.scopeId) {
                IsoMutableMap()
            }[key] = scope
            return scope
        }
    }

    fun removeScope(key: ScopeKey) {
        lock.withLock {
            var remove: ((key: ScopeKey) -> Scope)? = null
            remove = { key ->
                val map = scopes[key.scopeId]
                val scope = map?.get(key)
                if (scope == null) {
                    error("scope is not registered: $key")
                } else {
                    scope.children.forEach {
                        remove?.invoke(it.scopeKey)
                    }
                    map.remove(key)
                    if (map.isEmpty()) {
                        scopes.remove(key.scopeId)
                    }
                    scope
                }
            }
            val removed = remove(key)
            removed.parent?.removeChild(removed)
        }
    }

    fun removeScope(scope: Scope) {
        removeScope(scope.scopeKey)
    }
}
