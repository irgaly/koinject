package net.irgaly.koinject.android

import net.irgaly.koinject.scope.ScopeId

val ScopeId.Companion.work: ScopeId get() = ScopeId("koinject.work")
