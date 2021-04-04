package net.irgaly.koinject.sample.di

import net.irgaly.koinject.module.Module
import net.irgaly.koinject.sample.`interface`.MyInterface
import net.irgaly.koinject.sample.impl.MyInterfaceImpl

class MyModule: Module({
    factory<MyInterface, MyInterfaceImpl>()
})
