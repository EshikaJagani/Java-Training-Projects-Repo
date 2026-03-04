package com.intermediate;

/*
Create a Building class
- This class will be used by criminals to steal items
Fields:
- String name
- Item array (hint: Item[] items)
Functions:
- getName – returns the name of the building
- getItems – returns the items array
 */

public class Building {
    private String name;
    private Item[] items;

    public Building(String name, Item[] items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public Item[] getItems() {
        return items;
    }
}