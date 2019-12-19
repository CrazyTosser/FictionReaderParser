package com.zhmyr.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FictionBook {
    private String path;
    public Book book;
    public FictionBook(File file, String path) {
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                this.path = path + entry.getName();
                FileOutputStream fout = new FileOutputStream(this.path);
                byte[] buffer = new byte[1024];
                int c = 0;
                while ((c = zip.read(buffer)) > -1)
                    fout.write(buffer, 0, c);
                fout.flush();
                zip.closeEntry();
                fout.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        XmlMapper mapper = new XmlMapper();
        try {
            this.book = mapper.readValue(new String(Files.readAllBytes(Paths.get(this.path))), Book.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
