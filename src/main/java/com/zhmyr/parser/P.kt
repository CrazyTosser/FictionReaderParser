package com.zhmyr.parser

import com.zhmyr.parser.fonts.Emphasis
import com.zhmyr.parser.fonts.StrikeThrough
import com.zhmyr.parser.fonts.Strong
import org.w3c.dom.Node
import java.util.*

class Subtitle(p: Node?) : P(p!!)

open class P : Element {
    lateinit var images: List<Image>
        protected set
    lateinit var emphasis: List<Emphasis>
        protected set
    lateinit var strong: List<Strong>
        protected set
    lateinit var strikeThrough: List<StrikeThrough>
        protected set

    //    TODO
//    Для нижних индексов <sub>, а для верхних индексов <sup>
//    Программный код - <code>
//    <subtitle>* * *</subtitle>
//  <cite>
//  <p>Время - деньги.<p>
//  <text-author>Бенджамин Франклин</text-author>
//  </cite>
//  <p>Об этом вы можете прочитать <a l:href="#n1">здесь</a>.</p>
//  <p>text<a l:href="#n_2" type="note">[2]</a>
    constructor() : super()
    constructor(p: String?) : super(p)

    constructor(image: Image) : super() {
        images = listOf(image)
    }

    constructor(p: Node) : super(p) {
        val nodeList = p.childNodes

        val images = mutableListOf<Image>()
        val emphasis = mutableListOf<Emphasis>()
        val strong = mutableListOf<Strong>()
        val strikeThrough = mutableListOf<StrikeThrough>()
        for (index in 0 until nodeList.length) {
            val node = nodeList.item(index)
            when (nodeList.item(index).nodeName) {
                "image" -> images += Image(node)
                "strikethrough" -> strikeThrough  += StrikeThrough(node.textContent, p.textContent)
                "strong" ->  strong += Strong(node.textContent, p.textContent)
                "emphasis" -> emphasis += Emphasis(node.textContent, p.textContent)
                "subtitle" -> emphasis += Emphasis(node.textContent, p.textContent)
            }
        }
        this.images = images
        this.emphasis = emphasis
        this.strong = strong
        this.strikeThrough = strikeThrough
    }

    override fun toString(): String {
        return "<p>$text</p>"
    }
}