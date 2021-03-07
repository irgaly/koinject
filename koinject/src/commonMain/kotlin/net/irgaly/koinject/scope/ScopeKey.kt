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

    override fun toString(): String {
        return if (owner != null)  "${scopeId.value}::$owner"
        else scopeId.value
    }
}
