package com.company;

import java.io.Serializable;
import java.net.URI;
import java.util.Map;

public class Document implements Serializable{
    private int Id;
    private String name;
    private Map<String, String> tags;
    private String path;
    private URI link;

    public Document(int id, String name) {
        Id = id;
        this.name = name;
    }

    public void setLink(URI link) {
        this.link = link;
    }

    public URI getLink() {
        return link;
    }

    public void setPath(String location) {
        this.path = location;
    }

    public String getPath() {
        return path;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return name;
    }
}
