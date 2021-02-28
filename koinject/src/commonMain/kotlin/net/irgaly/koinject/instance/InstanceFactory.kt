package net.irgaly.koinject.instance

import net.irgaly.koinject.definition.InstanceDefinition
import net.irgaly.koinject.scope.Scope

abstract class InstanceFactory<T: Any>(
    val definition: InstanceDefinition<T>
) {
    abstract fun get(scope: Scope): T

    protected fun create(scope: Scope): T {
        return definition.create(scope)
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
