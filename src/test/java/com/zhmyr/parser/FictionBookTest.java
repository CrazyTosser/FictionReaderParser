package com.zhmyr.parser;

import org.junit.Test;

import java.io.File;

public class FictionBookTest {
    @Test
    public void isBook() {
        FictionBook fb2 =
                new FictionBook(new File("/home/zhmyr/AndroidStudioProjects/FictionBookReader/parser/files/Nejro.zip"),
                "/home/zhmyr/AndroidStudioProjects/FictionBookReader/parser/files/");
        System.out.println(fb2.toString());

    }
}