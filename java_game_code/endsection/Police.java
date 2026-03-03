package java_game_code.endsection;

/*
Create Police class
- Adam Palmer object is defined in this class’s constructor.
- A supporter function is created which returns whether the
detective could catch the criminals or not.
- The main function uses the supporter function to print out the
appropriate text when Rob and Bobby are caught and it prints
out another text when the detective can’t catch them.
Fields:
- Detective adamPalmer
Functions:
- areCriminalsCaught – Uses Random class to generate a
random numberbetween 0 and 100. If the random number is
greater than or equals to the Detective’s
SUCCESS_PERCENTAGE it returns false. Anyways it returns
true.
- catchCriminals – takes a gang object as parameter and using
the areCriminals in an if-statement
o If areCriminals returns true:
1. Prints out the following:
detective’s name + “ managed to catch the gang.”
2. If the gang’s stole value is greater than zero it prints
out the following:
“The solen items are recovered. Their overall value is
estimated to $” + sumRobbedValue from the gang
object
3. If the stolen value is zero it prints out the following:
“The gang couldn’t steal anything.”
4. Returns true
o If areCriminals returns false:
1. Prints out that:
detective’s name + “ managed to catch the gang.”
2. Also, the following:
“They managed to steal items valued $” +
sumRobbedValue from the gang object
3. Returns false
 */

public class Police {
    private Detective adamPalmer;

    public Police() {
        adamPalmer = new Detective("Adam Palmer", "Detective", 1975, "Investigating", new Item[]{new Item("Badge", 50.0)});
    }

    public boolean areCriminalsCaught() {
        int randomNumber = (int) (Math.random() * 100);
        return randomNumber >= adamPalmer.getSuccessPercentage();
    }

    public boolean catchCriminals(Gang gang) {
        if (areCriminalsCaught()) {
            System.out.println(adamPalmer.getName() + " managed to catch the gang.");
            double sumRobbedValue = gang.getSumRobbedValue();
            if (sumRobbedValue > 0) {
                System.out.println("The stolen items are recovered. Their overall value is estimated to $" + sumRobbedValue);
            } else {
                System.out.println("The gang couldn’t steal anything.");
            }
            return true;
        } else {
            System.out.println(adamPalmer.getName() + " failed to catch the gang.");
            System.out.println("They managed to steal items valued at $" + gang.getSumRobbedValue());
            return false;
        }
    }
}
