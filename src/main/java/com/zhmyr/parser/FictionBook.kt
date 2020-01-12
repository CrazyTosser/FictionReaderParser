package com.zhmyr.parser

import org.w3c.dom.Document
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.*
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

data class Xmlns(private val node: Node) {
    val name: String = node.nodeName ?: ""
    var value: String = node.nodeValue ?: ""

}

class FictionBook(inputStream: InputStream) {
    val xmlns = mutableListOf<Xmlns>()
    var description: Description
        protected set
    protected val bodies = mutableListOf<Body>()
    val binaries = mutableMapOf<String, Binary>()
    var encoding = "utf-8"

    constructor(file: File) : this(FileInputStream(file))

    init {
        val dbf =
            DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        val doc =
            db.parse(InputSource(InputStreamReader(inputStream, encoding) as Reader))
        initXmlns(doc)
        val binary = doc.getElementsByTagName("binary")
        for (item in 0 until binary.length) {
            val binary1 = Binary(binary.item(item))
            if (binary1.id != null)
                binaries[binary1.id!!] = binary1
        }
        description = Description(doc)
        description.titleInfo?.coverPage?.forEach {
            it.value = (binaries.getOrDefault(it.value?.replace("#", "") ?: "", "") as Binary).binary
        }
        val bodyNodes = doc.getElementsByTagName("body")
        for (item in 0 until bodyNodes.length) {
            bodies.add(Body(bodyNodes.item(item)))
        }
    }

    protected fun setXmlns(nodeList: ArrayList<Node?>) {
        for (index in nodeList.indices) {
            val node = nodeList[index]
            xmlns += node?.let { Xmlns(it) } ?: continue
        }
    }

    protected fun initXmlns(doc: Document) {
        val fictionBook = doc.getElementsByTagName("FictionBook")
        val xmlns = ArrayList<Node?>()
        for (item in 0 until fictionBook.length) {
            val map = fictionBook.item(item).attributes
            for (index in 0 until map.length) {
                val node = map.item(index)
                xmlns.add(node)
            }
        }
        setXmlns(xmlns)
    }

    val body: Body
        get() = getBody(null)

    val notes: Body
        get() = getBody("notes")

    val comments: Body
        get() = getBody("comments")

    private fun getBody(name: String?): Body {
        for (body in bodies) {
            if (name + "" == body.name + "") {
                return body
            }
        }
        return bodies[0]
    }

    companion object {
        fun prepare(openInputStream: InputStream?, path: File):FileInputStream{
            val text = openInputStream?.readBytes()
            File(path.absolutePath + "/file").writeBytes(text!!)
            return FileInputStream(File(path.absolutePath + "/file"))
        }
    }
}