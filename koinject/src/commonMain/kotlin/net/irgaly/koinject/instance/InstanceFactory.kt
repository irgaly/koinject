package net.irgaly.koinject.instance

import net.irgaly.koinject.definition.InstanceDefinition

abstract class InstanceFactory<T: Any>(
    val definition: InstanceDefinition<T>
) {
    abstract fun get(): T

    protected fun create(): T {
        return definition.create()
    }

    companion object {
        fun <T: Any> from(definition: InstanceDefinition<T>): InstanceFactory<T> {
            return when (definition.single) {
                true -> SingleInstanceFactory(definition)
                false -> FactoryInstanceFactory(definition)
            }
        }
    }
}
