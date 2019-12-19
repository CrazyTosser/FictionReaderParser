package com.zhmyr.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "FictionBook")
public class Book implements Serializable {
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @JacksonXmlProperty(localName = "title-info")
    @JacksonXmlElementWrapper(localName = "description")
    public Info info;


}
