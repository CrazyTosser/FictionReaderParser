package com.zhmyr.parser

import org.w3c.dom.Document

class PublishInfo internal constructor(document: Document) {
    var bookName: String? = null
        protected set
    var city: String? = null
        protected set
    var year: String? = null
        protected set
    var publisher: String? = null
        protected set
    var isbn: String? = null
        protected set
    var sequence: Sequence? = null
        protected set

    init {
        val description = document.getElementsByTagName("publish-info")
        for (item in 0 until description.length) {
            val map = description.item(item).childNodes
            for (index in 0 until map.length) {
                val node = map.item(index)
                when (node.nodeName) {
                    "book-name" -> bookName = node.textContent
                    "city" -> city = node.textContent
                    "year" -> year = node.textContent
                    "isbn" -> isbn = node.textContent
                    "publisher" -> publisher = node.textContent
                    "sequence" -> sequence = Sequence(node)
                }
            }
        }
    }

}