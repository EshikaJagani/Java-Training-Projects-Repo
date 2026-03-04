package com.intermediate;

/*
Create a Person class.
- the class should be abstract – it’s a base class for Criminal and
Detective class
- Don’t forget the constructor
Fields:
- String name
- String nickname
- int yearOfBorn
- String expertIn
- Item array (hint: Item[] items)
Functions:
- printBioData – prints out all the fields(name, nickname,
yearOfBorn, expertIn and items – use for loop to print out the
items)
- getName – returns the name of the person
- getNickname – returns the nickname of the person
 */

public class Person {
    public static final int SUCCESS_PERCENTAGE = 0;
    private String name;
    private String nickname;
    private int yearOfBorn;
    private String expertIn;
    private Item[] items;

    public Person(String name, String nickname, int yearOfBorn, String expertIn, Item[] items) {
        this.name = name;
        this.nickname = nickname;
        this.yearOfBorn = yearOfBorn;
        this.expertIn = expertIn;
        this.items = items;
    }

    public void printBioData() {
        System.out.println("Name: " + name);
        System.out.println("Nickname: " + nickname);
        System.out.println("Year of Born: " + yearOfBorn);
        System.out.println("Expert In: " + expertIn);
        System.out.println("Items:");
        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }
}


