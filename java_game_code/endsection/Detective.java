package java_game_code.endsection;

/*
Create a Detective class
- Extends the Person class
- A constructor will be needed where the super constructor is
called
Fields:
- int SUCCESS_PERCENTAGE – it’ll be used to decide whether
the detective can catch the criminals or not - it should be final
and static
Functions:
- printBioData – override the original version and also use it
with super.printBioData(), before that print out the following
text: “Detective:”
 */

public class Detective extends Person {

    private static final int SUCCESS_PERCENTAGE = 80;

    public Detective(String name, String nickname, int yearOfBorn, String expertIn, Item[] items) {
        super(name, nickname, yearOfBorn, expertIn, items);
    }

    @Override
    public void printBioData() {
        System.out.println("Detective:");
        super.printBioData();
    }

    public int getSuccessPercentage() {
        return SUCCESS_PERCENTAGE;
    }

}