package misc;

import java.util.Scanner;

/**
 * Created by chaitra.kr on 09/05/16.
 */
public class Assertions {

    public static void main( String args[] )
        {
//            Scanner input = new Scanner(System.in);
//            System.out.print( "Enter a number between 0 and 10: " );
//            int number = input.nextInt();
//            assert ( number >= 0 && number <= 10 ) : "bad number: " + number;
//            System.out.printf("You entered %d\n", number);

            Scanner input_day = new Scanner(System.in);
            System.out.println("Enter day number :");
            int day_input = input_day.nextInt();
            System.out.println("Day : "+dayOfWeek(day_input));
        }

    public static String dayOfWeek(int d){

        String day_of_week;

        switch(d%7){
            case 0 : day_of_week = "Sunday";
                break;
            case 1 : day_of_week = "Monday";
                break;
            case 2 : day_of_week = "Tuesday";
                break;
            case 3 : day_of_week = "Wednesday";
                break;
            case 4 : day_of_week = "Thursday";
                break;
            case 5 : day_of_week =  "Friday";
                break;

            default : assert d%7 == 6 : "Invalid day input";
                day_of_week = "Saturday";

        }

        return day_of_week;

    }

}
