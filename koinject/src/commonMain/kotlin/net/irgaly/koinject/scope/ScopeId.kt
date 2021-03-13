package net.irgaly.koinject.scope

inline class ScopeId(val value: String) {
    companion object {
        val root: ScopeId = ScopeId("koinject.root")
        val application: ScopeId = ScopeId("koinject.application")
    }
}
