package Coding_Assessments;
// This question was asked in the coding assessment by Silverspace Inc. on GrorillaTest
public class GroceryList {
    public static void main(String args[]) {
        
      String[] groceryList = new String[]{"oranges 2 1", "apple 3 5"};
      String result = new String();
      int total = 0;
      
      for(int i = 0; i < groceryList.length; i++) {
          int temp = 1;
          char[] charArr = groceryList[i].toCharArray();
          for(int j = 0; j < groceryList[i].length(); j++) {
              if (Character.isDigit(charArr[j])) {
                  temp *= Character.getNumericValue(charArr[j]);
              }
          }
          total += temp;
          result += temp + " + ";
      }
      result += " = " + total;
      System.out.println(result);
    }
}
