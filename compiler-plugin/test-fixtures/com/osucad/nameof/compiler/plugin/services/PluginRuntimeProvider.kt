package com.osucad.nameof.compiler.plugin.services

import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoots
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.RuntimeClasspathProvider
import org.jetbrains.kotlin.test.services.TestServices
import java.io.File

private val runtimeClasspath =
    System.getProperty("nameofRuntime.classpath")?.split(File.pathSeparator)?.map(::File)
        ?: error("Unable to get a valid classpath from 'nameofRuntime.classpath' property")

fun TestConfigurationBuilder.configureRuntime() {
    useConfigurators(::PluginRuntimeProvider)
    useCustomRuntimeClasspathProviders(::PluginRuntimeClasspathProvider)
}

private class PluginRuntimeProvider(testServices: TestServices) : EnvironmentConfigurator(testServices) {
    override fun configureCompilerConfiguration(configuration: CompilerConfiguration, module: TestModule) {
        configuration.addJvmClasspathRoots(runtimeClasspath)
    }
}

private class PluginRuntimeClasspathProvider(testServices: TestServices) : RuntimeClasspathProvider(testServices) {
    override fun runtimeClassPaths(module: TestModule) = runtimeClasspath
}