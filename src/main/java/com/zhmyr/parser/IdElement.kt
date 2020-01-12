package com.zhmyr.parser

import com.sun.istack.internal.Nullable
import org.w3c.dom.Node

open class IdElement {
    open var id: String? = null

    internal constructor()
    internal constructor(node: Node) {
        val map = node.attributes
        for (index in 0 until map.length) {
            val attr = map.item(index)
            if (attr.nodeName == "id") {
                this.id = attr.nodeValue
            }
        }
    }

}