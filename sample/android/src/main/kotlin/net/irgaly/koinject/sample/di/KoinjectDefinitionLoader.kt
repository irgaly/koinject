package net.irgaly.koinject.sample.di

import net.irgaly.koinject.Koinject

object KoinjectDefinitionLoader {
    fun load(koinject: Koinject) {
        koinject.globalDefinitions.addDefinition(net.irgaly.koinject.sample.impl.MyInterfaceImpl::class) { net.irgaly.koinject.sample.impl.MyInterfaceImpl() }
    }
}
