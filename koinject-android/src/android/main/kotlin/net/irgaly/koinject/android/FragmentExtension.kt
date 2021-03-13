package net.irgaly.koinject.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.irgaly.koinject.Koinject
import net.irgaly.koinject.component.KoinjectComponent
import net.irgaly.koinject.scope.Scope
import net.irgaly.koinject.scope.ScopeId

fun Fragment.retainedScope(): Scope {
    val viewModel = viewModels<ScopedViewModel>().value
    var scope = viewModel.scope
    if (scope == null) {
        val activityScope = requireActivity().retainedScope()
        scope = koinject.createInstanceScope(ScopeId.retainedFragment, this, activityScope)
        viewModel.scope = scope
    }
    return scope
}

val Fragment.koinject: Koinject
    get() = when(this) {
    is KoinjectComponent -> this.koinject
    else -> requireActivity().koinject
}
