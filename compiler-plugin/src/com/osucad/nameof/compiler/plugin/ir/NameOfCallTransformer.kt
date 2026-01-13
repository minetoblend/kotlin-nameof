package com.osucad.nameof.compiler.plugin.ir

import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.util.isNullable
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.name.FqName

class NameOfCallTransformer(context: IrPluginContext) : IrElementTransformerVoidWithContext() {
    private val irBuiltIns = context.irBuiltIns

    private val nameOfFqn = FqName("com.osucad.nameof.nameOf")

    override fun visitCall(expression: IrCall): IrExpression {
        val function = expression.symbol.owner
        val fqName = function.kotlinFqName

        if (fqName != nameOfFqn)
            return super.visitCall(expression)

        val name = getArgumentName(expression) ?: return super.visitCall(expression)

        return IrConstImpl(-1, -1, irBuiltIns.stringType, IrConstKind.String, value = name)
    }

    private fun getArgumentName(call: IrCall): String? {
        if (call.typeArguments.isNotEmpty()) {
            val typeArgument = call.typeArguments[0] ?: return null

            val nullable = typeArgument.isNullable()
            val typeName = typeArgument.classFqName?.shortName()?.asString() ?: return null

            return when {
                nullable -> "$typeName?"
                else -> typeName
            }
        }

        return when (val argument = call.arguments.single()) {
            is IrPropertyReference ->
                argument.symbol.owner.name.asString()

            is IrDeclarationReference -> {
                val owner = argument.symbol.owner as? IrDeclarationWithName ?: return null

                owner.name.asString()
            }

            else -> null
        }
    }
}