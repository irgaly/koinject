package net.irgaly.koinject.android

import android.app.Service
import androidx.lifecycle.*
import net.irgaly.koinject.Koinject
import net.irgaly.koinject.component.KoinjectComponent
import net.irgaly.koinject.scope.Scope
import net.irgaly.koinject.scope.ScopeId

val LifecycleService.koinjectScope: Scope get() {
    var scope = koinject.getScope(ScopeId.service, this)
    if (scope == null) {
        scope = koinject.createInstanceScope(ScopeId.service, this)
        lifecycle.addObserver(object: LifecycleEventObserver {
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
    get() = when(this) {
        is KoinjectComponent -> this.koinject
        else -> application.koinject
}

