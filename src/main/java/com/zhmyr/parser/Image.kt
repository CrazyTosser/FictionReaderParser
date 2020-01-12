package com.zhmyr.parser

import org.w3c.dom.Node

data class Image internal constructor(val node: Node) {
    var name: String? = node.attributes.item(0).nodeName
        protected set
    var value: String? = node.attributes.item(0).nodeValue
        set

}