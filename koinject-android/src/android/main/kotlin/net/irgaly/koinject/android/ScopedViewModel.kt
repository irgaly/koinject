package net.irgaly.koinject.android

import androidx.lifecycle.ViewModel
import net.irgaly.koinject.scope.Scope

class ScopedViewModel : ViewModel() {
    var scope: Scope? = null

    override fun onCleared() {
        scope?.close()
    }
}
