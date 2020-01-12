package com.zhmyr.parser

import com.sun.istack.internal.NotNull
import org.w3c.dom.Node

//http://www.fictionbook.org/index.php/Элемент_binary
class Binary internal constructor(node: Node) : IdElement(node) {
    var contentType: String? = null
    var binary: String = node.textContent

    init {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            when (attr.nodeName) {
                "content-type" -> contentType = attr.nodeValue
            }
        }
    }

}