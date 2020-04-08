package com.company;

public class Main {

    public static void main(String[] args) {
        Database db = new Database("", "dba", "sql");

        ArtistController ac = new ArtistController();
        ac.setDbConnection(db.getDbConnection());
        ac.create("Alex", "Romania");
        ac.create("Bob", "UK");
        ac.create("Wiz", "USA");

        ac.findByName("Alex");


        AlbumController albC = new AlbumController();
        albC.create("This", 1, 2000);
        albC.create("This2", 1, 1999);
        albC.create("That", 2, 2010);
        albC.create("those", 3, 2001);

        albC.findByArtist(1);

        db.close();
    }
}
