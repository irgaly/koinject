package net.irgaly.koinject.android

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import net.irgaly.koinject.Koinject
import net.irgaly.koinject.component.KoinjectComponent
import net.irgaly.koinject.scope.Scope
import net.irgaly.koinject.scope.ScopeId

fun ComponentActivity.retainedScope(): Scope {
    val viewModel = viewModels<ScopedViewModel>().value
    var scope = viewModel.scope
    if (scope == null) {
        val applicationScope = application.koinjectScope
        scope = koinject.createInstanceScope(ScopeId.retainedActivity, this, applicationScope)
        viewModel.scope = scope
    }
    return scope
}

val ComponentActivity.koinject: Koinject get() = when(this) {
    is KoinjectComponent -> this.koinject
    else -> application.koinject
}
