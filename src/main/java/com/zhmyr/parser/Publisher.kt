package com.zhmyr.parser

import org.w3c.dom.Node

class Publisher(node: Node) : Person(node) {
    var lang: String? = null

    init {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "lang") {
                id = attr.nodeValue
            }
        }
    }

}