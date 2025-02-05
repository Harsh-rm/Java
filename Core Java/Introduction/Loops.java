package JavaCore.Introduction;

import java.util.*;

public class Loops {
    public static void main(String[] args) {

        Loops loops = new Loops();
        // loops.forLoop();
        loops.whileLoop();
    }

    public void forLoop() {
        int x = 0;
        // pre-increment
        System.out.printf("Pre-increment: ");
        for (int i = 0; i < 5; ++i) {
            x += i;
            System.out.printf("%d\t", x);
        }
        System.out.println("\n");

        x = 0;
        // post-increment
        System.out.printf("Post-increment: ");
        for (int i = 0; i < 5; i++) {
            x += i;
            System.out.printf("%d\t", x);
        }
        System.out.println("\n");

        // increment by two - even/odd numbers
        System.out.printf("Print odd numbers up to 10: ");
        for (int i = 1; i <= 10; i += 2) {
            System.out.printf("%d\t", i);
        }
        System.out.println("\n");

        System.out.printf("decrement: ");
        for (int i = 5; i > 0; i--) {
            System.out.printf("%d\t", i);
        }
        System.out.println("\n");

        System.out.printf("Looping through an Array of Strings: ");
        List<String> names = Arrays.asList("Sri Hari", "Anurag", "Hitesh", "Arvind", "Adhiesh", "Yash");

        // for (int i = 0; i < names.size(); i++) {
        //     System.out.printf("%s\t", names.get(i));
        // }
        // System.out.println();

        for (String name: names) {
            System.out.printf("%s\t",name);
        }
        System.out.println();
    }

    public void whileLoop() {
        int i = 0;

        while (i < 5) {
            System.out.printf("%d\t", i);
            i++;
        }
        System.out.println();

        i = 0;
        while (true) {
            System.out.printf("%d\t", i);
            i++;
            if (i > 5) break;
        }
        System.out.println();

        List<String> friends = Arrays.asList("Sri Hari", "Anurag", "Hitesh", "Arvind", "Adhiesh", "Yash");
        Iterator<String> iter = friends.iterator();
        while (iter.hasNext()) {
            System.out.printf("%s\t",iter.next());
        }
        System.out.println();

        i = 0; 
        do {
            System.out.printf("%d\t",i);
            i++;
        } while (i < 5);
        System.out.println();
    }
}