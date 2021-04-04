package net.irgaly.koinject.ksp

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated

internal class KoinjectSetupProcessor : SymbolProcessor {
    private lateinit var codeGenerator: CodeGenerator
    private lateinit var logger: KSPLogger

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
        return emptyList()
    }

    override fun finish() {
        val packageName = "net.irgaly.koinject.sample.di"
        val output = codeGenerator.createNewFile(
            Dependencies(true),
            packageName, "KoinjectSetup"
        )
        output.write(
            """
            package $packageName

            fun setupKoinject() {
                val koinject = net.irgaly.koinject.context.GlobalContext.koinject
                KoinjectDefinitionLoader.load(koinject)
                KoinjectModuleLoader.load(koinject)
            }
        """.trimIndent().toByteArray()
        )
        output.close()
    }
}
