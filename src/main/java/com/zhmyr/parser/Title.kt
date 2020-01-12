package com.zhmyr.parser

import com.sun.istack.internal.NotNull
import org.w3c.dom.Node
import java.util.*

class Title constructor(root: Node) {

    var paragraphs = listOf<P>()
        protected set

    init {
        val body = root.childNodes
        val p = mutableListOf<P>()
        for (item in 0 until body.length) {
            val node = body.item(item)
            when (node.nodeName) {
                "p" -> p += P(node)
            }
        }
        paragraphs = p
    }

    override fun toString(): String {
        return paragraphs.joinToString("\n")
    }

}