package com.osucad.nameof.compiler.plugin.fir.checker

import com.osucad.nameof.compiler.plugin.fir.checker.checkers.NameOfExpressionCheckers
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension

internal class NameOfCheckerExtension(session: FirSession) : FirAdditionalCheckersExtension(session) {
    override val expressionCheckers: ExpressionCheckers
        get() = NameOfExpressionCheckers()
}