package com.company;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        Document d1 = new Document(1, "a book");
        Document d2 = new Document(2, "an article");
        Document d3 = new Document(3, "another book");

        try {
            d1.setLink(new URI("https://profs.info.uaic.ro/~acf/java/labs/lab_05.html"));
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }

        d1.setPath("G:\\Facultate\\An 3\\Sem 2\\Java\\Lab5\\catalogHolder.txt");

        Catalog catalog = new Catalog();
        catalog.AddDocument(d1);
        catalog.AddDocument(d2);
        catalog.AddDocument(d3);

        ExternalFilesHandler handler = new ExternalFilesHandler();
        handler.view(d1);
        handler.save(catalog);

        catalog.DeleteDocument(d1);
        catalog.DeleteDocument(d2);
        catalog.DeleteDocument(d3);

        catalog = handler.load();
        System.out.println(catalog);
    }
}
