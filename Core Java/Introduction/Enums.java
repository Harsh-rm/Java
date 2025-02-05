package JavaCore.Introduction;

//An Enum is a language construct that is used to define type-safe variables
// import java.lang.Enum;

// all enums implicitly extend java.lang.Enum
public enum Enums {
    //Days of the week
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
}

public enum Coins {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
}
public enum CoinColors {
    COPPER, NICKEL, SILVER;

    public static Coins color(Coins c) {
        switch(c){
            case PENNY: return CoinColors.COPPER;
            case NICKEL: return CoinColors.NICKEL;
            case DIME: 
            case QUARTER: return CoinColors.SILVER;
            default: throw new AssertionError("Unknown Coin: ", c);
        }
    }
}