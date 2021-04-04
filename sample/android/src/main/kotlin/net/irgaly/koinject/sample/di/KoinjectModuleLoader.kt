package net.irgaly.koinject.sample.di

import net.irgaly.koinject.Koinject

object KoinjectModuleLoader {
    fun load(koinject: Koinject) {
        koinject.loadModule(net.irgaly.koinject.sample.di.MyModule())
    }
}
