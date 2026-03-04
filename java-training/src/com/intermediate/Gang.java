package com.intermediate;

/*
Create Gang class.
- It contains Rob and Bobby objects in an array. They are
defined in the constructor
- The letsRob function randomly chooses a building and based
on a supporter function (isSuccessfulRobbery) it displays
different texts
- The summary of the stole items’ value is stored in this class,
too.
Fields:
- Criminals array
- Random number generator
- double sumRobbedValue
Functions:
- getSumRobbedValue – returns the summarized value of the
robbed items
- getGangInfo – cycles through the criminals array in a for loop
and calls printBioData on the criminal objects
- isSuccessfulRobbery – in this function an int number is
generated between 0 and 100. The SUCCESS_PERCENTAGE is
multiplied by the number of criminals. If the random number
is smaller than the calculated success percentage, the
function returns true. Anyways, it returns false.
- letsRob – takes a building array as parameter – generates a
random number between zero and the length of the building
array.
Then the isSuccessfulRobbery function is used in an if
statement:
o If TRUE
1. Then the following text is printed out:
“The gang managed to rob the following items from the”
+ building name + “:”
2. After that a for loop summarizes the value of the items
in the building and adds it to the sumRobbedValue
3. Also the item names are printed out in the loop
o If FALSE
1. In the else block the following text is printed out:
“The gang tried to rob the” + building name + “but they
failed”
 */

public class Gang {
    private Person[] criminals;
    private double sumRobbedValue;

    public Gang() {
        criminals = new Person[2];
        criminals[0] = new Person("Rob", "The Robber", 1985, "Stealing", new Item[]{new Item("Knife", 10.0)});
        criminals[1] = new Person("Bobby", "The Burglar", 1990, "Breaking and Entering", new Item[]{new Item("Lockpick Set", 15.0)});
        sumRobbedValue = 0.0;
    }

    public double getSumRobbedValue() {
        return sumRobbedValue;
    }

    public void getGangInfo() {
        for (Person criminal : criminals) {
            criminal.printBioData();
        }
    }

    private boolean isSuccessfulRobbery() {
        int randomNumber = (int) (Math.random() * 100);
        int successPercentage = Person.SUCCESS_PERCENTAGE * criminals.length;
        return randomNumber < successPercentage;
    }

    public void letsRob(Building[] buildings) {
        int randomIndex = (int) (Math.random() * buildings.length);
        Building buildingToRob = buildings[randomIndex];

        if (isSuccessfulRobbery()) {
            System.out.println("The gang managed to rob the following items from the " + buildingToRob.getName() + ":");
            for (Item item : buildingToRob.getItems()) {
                sumRobbedValue += item.getValue();
                System.out.println("- " + item.getName());
            }
        } else {
            System.out.println("The gang tried to rob the " + buildingToRob.getName() + " but they failed.");
        }
    }
}
