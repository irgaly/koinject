package net.irgaly.koinject.definition

import kotlin.reflect.KClass

/**
 * クラス生成定義の集合
 */
class Definitions {
    private val definitions: MutableMap<KClass<*>, Definition<*>> = mutableMapOf()

    fun <T: Any> addDefinition(kClass: KClass<T>, definition: Definition<T>) {
        definitions[kClass] = definition
    }

    fun <T : Any> getDefinition(kClass: KClass<T>): Definition<T>? {
        @Suppress("UNCHECKED_CAST")
        return definitions[kClass] as? Definition<T>
    }
}
