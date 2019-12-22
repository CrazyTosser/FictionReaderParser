package com.zhmyr.parser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Map;

class Body {
    private String title;
    private Image img;
    private List<Section> mSection;
    private Map<String, String> notes;

    public Body(Node item) {
        NodeList child = item.getChildNodes();
        for (int i = 0; i < child.getLength(); i++) {
            switch (child.item(i).getNodeName()) {
                case "image":
                    if (img == null) img = new Image(child.item(i));
                    break;
                case "title":
                    if (title == null) title = child.item(i).getTextContent();
                    break;
                case "epigraph":

            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Section> getSection() {
        return mSection;
    }

    public Map<String, String> getNotes() {
        return notes;
    }

    public boolean addSection(Section section) {
        return this.mSection.add(section);
    }

    public String addNote(String key, String value) {
        return this.notes.put(key, value);
    }
}