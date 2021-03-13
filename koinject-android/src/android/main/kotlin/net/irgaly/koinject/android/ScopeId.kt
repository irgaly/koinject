package net.irgaly.koinject.android

import net.irgaly.koinject.scope.ScopeId

val ScopeId.Companion.service: ScopeId get() = ScopeId("koinject.service")

val ScopeId.Companion.retainedActivity: ScopeId get() = ScopeId("koinject.activity.retained")

val ScopeId.Companion.retainedFragment: ScopeId get() = ScopeId("koinject.fragment.retained")
