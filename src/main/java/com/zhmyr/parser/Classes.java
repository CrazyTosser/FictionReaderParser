package com.zhmyr.parser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.List;

class Image {
    private String type;
    private String href;
    private String alt;

    public Image(Node item) {
        NamedNodeMap attr = item.getAttributes();
        this.type = attr.getNamedItem("type").getNodeValue();
        this.href = attr.getNamedItem("href").getNodeValue();
        this.alt = attr.getNamedItem("alt").getNodeValue();
    }

    public String getType() {
        return type;
    }

    public String getHref() {
        return href;
    }

    public String getAlt() {
        return alt;
    }
}

class Section {
    private List<String> text;
    private List<Section> subSection;

    public List<String> getText() {
        return text;
    }

    public boolean addText(String text) {
        return this.text.add(text);
    }

    public List<Section> getSubSection() {
        return subSection;
    }

    public boolean addSubSection(Section subSection) {
        return this.subSection.add(subSection);
    }

}

class Header {
    private String author;
    private String annotation;
    private String title;

    Header(String author, String annotation, String title) {
        this.author = author;
        this.annotation = annotation;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAnnotation() {
        return annotation;
    }

    public String getTitle() {
        return title;
    }
}

