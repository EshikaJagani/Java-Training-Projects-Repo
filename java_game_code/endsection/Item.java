package java_game_code.endsection;

/*
Create an Item class
- It’ll be used to store the items for Buildings and Persons
Fields:
- String name
- double value – the item’s value in dollars
Functions:
- getName – returns the name of the item
- getValue – returns the value of the item 
*/

public class Item {
    private String name;
    private double value;

    public Item(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    } 
}
