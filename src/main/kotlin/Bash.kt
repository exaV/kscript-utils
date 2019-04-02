package ch.delconte.kscript.utils

import de.mpicbg.scicomp.kutils.evalBash
import java.io.File

@JvmName("evalBashStatic")
fun String.evalBash(
    showOutput: Boolean = false,
    redirectStdout: File? = null, redirectStderr: File? = null, wd: File? = null
) = evalBash(this, showOutput, redirectStdout, redirectStderr)

