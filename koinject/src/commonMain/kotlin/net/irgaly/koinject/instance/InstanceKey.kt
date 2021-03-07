package net.irgaly.koinject.instance

import net.irgaly.koinject.platform.fullName
import net.irgaly.koinject.qualifier.Qualifier
import kotlin.reflect.KClass

inline class InstanceKey(val value: String) {
    companion object {
        fun from(kClass: KClass<*>, qualifier: Qualifier? = null): InstanceKey {
            return InstanceKey(
                if (qualifier != null) "${kClass.fullName}::${qualifier.value}"
                else kClass.fullName
            )
        }
    }
}

