package ch.delconte.kscript.utils

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody


fun <T> ArgParser.parse(constructor: (ArgParser) -> T) = mainBody {
    this.parseInto(constructor)
}
