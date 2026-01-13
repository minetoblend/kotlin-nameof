package com.osucad.nameof.compiler.plugin

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import com.osucad.nameof.compiler.plugin.ir.NameOfIrGenerationExtension
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter

class NameOfPluginComponentRegistrar: CompilerPluginRegistrar() {
    override val supportsK2: Boolean
        get() = true

    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        FirExtensionRegistrarAdapter.registerExtension(NameOfPluginRegistrar())
        IrGenerationExtension.registerExtension(NameOfIrGenerationExtension())
    }
}
