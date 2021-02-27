package net.irgaly.koinject.ksp

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate

internal class ConstructorProcessor : SymbolProcessor {
    private lateinit var codeGenerator: CodeGenerator
    private lateinit var logger: KSPLogger

    private val targets = mutableListOf<KSClassDeclaration>()

    override fun init(
        options: Map<String, String>,
        kotlinVersion: KotlinVersion,
        codeGenerator: CodeGenerator,
        logger: KSPLogger
    ) {
        this.codeGenerator = codeGenerator
        this.logger = logger
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation("")

        symbols.filter { ksAnnotated ->
            ksAnnotated is KSClassDeclaration && ksAnnotated.validate()
        }.forEach { kSClassDeclaration ->
            kSClassDeclaration.accept(Visitor(), Unit)
        }

        return emptyList()
    }

    override fun finish() {
        val packageName = "net.irgaly.koinject.ksp.test"
        val output = codeGenerator.createNewFile(
            Dependencies(true),
            packageName, "GeneratedTest"
        )
        output.write("""
            package $packageName
            
            class GeneratedTest
        """.trimIndent().toByteArray())
        output.close()
    }
}
