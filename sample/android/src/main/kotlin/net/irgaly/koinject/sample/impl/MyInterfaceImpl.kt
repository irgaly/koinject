package net.irgaly.koinject.sample.impl

import net.irgaly.koinject.sample.`interface`.MyInterface
import javax.inject.Inject

class MyInterfaceImpl constructor(): MyInterface {
    @Inject
    constructor(arg: String) : this()
    constructor(arg: Int) : this()
}
