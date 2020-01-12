package com.zhmyr.parser

import org.w3c.dom.Node

class Epigraph internal constructor(root: Node) : IdElement() {
    var elements = listOf<Element>()
    var textAuthor = listOf<TextAuthor>()

    init {
        val map = root.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "id") {
                id = attr.nodeValue
            }
        }
        val body = root.childNodes
        val elements = mutableListOf<Element>()
        val textAuthor = mutableListOf<TextAuthor>()
        for (item in 0 until body.length) {
            val node = body.item(item)
            when (node.nodeName) {
                "text-author" -> textAuthor += TextAuthor(node)
                "poem" -> elements += Poem(node)
                "cite" -> elements += Cite(node)
                "p" -> elements += P(node)
            }
        }
        this.elements = elements
        this.textAuthor = textAuthor
    }

}