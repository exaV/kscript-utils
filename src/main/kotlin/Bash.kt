package ch.delconte.kscript.utils

import org.intellij.lang.annotations.Language
import java.io.File
import java.io.IOException
import java.io.InputStream

@JvmName("evalBashStatic")
fun @receiver:Language("Bash") String.evalBash(showOutput: Boolean = false, wd: File? = null) =
    evalBash(this, showOutput, wd)

data class BashResult(val exitCode: Int, val stdout: Iterable<String>, val stderr: Iterable<String>) {
    fun sout() = stdout.joinToString("\n").trim()

    fun serr() = stderr.joinToString("\n").trim()
}

fun evalBash(@Language("Bash") cmd: String, showOutput: Boolean = false, wd: File? = null): BashResult {

    try {

        // optionally prefix script with working directory change
        val cmd = if (wd != null) "cd '${wd.absolutePath}' && $cmd" else cmd


        val pb = ProcessBuilder("/bin/bash", "-c", cmd)
        if (showOutput) {
            pb.inheritIO()
        }
        pb.directory(File("."))

        val p = pb.start()
        val outputGobbler = StreamGobbler(p.inputStream)
        val errorGobbler = StreamGobbler(p.errorStream)
        errorGobbler.start()
        outputGobbler.start()

        // any error???
        val exitVal = p.waitFor()

        return BashResult(exitVal, outputGobbler.lines, errorGobbler.lines)
    } catch (t: Throwable) {
        throw RuntimeException(t)
    }
}


internal class StreamGobbler(var inStream: InputStream) : Thread() {
    var sb = StringBuilder()

    override fun run() {
        try {
            for (line in inStream.bufferedReader().lineSequence()) {
                sb.appendln(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    val lines
        @Synchronized
        get() = sb.lines()
}