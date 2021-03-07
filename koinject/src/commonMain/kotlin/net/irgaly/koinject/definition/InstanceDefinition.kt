package net.irgaly.koinject.definition

import net.irgaly.koinject.instance.InstanceKey
import net.irgaly.koinject.qualifier.Qualifier
import net.irgaly.koinject.scope.Scope
import kotlin.reflect.KClass

/**
 * インスタンス定義
 */
data class InstanceDefinition<T : Any>(
    val single: Boolean,
    val type: KClass<T>,
    val qualifier: Qualifier? = null,
    val definition: Definition<T>
) {
    fun key(): InstanceKey {
        return InstanceKey.from(type, qualifier)
    }

    fun create(scope: Scope): T {
        return definition(scope)
    }
}
