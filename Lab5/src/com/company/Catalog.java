package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private List<Document> documents;

    public Catalog() {
        this.documents = new ArrayList<>();
    }

    public void AddDocument(Document document) {
        this.documents.add(document);
    }

    public void DeleteDocument(Document document) {
        this.documents.remove(document);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "documents=" + documents +
                '}';
    }
}
