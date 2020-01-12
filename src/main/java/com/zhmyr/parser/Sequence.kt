package com.zhmyr.parser

import org.w3c.dom.Node

class Sequence(node: Node) {
    var name: String? = null
        protected set
    var number: String? = null
        protected set

    init {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "name") {
                name = attr.nodeValue
            } else if (attr.nodeName == "number") {
                number = attr.nodeValue
            }
        }
    }

}