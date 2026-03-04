package com.intermediate;

/*
Create a Criminal class
- Extends the Person class
- A constructor will be needed where the super constructor is
called
Fields:
- int SUCCESS_PERCENTAGE – it’ll be used to decide whether
their mission is successful or not - it should be final and static
Functions:
- printBioData – override the original version and also use it
with super.printBioData(), before that print out the following
text: “Criminal person:”
 */

public class Criminal extends Person {

    private static final int SUCCESS_PERCENTAGE = 70;

    public Criminal(String name, String nickname, int yearOfBorn, String expertIn, Item[] items) {
        super(name, nickname, yearOfBorn, expertIn, items);
    }

    @Override
    public void printBioData() {
        System.out.println("Criminal person:");
        super.printBioData();
    }

}