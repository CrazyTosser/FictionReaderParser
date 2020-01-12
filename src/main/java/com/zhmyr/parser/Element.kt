package com.zhmyr.parser

import org.w3c.dom.Node

open class Element {
    open var text: String?

    constructor() {
        this.text = null
    }

    constructor(p: Node) {
        this.text = p.textContent
    }

    constructor(p: String?) {
        this.text = p
    }

    override fun toString(): String {
        return text ?: ""
    }
}