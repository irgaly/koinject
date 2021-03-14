package net.irgaly.koinject.android

import android.app.Service
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleService
import net.irgaly.koinject.Koinject
import net.irgaly.koinject.component.KoinjectComponent
import net.irgaly.koinject.qualifier.Qualifier
import net.irgaly.koinject.scope.Scope
import net.irgaly.koinject.scope.ScopeId

val LifecycleService.koinjectScope: Scope
    get() {
        var scope = koinject.getScope(ScopeId.service, this)
        if (scope == null) {
            scope = koinject.createInstanceScope(ScopeId.service, this)
            lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        scope.close()
                    }
                }
            })
        }
        return scope
    }

val Service.koinject: Koinject
    get() = when (this) {
        is KoinjectComponent -> this.koinject
        else -> application.koinject
    }

inline fun <reified T : Any> Service.inject(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED
): Lazy<T> = lazy(mode) {
    get(qualifier)
}

inline fun <reified T : Any> Service.get(
    qualifier: Qualifier? = null
): T = when (this) {
    is LifecycleService -> koinjectScope.get(qualifier)
    else -> application.koinjectScope.get(qualifier)
}
