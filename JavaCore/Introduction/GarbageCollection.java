package JavaCore.Introduction;

// import java.io.*;
// import java.util.*;

class Person {
    String name;
    
    Person(String name) {
        this.name = name;
    }
}

public class GarbageCollection {
    public static void main(String[] args) {
        Person person1 = new Person("Alice");
        Person person2 = new Person("Bob");

        // System.out.println(person1 + " " + person1.name);
        // System.out.println(person2 + " " + person2.name);
        
        person1 = person2; // person1 now refers to the same object as person2

        System.out.println(person1 + " " + person1.name);
        
        person2 = null; // person2 reference is set to null

        // At this point, the object originally referred to by person1 ("Alice") is no longer reachable
        // It becomes eligible for garbage collection
        try {
            System.out.println(person2 + " " + person2.name);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}