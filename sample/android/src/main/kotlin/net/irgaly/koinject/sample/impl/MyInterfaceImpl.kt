package net.irgaly.koinject.sample.impl

import net.irgaly.koinject.sample.`interface`.MyInterface
import javax.inject.Inject

class MyInterfaceImpl @Inject constructor() : MyInterface {
    constructor(arg: String) : this()
    constructor(arg: Int) : this()
}
