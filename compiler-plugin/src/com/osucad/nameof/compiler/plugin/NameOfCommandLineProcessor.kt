package com.osucad.nameof.compiler.plugin

import com.osucad.nameof.BuildConfig
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration

@Suppress("unused") // Used via reflection.
class NameOfCommandLineProcessor : CommandLineProcessor {
    override val pluginId: String = BuildConfig.KOTLIN_PLUGIN_ID

    override val pluginOptions: Collection<CliOption> = emptyList()

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        error("Unexpected config option: '${option.optionName}'")
    }
}
