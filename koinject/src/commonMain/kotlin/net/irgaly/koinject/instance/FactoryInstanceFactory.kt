package net.irgaly.koinject.instance

import net.irgaly.koinject.definition.InstanceDefinition

class FactoryInstanceFactory<T: Any>(
    definition: InstanceDefinition<T>
): InstanceFactory<T>(definition) {
    override fun get(): T {
        return create()
    }
}
