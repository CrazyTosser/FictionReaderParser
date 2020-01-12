package com.zhmyr.parser

import org.w3c.dom.Node

class Date internal constructor(node: Node) {
    var value: String? = null
        protected set
    var date: String? = null
        protected set

    init {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "value") {
                value = attr.nodeValue
            }
        }
        date = node.textContent
    }

}