package com.intermediate;

/*
Create TheGreatRobberyApp class
- The following objects are needed in the main method:
City
Gang
Police
- The gang info is printed out.
- The gang’s letsRob function is used with the city’s
getBuildings function as parameter.
- Then the police object’s catch criminals function is called with
the gang object as parameter.
- A do-while loop is needed to run the letsRob function while
the gang isn’t caught (hint: the opposite of the catch criminals
function’s returned value is needed in the while condition)
 */

public class TheGreatRobberyApp {
    public static void main(String[] args) {
        City city = new City();
        Gang gang = new Gang();
        Police police = new Police();

        gang.getGangInfo();
        gang.letsRob(city.getBuildings());

        do {
            if (police.catchCriminals(gang)) {
                break;
            }
            gang.letsRob(city.getBuildings());
        } while (true);
    }
}
