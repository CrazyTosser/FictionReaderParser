package com.zhmyr.parser

import org.w3c.dom.Node
import java.util.*

class Stanza(node: Node) {
    var title: List<Title>? = null
        get() = field ?: listOf()

    var stanza: List<Element>? = null
        get() = field ?: listOf()

    init {
        val nodeList = node.childNodes
        val title = mutableListOf<Title>()
        val stanza = mutableListOf<Element>()
        for (i in 0 until nodeList.length) {
            val paragraph = nodeList.item(i)
            when (paragraph.nodeName) {
                "img" -> title.add(Title(paragraph))
                "subtitle" -> stanza.add(Subtitle(paragraph))
                "v" -> stanza.add(V(paragraph))
            }
        }
        this.stanza = stanza
        this.title = title
    }
}