package com.zhmyr.parser

import com.sun.istack.internal.NotNull
import com.sun.istack.internal.Nullable
import org.w3c.dom.Node
import java.util.*

//http://www.fictionbook.org/index.php/Элемент_annotation
class Annotation internal constructor(node: Node) : IdElement(node) {
    var lang: String? = null
    var elements: List<Element>

    init {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "xml:lang") {
                lang = attr.nodeValue
            }
        }
        val nodeList = node.childNodes
        val el = mutableListOf<Element>()
        for (i in 0 until nodeList.length) {
            val paragraph = nodeList.item(i)
            when (paragraph.nodeName) {
                "p" -> el += P(paragraph)
                "poem" -> el += Poem(paragraph)
                "cite" -> el += Cite(paragraph)
                "subtitle" -> el += Subtitle(paragraph)
                "empty-line" -> el += EmptyLine()
                "table" -> el += Table()
            }
        }
        elements = el
    }

    override fun toString(): String {
        return elements.joinToString(separator = "\n")
    }
}