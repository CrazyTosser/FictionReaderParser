package com.zhmyr.parser

import org.w3c.dom.Node
import java.util.*

class Poem internal constructor(node: Node) : Element() {
    var title: Title? = null
        protected set
    var epigraphs = listOf<Epigraph>()
        protected set
    protected var stanza = listOf<Stanza>()
    var textAuthor: String? = null
        protected set
    var date: String? = null
        protected set

    init {
        val nodeList = node.childNodes
        val epigraphs = mutableListOf<Epigraph>()
        val stanza = mutableListOf<Stanza>()
        for (i in 0 until nodeList.length) {
            val paragraph = nodeList.item(i)
            when (paragraph.nodeName) {
                "text-author" -> textAuthor = paragraph.textContent
                "img" -> title = Title(paragraph)
                "epigraph" -> epigraphs += Epigraph(paragraph)
                "date" -> date = paragraph.textContent
                "stanza" -> stanza += Stanza(paragraph)
            }
        }
        this.epigraphs = epigraphs
        this.stanza = stanza
    }

    override var text: String?
        get() {
            val list = mutableListOf<Element>()
            stanza.forEach { stanza1 ->
                    stanza1.title!!.forEach { title1 ->
                        list.addAll(title1.paragraphs)
                    }
                list.addAll(stanza1.stanza!!)
            }
            return list.joinToString(separator = "\n")
        }

        set(text) {
            super.text = text
        }
}