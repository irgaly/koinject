package net.irgaly.koinject.sample.android

import net.irgaly.koinject.Koinject
import net.irgaly.koinject.module.Module
import net.irgaly.koinject.scope.Scope
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SampleTest: Spek({
    describe("koinject 基本フロー") {
        it("Module 登録とインスタンス取得ができる") {
            val koinject = Koinject()
            val scope = Scope(koinject)
            scope.loadModule(SampleModule())
            scope.loadModule(SampleModule2())
            val classA:ClassA = scope.get()
            assertEquals(classA::class, ClassB::class)
            assertNotEquals(classA::class, ClassA::class)
        }
        it("Scope の親を辿ってインスタンス取得ができる") {
            val koinject = Koinject()
            val rootScope = Scope(koinject)
            rootScope.loadModule(SampleModule())
            val scope = Scope(koinject)
            scope.parent = rootScope
            scope.loadModule(SampleModule2())
            val classA:ClassA = scope.get()
            assertEquals(classA::class, ClassB::class)
            assertNotEquals(classA::class, ClassA::class)
        }
    }
})

class SampleModule: Module({
    factory<ClassA> { ClassB(get()) }
})
class SampleModule2: Module({
    factory { ClassC() }
})
abstract class ClassA
class ClassB(val classC: ClassC): ClassA()
class ClassC
