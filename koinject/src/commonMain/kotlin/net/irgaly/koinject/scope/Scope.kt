package net.irgaly.koinject.scope

import net.irgaly.koinject.Koinject
import net.irgaly.koinject.definition.InstanceDefinition
import net.irgaly.koinject.exception.NoDefinitionException
import net.irgaly.koinject.instance.InstanceKey
import net.irgaly.koinject.module.Module
import net.irgaly.koinject.qualifier.Qualifier
import net.irgaly.koinject.registory.InstanceRegistry
import kotlin.reflect.KClass

class Scope(private val koinject: Koinject) {
    private val logger get() = koinject.logger
    val instances: InstanceRegistry = InstanceRegistry(koinject)
    val parent: Scope? = null
    val modules: MutableSet<Module> = mutableSetOf()

    fun loadModule(module: Module) {
        if (!modules.contains(module)) {
            modules.add(module)
            module.buildDefinitions(koinject).definitions.forEach {
                instances.addDefinition(it.value)
            }
        } else {
            logger.error { "module already loaded: $module" }
        }
    }

    fun unloadModule(module: Module) {
        if (modules.contains(module)) {
            modules.remove(module)
            module.buildDefinitions(koinject).definitions.forEach {
                instances.removeDefinition(it.value)
            }
        } else {
            logger.error { "module not loaded: $module" }
        }
    }

    inline fun <reified T: Any> register(instance: T, qualifier: Qualifier? = null) {
        instances.addDefinition(InstanceDefinition(true, T::class, qualifier) { instance })
    }

    inline fun <reified T: Any> unregister(instance: T, qualifier: Qualifier? = null) {
        instances.removeDefinition(InstanceDefinition(true, T::class, qualifier) { instance })
    }

    inline fun <reified T: Any> get(qualifier: Qualifier? = null): T {
        return get(T::class, qualifier)
    }

    fun <T: Any> get(type: KClass<T>, qualifier: Qualifier? = null): T {
        return resolveInstance(this, InstanceDefinition.instanceKey(type, qualifier))
    }

    internal fun <T: Any> resolveInstance(currentScope: Scope, key: InstanceKey): T {
        return instances.resolveInstance(currentScope, key)
            ?: parent?.resolveInstance(currentScope, key)
            ?: throw NoDefinitionException(key)

    }
}
