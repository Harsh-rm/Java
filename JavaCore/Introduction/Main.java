import JavaCore.Introduction.Enums;

import java.util.EnumSet;

enum Coins {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    Coins(int value) { this.value = value; }

    private final int value;

    public int value() { return value; }
}

public class Main {
    public static void main(String[] args) {
        
        // This is an example of the class Enums
        // System.out.println("Workdays: ");
        // for(Enums day: EnumSet.range(Enums.MONDAY, Enums.FRIDAY))
        //     System.out.printf("%s ",day);
        // System.out.println();

        for (Coins c : Coins.values()) {
            CoinColors coinColor = CoinColors.color(c);
            System.out.println(c  + ":   	"+ c.value() +"¢ 	" + coinColor);
        }
    }

    public enum CoinColors {
        COPPER, NICKEL, SILVER;

        public static CoinColors color(Coins c) {
            switch(c){
                case PENNY: return CoinColors.COPPER;
                case NICKEL: return CoinColors.NICKEL;
                case DIME: 
                case QUARTER: return CoinColors.SILVER;
                default: throw new AssertionError("Unknown Coin: " + c);
            }
        }
    }
}