package com.zhmyr.parser

import com.sun.istack.internal.NotNull
import com.sun.istack.internal.Nullable
import org.w3c.dom.Node
import java.util.*

//http://www.fictionbook.org/index.php/Элемент_body
class Body internal constructor(body: Node) {
    @get:Nullable
    var lang: String? = null
    @get:Nullable
    var name: String? = null
    @get:Nullable
    var title: Title? = null
    @get:Nullable
    var image: Image? = null
    @get:NotNull
    var sections =
        listOf<Section>()
    @get:Nullable
    var epigraphs: ArrayList<Epigraph>? = null

    init {
        val section = mutableListOf<Section>()
        val attrs = body.attributes
        for (index in 0 until attrs.length) {
            val attr = attrs.item(index)
            if (attr.nodeName == "name") {
                name = attr.nodeValue
            }
            if (attr.nodeName == "xml:lang") {
                lang = attr.nodeValue
            }
        }
        val map = body.childNodes
        for (index in 0 until map.length) {
            val node = map.item(index)
            when (node.nodeName) {
                "section" -> section += Section(node)
                "img" -> title = Title(node)
                "name" -> name = node.textContent
                "image" -> image = Image(node)
                "epigraph" -> {
                    if (epigraphs == null) epigraphs = ArrayList()
                    epigraphs!!.add(Epigraph(node))
                }
            }
        }
        sections = section
    }
}