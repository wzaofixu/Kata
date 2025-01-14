package com.src;

import java.util.Scanner;

public class Main {
   public static void main(String[] args) throws Exception {
      Scanner scanner = new Scanner(System.in);
      String expression = scanner.nextLine();
      String expressionZ = expression.replaceAll("\\s","");
      char[] chars = expressionZ.toCharArray();
      int countMathOp = 0;
      int position = 0;
      char mathOp = 0;
      for (int i = 0; i < chars.length ; i++) {
         if(chars[i] == '+'){
            countMathOp++;
            position = i;
            mathOp ='+';
         }
         if(chars[i] == '-'){
            countMathOp++;
            position = i;
            mathOp = '-';
         }
         if(chars[i] == '/'){
            countMathOp++;
            position = i;
            mathOp = '/';
         }
         if(chars[i] == '*'){
            countMathOp++;
            position = i;
            mathOp = '*';
         }
      }
      if (countMathOp==0){
         throw new Exception("строка не является математической операцией");
      }
      if (countMathOp>1){
         throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
      }
      StringBuilder number1 = new StringBuilder();
      StringBuilder number2 = new StringBuilder();
      for (int i = 0; i < position; i++) {
         number1.append(chars[i]);
      }
      for (int i = position+1; i < chars.length; i++) {
         number2.append(chars[i]);
      }
      System.out.println(calculation(number1.toString(),number2.toString(),mathOp));
   }
   public static boolean isArab(String string){
      try {
         Integer.parseInt(string);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }
   public static String calculation(String number1, String number2, char mathOp) throws Exception {
      String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
              "XXI", "XXII", "XXIII" , "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
              "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
              "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
              "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
              "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
              "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
      };
      String result = "";
      if((!isArab(number1)&isArab(number2))||(isArab(number1)&!isArab(number2))){
         throw new Exception("используются одновременно разные системы счисления");
      }
      else if(((isArab(number1)) && (isArab(number2))) &&( (Integer.parseInt(number1)<0)|(Integer.parseInt(number1)>10)|(Integer.parseInt(number2)<0) | (Integer.parseInt(number2)>10))){
         throw new Exception("Только числа от 1 до 10");
      }
      else if(isArab(number1) && isArab(number2)){
         switch (mathOp) {
            case '+' -> result =result+(Integer.parseInt(number1)+Integer.parseInt(number2));
            case '-' -> result =result+(Integer.parseInt(number1)-Integer.parseInt(number2));
            case '/' -> result =result+(Integer.parseInt(number1)/Integer.parseInt(number2));
            case '*' -> result =result+(Integer.parseInt(number1)*Integer.parseInt(number2));
         }
      }
      else if (!isArab(number1) && !isArab(number2)) {
         switch (mathOp) {
            case '+' -> result =result+((roman[toArabic(number1) + toArabic(number2)]));
            case '-' -> {
               if(toArabic(number1)<toArabic(number2)){
                  throw new Exception("В римской системе нет отрицательных чисел");
               }
               result =result+((roman[toArabic(number1) - toArabic(number2)]));
            }
            case '/' -> {
               if (toArabic(number1) < toArabic(number2)) {
                  throw new Exception("В римской системе нет 0.");
               }
               result =result+((roman[toArabic(number1) / toArabic(number2)]));
            }
            case '*' -> result =result+((roman[toArabic(number1) * toArabic(number2)]));
         }
      }
      return result;
   }
   private static int toArabic (String number) throws Exception {
      return switch (number) {
         case "I" -> 1;
         case "II" -> 2;
         case "III" -> 3;
         case "IV" -> 4;
         case "V" -> 5;
         case "VI" -> 6;
         case "VII" -> 7;
         case "VIII" -> 8;
         case "IX" -> 9;
         case "X" -> 10;
         default -> throw new Exception("Только числа от 1 до 10");
      };
   }
}