package net.irgaly.koinject.definition

import net.irgaly.koinject.instance.InstanceKey
import net.irgaly.koinject.platform.fullName
import net.irgaly.koinject.qualifier.Qualifier
import kotlin.reflect.KClass

/**
 * インスタンス定義
 */
data class InstanceDefinition<T: Any>(
    val single: Boolean,
    val type: KClass<T>,
    val qualifier: Qualifier? = null,
    val definition: Definition<T>
) {
    companion object {
        fun <T: Any> instanceKey(type: KClass<T>, qualifier: Qualifier? = null): InstanceKey {
            return if (qualifier != null) {
                "${type.fullName}::${qualifier.value}"
            } else type.fullName
        }
    }

    fun key(): InstanceKey {
        return instanceKey(type, qualifier)
    }

    fun create(): T {
        return definition()
    }
}
