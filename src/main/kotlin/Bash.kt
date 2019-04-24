package ch.delconte.kscript.utils

import de.mpicbg.scicomp.kutils.BashResult
import de.mpicbg.scicomp.kutils.evalBash
import org.intellij.lang.annotations.Language
import java.io.*

@JvmName("evalBashStatic")
fun @receiver:Language("Bash") String.evalBash(showOutput: Boolean = false, wd: File? = null) =
    evalBash(this, showOutput, wd)

fun evalBash(@Language("Bash") cmd: String, showOutput: Boolean = false, wd: File? = null): BashResult {
    try {
        // optionally prefix script with working directory change
        val cmd = (if (wd != null) "cd '${wd.absolutePath}'\n" else "") + cmd

        var pb = ProcessBuilder("/bin/bash", "-c", cmd);
        if (showOutput) {
            pb.inheritIO()
        }

        pb.directory(File("."));
        var p = pb.start();

        val outputGobbler = StreamGobbler(p.getInputStream())
        val errorGobbler = StreamGobbler(p.getErrorStream())
//         kick them off
        errorGobbler.start()
        outputGobbler.start()

        // any error???
        val exitVal = p.waitFor()
        return BashResult(exitVal, outputGobbler.sb.lines(), errorGobbler.sb.lines())
    } catch (t: Throwable) {
        throw RuntimeException(t)
    }
}


// workaround missing lines() function in java
private fun BufferedReader.linesJ7(): Iterable<String> = lineSequence().toList()

internal class StreamGobbler(var inStream: InputStream) : Thread() {
    var sb = StringBuilder()

    override fun run() {
        try {
            val isr = InputStreamReader(inStream)
            val br = BufferedReader(isr)
            for (line in br.linesJ7()) {
                sb.append(line + "\n")
            }
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }


    val output: String get() = sb.toString()
}