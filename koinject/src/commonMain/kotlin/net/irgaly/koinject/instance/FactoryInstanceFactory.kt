package net.irgaly.koinject.instance

import net.irgaly.koinject.definition.InstanceDefinition
import net.irgaly.koinject.scope.Scope

class FactoryInstanceFactory<T: Any>(
    definition: InstanceDefinition<T>
): InstanceFactory<T>(definition) {
    override fun get(scope: Scope): T {
        return create(scope)
    }
}
