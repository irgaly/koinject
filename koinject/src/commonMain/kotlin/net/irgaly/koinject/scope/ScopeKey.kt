package net.irgaly.koinject.scope

data class ScopeKey(
    val scopeId: ScopeId,
    /**
     * scopeOwner Instance
     */
    val owner: Any? = null
) {
    companion object {
        val rootKey = ScopeKey(ScopeId.root)
    }
}
