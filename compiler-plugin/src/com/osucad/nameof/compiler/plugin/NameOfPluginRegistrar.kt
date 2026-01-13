package com.osucad.nameof.compiler.plugin

import com.osucad.nameof.compiler.plugin.fir.checker.NameOfCheckerExtension
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class NameOfPluginRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::NameOfCheckerExtension
    }
}
