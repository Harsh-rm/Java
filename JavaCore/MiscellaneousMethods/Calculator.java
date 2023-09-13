package JavaCore.MiscellaneousMethods;

import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Double result = 0d; // why is 0d double type?

        try(Scanner in = new Scanner(System.in)) {

            while(true) {
                String input = in.nextLine();
                // System.out.println(input);

                if("exit".equals(input)) {
                    System.exit(0);
                } 

                else if(isNumeric(input)) {
                    // System.out.println("The input is Numeric!");
                    result = Double.parseDouble(input);
                }

                else {
                    Double temp = Double.parseDouble(in.nextLine());
                    switch(input) {
                        case "+": result += temp;
                        break;
                        case "-": result -= temp;
                        break;
                        case "*": result *= temp;
                        break;
                        case "/": result /= temp;
                        break;
                    }

                    System.out.println(result);
                }
            }
        }
    }

    private static boolean isNumeric(String input) {
        try {
            // Integer.parseInt(input);
            Double.parseDouble(input);
            return true;
        }
        catch(NumberFormatException e) {
            // if (!"exit".equals(input)) {
            //     System.out.println(e);
            // }
            // System.out.println("Please enter a numeric value: ");
            return false;
        }
    }
}