package com.osucad.nameof.compiler.plugin.services

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import com.osucad.nameof.compiler.plugin.NameOfPluginRegistrar
import com.osucad.nameof.compiler.plugin.ir.NameOfIrGenerationExtension
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices

fun TestConfigurationBuilder.configurePlugin() {
    useConfigurators(::ExtensionRegistrarConfigurator)
    configureRuntime()
}

private class ExtensionRegistrarConfigurator(testServices: TestServices) : EnvironmentConfigurator(testServices) {
    override fun CompilerPluginRegistrar.ExtensionStorage.registerCompilerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration
    ) {
        FirExtensionRegistrarAdapter.registerExtension(NameOfPluginRegistrar())
        IrGenerationExtension.registerExtension(NameOfIrGenerationExtension())
    }
}
