package com.company;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;

public class ExternalFilesHandler {
    public void save(Catalog catalog) {
        try {
            FileOutputStream f = new FileOutputStream(new File("catalogHolder2.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(catalog);

            o.close();
            f.close();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public Catalog load() {
        try {
            FileInputStream fi = new FileInputStream(new File("catalogHolder2.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            return (Catalog) oi.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void view(Document document) {
        if (document.getLink() != null) {
            try {
                Desktop.getDesktop().browse(document.getLink());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (document.getPath() != null) {
            try {
                Desktop.getDesktop().open(new File(document.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
