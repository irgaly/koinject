package net.irgaly.koinject.component

import net.irgaly.koinject.Koinject
import net.irgaly.koinject.context.GlobalContext

interface KoinjectComponent {
    val koinject: Koinject get() = GlobalContext.koinject
}
