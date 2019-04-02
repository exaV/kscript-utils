package ch.delconte.kscript.utils

import java.io.File

fun evalBash(cmd: String, workingDir: String = ".", showOutput: Boolean = true, printCmd: Boolean = false): Int {
    val dir = File(workingDir)
    if (printCmd) {
        println("${dir.absolutePath}$ $cmd")
    }

    var builder = ProcessBuilder("/bin/sh", "-c", cmd)
        .directory(dir)

    if (showOutput) {
        builder = builder.inheritIO()
    }

    val returnValue = builder.start().waitFor()

    return returnValue
}

@JvmName("evalBashStatic")
fun String.evalBash(workingDir: String = ".", showOutput: Boolean = true, printCmd: Boolean = false) =
    evalBash(this, workingDir, showOutput, printCmd)

