package net.irgaly.koinject.registory

import co.touchlab.stately.collections.IsoArrayDeque
import co.touchlab.stately.collections.IsoMutableMap
import co.touchlab.stately.concurrency.Lock
import co.touchlab.stately.concurrency.withLock
import net.irgaly.koinject.Koinject
import net.irgaly.koinject.definition.InstanceDefinition
import net.irgaly.koinject.instance.InstanceFactory
import net.irgaly.koinject.instance.InstanceKey
import net.irgaly.koinject.scope.Scope

class InstanceRegistry(koinject: Koinject) {
    private val lock = Lock()
    private val logger = koinject.logger
    private val instances: MutableMap<InstanceKey, MutableList<InstanceFactory<*>>> = IsoMutableMap()

    @OptIn(ExperimentalStdlibApi::class)
    fun addDefinition(definition: InstanceDefinition<*>) {
        lock.withLock {
            val key = definition.key()
            var deque = instances[key]
            if (deque == null) {
                deque = IsoArrayDeque()
                instances[key] = deque
            }
            deque.add(0, InstanceFactory.from(definition))
        }
    }

    fun removeDefinition(definition: InstanceDefinition<*>) {
        lock.withLock {
            val key = definition.key()
            val deque = instances[key]
            val found = deque?.find { it.definition == definition }
            if (found != null) {
                deque.remove(found)
                if (deque.isEmpty()) {
                    instances.remove(key)
                }
            } else {
                logger.error { "No definition in InstanceRegistry: $definition" }
            }
        }
    }

    fun <T> resolveInstance(currentScope: Scope, key: InstanceKey): T? {
        @Suppress("UNCHECKED_CAST")
        return instances[key]?.first()?.get() as? T
    }
}
