package com.zhmyr.parser

import org.w3c.dom.Document
import java.util.*

class SrcTitleInfo(doc: Document?, tagName: String) : TitleInfo(doc!!, tagName)

open class TitleInfo(document: Document, tagName: String) {
    lateinit var keywords:List<String>
        protected set
    var bookTitle: String = ""
        protected set
        get() = "<h1>$field</h1>"
    var date: String? = null
        protected set
    var lang: String? = null
        protected set
    var srcLang: String? = null
        protected set
    lateinit var authors:List<Person>
        protected set
    lateinit var translators:List<Person>
        protected set
    var annotation: Annotation? = null
        protected set
    lateinit var coverPage:List<Image>
        protected set
    var sequence: Sequence? = null
        protected set

    init {
        val description = document.getElementsByTagName(tagName)
        for (item in 0 until description.length) {
            val authors = mutableListOf<Person>()
            val translators = mutableListOf<Person>()
            val coverPage = mutableListOf<Image>()
            val keywords = mutableListOf<String>()
            val map = description.item(item).childNodes
            for (index in 0 until map.length) {
                val node = map.item(index)
                when (node.nodeName) {
                    "sequence" -> sequence = Sequence(node)
                    "coverpage" -> {
                        val images = node.childNodes
                        var image = 0
                        while (image < images.length) {
                            if (images.item(image).nodeName == "image") {
                                coverPage.add(Image(images.item(image)))
                            }
                            image++
                        }
                    }
                    "annotation" -> annotation = Annotation(node)
                    "date" -> date = node.textContent
                    "author" -> authors.add(Person(node))
                    "translator" -> translators.add(Person(node))
                    "keywords" -> keywords.add(node.textContent)
                    "book-title" -> bookTitle = node.textContent
                    "lang" -> lang = node.textContent
                    "src-lang" -> srcLang = node.textContent
                }
            }
            this.keywords = keywords.toList()
            this.coverPage = coverPage
            this.translators = translators
            this.authors = authors
        }
    }
}