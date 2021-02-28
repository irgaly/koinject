package net.irgaly.koinject.instance

import co.touchlab.stately.concurrency.Lock
import co.touchlab.stately.concurrency.withLock
import net.irgaly.koinject.definition.InstanceDefinition
import net.irgaly.koinject.scope.Scope

class SingleInstanceFactory<T: Any>(
    definition: InstanceDefinition<T>
): InstanceFactory<T>(definition) {
    private val lock = Lock()
    private var value: T? = null

    override fun get(scope: Scope): T {
        return value ?: createSingle(scope)
    }

    private fun createSingle(scope: Scope): T {
        return lock.withLock {
            var instance = value
            if (instance == null) {
                instance = create(scope)
                value = instance
            }
            instance
        }
    }
}
