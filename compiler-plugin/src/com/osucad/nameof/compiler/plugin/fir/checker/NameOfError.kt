package com.osucad.nameof.compiler.plugin.fir.checker

import com.intellij.psi.PsiElement
import com.osucad.nameof.compiler.plugin.fir.checker.NameOfError.ARRAY_ACCESS
import com.osucad.nameof.compiler.plugin.fir.checker.NameOfError.CALL_EXPRESSION
import com.osucad.nameof.compiler.plugin.fir.checker.NameOfError.CONSTANT
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
import org.jetbrains.kotlin.diagnostics.error0
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory

internal object NameOfError : KtDiagnosticsContainer() {
    val CONSTANT by error0<PsiElement>()

    val ARRAY_ACCESS by error0<PsiElement>()

    val CALL_EXPRESSION by error0<PsiElement>()

    override fun getRendererFactory(): BaseDiagnosticRendererFactory = Renderers


    private object Renderers : BaseDiagnosticRendererFactory() {

        override val MAP: KtDiagnosticFactoryToRendererMap by rendererMap
    }
}

private val rendererMap by lazy {
    KtDiagnosticFactoryToRendererMap("nameOf Errors") { map ->
        map.put(
            factory = CONSTANT,
            message = "nameOf() call with a constant"
        )

        map.put(
            factory = ARRAY_ACCESS,
            message = "nameOf() call with an array access"
        )

        map.put(
            factory = CALL_EXPRESSION,
            message = "nameOf() call with a function call"
        )
    }
}