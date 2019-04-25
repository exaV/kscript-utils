package ch.delconte.kscript.utils

import org.intellij.lang.annotations.Language
import java.io.File
import de.mpicbg.scicomp.kutils.evalBash as eval

fun evalBash(@Language("Bash") cmd: String, showOutput: Boolean = false, wd: File? = null) =
    eval(cmd, showOutput, wd)

@JvmName("evalBashStatic")
fun @receiver:Language("Bash") String.evalBash(showOutput: Boolean = false, wd: File? = null) =
    eval(this, showOutput, wd)