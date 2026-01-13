package com.osucad.nameof.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

class NameOfIrGenerationExtension: IrGenerationExtension {
    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        for (file in moduleFragment.files) {
            NameOfCallTransformer(SourceFile(file), pluginContext).visitFile(file)
        }
    }
}
