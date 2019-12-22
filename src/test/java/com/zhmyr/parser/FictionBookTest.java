package com.zhmyr.parser;

import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

public class FictionBookTest {
    @Test
    public void isBook() {
        FB2Api fb2 =
                null;
        try {
            fb2 = new FB2Api("/home/zhmyr/AndroidStudioProjects/FictionBookReader/parser/files/Gibson_Gibson_Uilyam._Sborniki_Graf_Nol._Mona_Liza_overdrayv.550048.fb2");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

    }
}