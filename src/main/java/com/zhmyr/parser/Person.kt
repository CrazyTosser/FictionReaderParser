package com.zhmyr.parser

import org.w3c.dom.Node
import java.util.*

//http://www.fictionbook.org/index.php/Элемент_author
open class Person {
    var id: String? = null
    var firstName: String? = null
    var middleName: String? = null
    var lastName: String? = null
    var nickname: String? = null
    protected var homePages: List<String> = listOf()
    protected var emails: List<String> = listOf()

    constructor()
    internal constructor(node: Node) {
        val nodeList = node.childNodes
        val homePages = mutableListOf<String>()
        val emails = mutableListOf<String>()
        for (i in 0 until nodeList.length) {
            val author = nodeList.item(i)
            when (author.nodeName) {
                "id" -> id = author.textContent
                "home-page" -> homePages.add(author.textContent)
                "email" -> emails.add(author.textContent)
                "nickname" -> nickname = author.textContent
                "first-name" -> firstName = author.textContent
                "middle-name" -> middleName = author.textContent
                "last-name" -> lastName = author.textContent
            }
        }
        this.emails = emails
        this.homePages = homePages
    }

    val fullName: String
        get() = ((if (firstName == null) "" else "$firstName ")
                + (if (middleName == null) "" else "$middleName ")
                + if (lastName == null) "" else lastName)
}