package com.zhmyr.parser;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class FB2Api {
    private Document doc;

    private FB2 work = new FB2();

    public FB2Api(String fname) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        this.doc = builder.parse(new File(fname));
        this.doc.getDocumentElement().normalize();
        if (!this.doc.getDocumentElement().getNodeName().equals("FictionBook"))
            throw new IOException("Root node unclassified");
        parseHead();
        parseBody();
    }

    private void parseHead() throws XPathExpressionException, IOException {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expression = xpath.compile("/FictionBook/description/title-info/author");
        NodeList nodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
        if (nodes.getLength() != 1) throw new IOException("Incorrect fb2 file");
        nodes = nodes.item(0).getChildNodes();
        StringBuilder author = new StringBuilder();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeName().contains("name"))
                author.append(nodes.item(i).getTextContent()).append(" ");
        }
        expression = xpath.compile("/FictionBook/description/title-info/book-title/text()");
        nodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
        if (nodes.getLength() != 1) throw new IOException("Incorrect fb2 file");
        String title = nodes.item(0).getNodeValue();
        expression = xpath.compile("/FictionBook/description/title-info/annotation");
        nodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
        if (nodes.getLength() != 1) throw new IOException("Incorrect fb2 file");
        this.work.setHead(new Header(author.toString(), title, nodes.item(0).getTextContent()));
    }

    private void parseBody() throws XPathExpressionException {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expression = xpath.compile("/FictionBook/body[not(@name=\"notes\")]");
        this.work.setBody(new Body(((NodeList) expression.evaluate(doc, XPathConstants.NODESET)).item(0)));
    }

    public FB2 getBook() {
        return work;
    }
}
