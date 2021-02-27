package net.irgaly.koinject.instance

import co.touchlab.stately.concurrency.Lock
import co.touchlab.stately.concurrency.withLock
import net.irgaly.koinject.definition.InstanceDefinition

class SingleInstanceFactory<T: Any>(
    definition: InstanceDefinition<T>
): InstanceFactory<T>(definition) {
    private val lock = Lock()
    private var value: T? = null

    override fun get(): T {
        return value ?: createSingle()
    }

    private fun createSingle(): T {
        return lock.withLock {
            var instance = value
            if (instance == null) {
                instance = create()
                value = instance
            }
            instance
        }
    }
}
