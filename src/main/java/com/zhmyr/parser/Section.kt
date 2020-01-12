package com.zhmyr.parser

import com.sun.istack.internal.Nullable
import org.w3c.dom.Node
import java.util.*

class Section internal constructor(root: Node) : IdElement(root) {
    @get:Nullable
    var image: Image? = null
    @get:Nullable
    var annotation: Annotation? = null
    protected var epigraphs: List<Epigraph>? = null
        get() = field ?: listOf()
    var sections: List<Section>? = null
        get() = field ?: listOf()
        private set
    var elements: List<Element>? = null
        get() = field ?: listOf()
        private set

    var title: Title? = null

    fun getTitleString(outerDivider:String = ""): String {
        if (title == null) return ""
        val list =
            ArrayList<Element>(title!!.paragraphs)
        return list.joinToString(separator = "\n")
    }

    override fun toString(): String {
        var data = getTitleString("\n")
        /**if (!elements!!.isEmpty()) {
            data += " p: " + elements!!.size
        }
        if (!elements!!.isEmpty()) {
            data += " section: " + sections!!.size
        }*/
        return data.trim { it <= ' ' }
    }

    init {
        val body = root.childNodes
        val epigraphs = mutableListOf<Epigraph>()
        val sections = mutableListOf<Section>()
        val elements = mutableListOf<Element>()

        for (item in 0 until body.length) {
            val node = body.item(item)
            when (node.nodeName) {
                "title" -> title = Title(node)
                "elements" -> annotation = Annotation(node)
                "image" -> elements.add(P(Image(node)))
                "epigraph" -> epigraphs.add(Epigraph(node))
                "section" -> sections.add(Section(node))
                "poem" -> elements.add(Poem(node))
                "subtitle" -> elements.add(Subtitle(node))
                "p" -> elements.add(P(node))
                "empty-line" -> elements.add(EmptyLine())
                "cite" -> elements.add(Cite(node))
            }
        }

        this.epigraphs = epigraphs
        this.sections = sections
        this.elements = elements
    }
}