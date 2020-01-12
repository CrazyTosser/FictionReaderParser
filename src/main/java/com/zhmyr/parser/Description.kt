package com.zhmyr.parser

import org.w3c.dom.Document

class Description(doc: Document?) {
    var titleInfo= doc?.let { TitleInfo(it, "title-info") }
        protected set
    var srcTitleInfo: SrcTitleInfo? = SrcTitleInfo(doc, "src-title-info")
        protected set

}