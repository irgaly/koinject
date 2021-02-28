package net.irgaly.koinject.definition

import net.irgaly.koinject.scope.Scope

/**
 * クラスの生成定義
 */
typealias Definition<T> = Scope.() -> T
