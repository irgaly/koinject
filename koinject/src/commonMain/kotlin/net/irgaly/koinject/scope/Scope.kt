package net.irgaly.koinject.scope

import net.irgaly.koinject.Koinject
import net.irgaly.koinject.definition.InstanceDefinition
import net.irgaly.koinject.exception.NoDefinitionException
import net.irgaly.koinject.instance.InstanceKey
import net.irgaly.koinject.module.Module
import net.irgaly.koinject.qualifier.Qualifier
import net.irgaly.koinject.registory.InstanceRegistry
import kotlin.reflect.KClass

class Scope internal constructor(
    private val koinject: Koinject,
    val scopeKey: ScopeKey,
    private var _parent: Scope? = null
) {
    private val logger get() = koinject.logger
    private val _children: MutableList<Scope> = mutableListOf()
    private val _modules: MutableSet<Module> = mutableSetOf()
    private val instances: InstanceRegistry = InstanceRegistry(koinject)

    val parent: Scope? get() = _parent
    val children: List<Scope> = _children
    val modules: Set<Module> = _modules

    fun loadModule(module: Module) {
        if (!_modules.contains(module)) {
            _modules.add(module)
            module.buildDefinitions(koinject).definitions.forEach {
                instances.addDefinition(it.value)
            }
        } else {
            logger.error { "module already loaded: $module" }
        }
    }

    fun unloadModule(module: Module) {
        if (_modules.contains(module)) {
            _modules.remove(module)
            module.buildDefinitions(koinject).definitions.forEach {
                instances.removeDefinition(it.value)
            }
        } else {
            logger.error { "module not loaded: $module" }
        }
    }

    inline fun <reified T: Any> register(instance: T, qualifier: Qualifier? = null) {
        register(T::class, instance, qualifier)
    }

    fun <T: Any> register(type: KClass<T>, instance: T, qualifier: Qualifier? = null) {
        instances.addDefinition(InstanceDefinition(true, type, qualifier) { instance })
    }

    inline fun <reified T: Any> unregister(instance: T, qualifier: Qualifier? = null) {
        unregister(T::class, instance, qualifier)
    }

    fun <T: Any> unregister(type: KClass<T>, instance: T, qualifier: Qualifier? = null) {
        instances.removeDefinition(InstanceDefinition(true, type, qualifier) { instance })
    }

    inline fun <reified T: Any> get(qualifier: Qualifier? = null): T {
        return get(T::class, qualifier)
    }

    fun <T: Any> get(type: KClass<T>, qualifier: Qualifier? = null): T {
        return resolveInstance(this, InstanceKey.from(type, qualifier))
    }

    fun close() {
        koinject.closeScope(this)
    }

    fun addChild(child: Scope) {
        _children.add(child)
    }

    fun removeChild(child: Scope) {
        _children.remove(child)
    }

    internal fun <T: Any> resolveInstance(currentScope: Scope, key: InstanceKey): T {
        return instances.resolveInstance(currentScope, key)
            ?: _parent?.resolveInstance(currentScope, key)
            ?: throw NoDefinitionException(key.value)
    }

    internal fun applyDefinition(definition: ScopeDefinition) {
        definition.modules.forEach {
            loadModule(it)
        }
    }

    override fun toString(): String {
        return scopeKey.toString()
    }
}
