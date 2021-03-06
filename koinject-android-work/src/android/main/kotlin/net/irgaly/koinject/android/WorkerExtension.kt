package net.irgaly.koinject.android

import android.app.Application
import android.app.Service
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.WorkManager
import androidx.work.Worker
import net.irgaly.koinject.Koinject
import net.irgaly.koinject.component.KoinjectComponent
import net.irgaly.koinject.context.GlobalContext
import net.irgaly.koinject.qualifier.Qualifier
import net.irgaly.koinject.scope.Scope
import net.irgaly.koinject.scope.ScopeId

val Worker.koinjectScope: Scope
    get() {
        var scope = koinject.getScope(ScopeId.work, this)
        if (scope == null) {
            val manager = WorkManager.getInstance(applicationContext)
            if (manager.getWorkInfoById(id).isDone) {
                error("Worker is already completed")
            }
            scope = koinject.createInstanceScope(ScopeId.work, this)
            manager.getWorkInfoByIdLiveData(id).observe(ProcessLifecycleOwner.get()) { info ->
                if (info.state.isFinished) {
                    scope.close()
                }
            }
        }
        return scope
    }

val Worker.koinject: Koinject
    get() = when (this) {
        is KoinjectComponent -> this.koinject
        else -> (applicationContext as? Application)?.koinject ?: GlobalContext.koinject
    }

inline fun <reified T : Any> Service.inject(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED
): Lazy<T> = lazy(mode) {
    get(qualifier)
}

inline fun <reified T : Any> Worker.get(
    qualifier: Qualifier? = null
): T = koinjectScope.get(qualifier)
