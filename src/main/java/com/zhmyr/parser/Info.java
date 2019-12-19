package com.zhmyr.parser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
class Info {
    @JacksonXmlProperty(localName = "author")
    private Author author;

    @JacksonXmlProperty(localName = "book-title")
    private String bookTitle;

    public Info() {
        super();
    }

    public Info(Author author, String bookTitle, String[] annotation) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.annotation = annotation;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String[] getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String[] annotation) {
        this.annotation = annotation;
    }

    @JacksonXmlProperty(localName = "p")
    @JacksonXmlElementWrapper(localName = "annotation")
    public String[] annotation;
}