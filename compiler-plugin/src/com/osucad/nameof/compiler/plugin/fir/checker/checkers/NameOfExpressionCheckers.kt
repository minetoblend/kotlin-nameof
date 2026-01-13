package com.osucad.nameof.compiler.plugin.fir.checker.checkers

import org.jetbrains.kotlin.KtFakeSourceElementKind
import com.osucad.nameof.compiler.plugin.fir.checker.NameOfError
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.diagnostics.reportOn
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.context.findClosest
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirFunctionCallChecker
import org.jetbrains.kotlin.fir.expressions.*
import org.jetbrains.kotlin.fir.references.toResolvedNamedFunctionSymbol
import org.jetbrains.kotlin.name.FqName

class NameOfExpressionCheckers : ExpressionCheckers() {
    override val functionCallCheckers: Set<FirFunctionCallChecker>
        get() = setOf(CallChecker)
}

object CallChecker : FirFunctionCallChecker(MppCheckerKind.Common) {
    private val nameOfFqn = FqName("com.osucad.nameof.nameOf")

    context(context: CheckerContext, reporter: DiagnosticReporter)
    override fun check(expression: FirFunctionCall) {
        val symbol = expression.calleeReference.toResolvedNamedFunctionSymbol() ?: return

        val fqName = symbol.callableId.asSingleFqName()
        if (fqName != nameOfFqn) return

        if (expression.arguments.size == 1) {
            when (val argument = expression.argument) {
                is FirFunctionCall -> {
                    if (argument.calleeReference.source?.kind is KtFakeSourceElementKind.ArrayAccessNameReference) {
                        reporter.reportOn(
                            source = argument.source,
                            factory = NameOfError.ARRAY_ACCESS,
                        )
                    } else {
                        reporter.reportOn(
                            source = argument.source,
                            factory = NameOfError.CALL_EXPRESSION,
                        )
                    }
                }
                is FirLiteralExpression -> reporter.reportOn(
                    source = argument.source,
                    factory = NameOfError.CONSTANT,
                )
            }
        }
    }
}