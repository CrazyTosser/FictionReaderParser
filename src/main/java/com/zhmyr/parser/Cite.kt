package com.zhmyr.parser

import com.sun.istack.internal.NotNull
import com.sun.istack.internal.Nullable
import org.w3c.dom.Node
import java.util.*

//http://www.fictionbook.org/index.php/Элемент_cite
class Cite(node: Node) : Element() {
    var id: String? = null
    var lang: String? = null
    protected var elements: List<Element>? = null
        get() = field ?: listOf()
    protected var textAuthor: List<TextAuthor>? = null
        get() = field ?: listOf()

    override var text: String?
        get() = textAuthor!!.joinToString(separator = "\n")
        set(value) {}

    init {
        val attrs = node.attributes
        for (index in 0 until attrs.length) {
            val attr = attrs.item(index)
            if (attr.nodeName == "id") {
                id = attr.nodeValue
            }
            if (attr.nodeName == "xml:lang") {
                lang = attr.nodeValue
            }
        }
        val nodeList = node.childNodes
        val element = mutableListOf<Element>()
        val txt = mutableListOf<TextAuthor>()
        for (i in 0 until nodeList.length) {
            val paragraph = nodeList.item(i)
            when (paragraph.nodeName) {
                "text-author" -> txt += TextAuthor(paragraph)
                "poem" -> element += Poem(paragraph)
                "subtitle" -> element += Subtitle(paragraph)
                "p" -> element += P(paragraph)
                "empty-line" -> element+= EmptyLine()
            }
        }
        elements = element
        textAuthor = txt

    }

    override fun toString(): String {
        return "<blockquote>${elements?.joinToString("\n")}</blockquote>"
    }
}