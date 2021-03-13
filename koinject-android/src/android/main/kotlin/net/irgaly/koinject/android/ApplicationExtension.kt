package net.irgaly.koinject.android

import android.app.Application
import androidx.lifecycle.*
import net.irgaly.koinject.Koinject
import net.irgaly.koinject.component.KoinjectComponent
import net.irgaly.koinject.context.GlobalContext
import net.irgaly.koinject.scope.Scope
import net.irgaly.koinject.scope.ScopeId

val Application.koinjectScope: Scope get() {
    var scope = koinject.getScope(ScopeId.application, this)
    if (scope == null) {
        scope = koinject.createInstanceScope(ScopeId.application, this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(object: LifecycleEventObserver {
            override fun onStateChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    scope.close()
                }
            }
        })
    }
    return scope
}

val Application.koinject: Koinject
    get() = when(this) {
        is KoinjectComponent -> this.koinject
        else -> GlobalContext.koinject
}
