package JavaCore.Introduction;

public class IntegerSize {
    public static void main(String[] args) {
        // Range of Integer word length- -2^31 to 2^31-1 (-2147483648 to 2147483647)
        int max =  2147483647;
        int min = -2147483648;
        
        // System.out.println(min + min);
        // System.out.println(max + min);
        // System.out.println(max + max);

        System.out.println(Integer.max(max, min));

        // System.out.println(Integer.MIN_VALUE);
        // System.out.println(Integer.MAX_VALUE);
    }
}