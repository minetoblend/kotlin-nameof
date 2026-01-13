package com.osucad.nameof.compiler.plugin.ir

import org.jetbrains.kotlin.fir.backend.FirMetadataSource
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.PsiIrFileEntry
import org.jetbrains.kotlin.ir.SourceRangeInfo
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.path

class SourceFile(val irFile: IrFile)
{
    private val source by lazy { irFile.readSourceText() }

    fun getSourceRangeInfo(element: IrElement): SourceRangeInfo {
        return irFile.fileEntry.getSourceRangeInfo(element.startOffset, element.endOffset)
    }

    fun getText(info: SourceRangeInfo): String {
        return getText(info.startOffset, info.endOffset)
    }

    fun getText(start: Int, end: Int): String {
        return source.substring(maxOf(start, 0), minOf(end, source.length))
    }
}

private fun IrFile.readSourceText(): String {
    // Try and access the source text via FIR metadata.
    val sourceFile = (metadata as? FirMetadataSource.File)?.fir?.sourceFile
    if (sourceFile != null) {
        return sourceFile.getContentsAsStream().use { it.reader().readText() }
    }

    // If FIR metadata isn't available, try and get source text via PSI.
    when (val entry = fileEntry) {
        is PsiIrFileEntry -> return entry.psiFile.text
    }

    error("Unable to find source for IrFile: $path")
}