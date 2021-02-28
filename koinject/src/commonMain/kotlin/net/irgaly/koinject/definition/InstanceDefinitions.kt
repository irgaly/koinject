package net.irgaly.koinject.definition

import net.irgaly.koinject.exception.NoDefinitionException
import kotlin.reflect.KClass

/**
 * インスタンス定義の集合
 */
class InstanceDefinitions(val globalDefinitions: Definitions) {
    val definitions: MutableMap<KClass<*>, InstanceDefinition<*>> = mutableMapOf()

    fun <T : Any> getDefinition(kClass: KClass<T>): InstanceDefinition<T>? {
        @Suppress("UNCHECKED_CAST")
        return definitions[kClass] as? InstanceDefinition<T>
    }

    inline fun <reified T : Any> factory(noinline definition: Definition<T>) {
        definitions[T::class] = InstanceDefinition(false, T::class, definition = definition)
    }

    inline fun <reified R : Any, reified T : R> factory() {
        val definition = globalDefinitions.getDefinition(T::class)
            ?: throw NoDefinitionException(T::class.toString())
        definitions[R::class] = InstanceDefinition(false, R::class, definition = definition)
    }

    inline fun <reified T : Any> single(noinline definition: Definition<T>) {
        definitions[T::class] = InstanceDefinition(true, T::class, definition = definition)
    }

    inline fun <reified R : Any, reified T : R> single() {
        val definition = globalDefinitions.getDefinition(T::class)
            ?: throw NoDefinitionException(T::class.toString())
        definitions[R::class] = InstanceDefinition(true, R::class, definition = definition)
    }
}
