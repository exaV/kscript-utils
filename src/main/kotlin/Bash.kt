package ch.delconte.kscript.utils

import de.mpicbg.scicomp.kutils.evalBash
import org.intellij.lang.annotations.Language
import java.io.File

@JvmName("evalBashStatic")
fun @receiver:Language("Bash") String.evalBash(showOutput: Boolean = false, wd: File? = null) =
    evalBash(this, showOutput, wd)