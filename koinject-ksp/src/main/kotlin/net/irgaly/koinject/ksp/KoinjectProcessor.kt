package net.irgaly.koinject.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated

internal class KoinjectProcessor : SymbolProcessor {
    private val koinjectSetupProcessor = KoinjectSetupProcessor()

    override fun init(
        options: Map<String, String>,
        kotlinVersion: KotlinVersion,
        codeGenerator: CodeGenerator,
        logger: KSPLogger
    ) {
        koinjectSetupProcessor.init(options, kotlinVersion, codeGenerator, logger)
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        return koinjectSetupProcessor.process(resolver)
    }

    override fun finish() {
        koinjectSetupProcessor.finish()
    }
}
