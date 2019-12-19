package com.zhmyr.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

class Author implements Serializable {
    @JacksonXmlProperty(localName = "first-name")
    public String fName;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @JacksonXmlProperty(localName = "middle-name")
    public String mName;

    @JacksonXmlProperty(localName = "last-name")
    public String lName;
}