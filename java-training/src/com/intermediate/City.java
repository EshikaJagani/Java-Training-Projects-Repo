package com.intermediate;

/*
Create a City class
- This class will store the buildings in an array(Bank, Mansion,
Post Office and Supermarket)
- Create an array of buildings with 4 element is the constructor
(public City()).
Fields:
- Buildings array (hint: Building[] building = new Building[4];)
Create the following building objects with the following items
in them:
o Bank
 Letter opener - $1.5
 Stamp - $2.5
o Mansion
 Pair of fancy shoes - $25
 Broken glass – $0.1
o Post Office
 Letter to Jenny - $1.5
 Pencil - $2.0
o Supermarket
 A loaf of bread - $2.5
 A bag of tea - $6.5
Functions:
- getBuildings – returns the array of buildings
 */

public class City {

    private Building[] buildings;

    public City() {
        buildings = new Building[4];
        buildings[0] = new Building("Bank", new Item[]{new Item("Letter opener", 1.5), new Item("Stamp", 2.5)});
        buildings[1] = new Building("Mansion", new Item[]{new Item("Pair of fancy shoes", 25.0), new Item("Broken glass", 0.1)});
        buildings[2] = new Building("Post Office", new Item[]{new Item("Letter to Jenny", 1.5), new Item("Pencil", 2.0)});
        buildings[3] = new Building("Supermarket", new Item[]{new Item("A loaf of bread", 2.5), new Item("A bag of tea", 6.5)});
    }

    public Building[] getBuildings() {
        return buildings;
    }

}