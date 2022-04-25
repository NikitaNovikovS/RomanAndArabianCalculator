import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        //System.out.println(false == false);
        System.out.println(calc(input));

    }

    public static String calc(String input) {
        String result, str1, str2, operation;
        String[] arrayStr = input.split(" ");
        if (arrayStr.length < 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
                System.exit(0);
            }
        }
        if (arrayStr.length != 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System.exit(0);
            }
        }
        str1 = arrayStr[0].toUpperCase();
        str2 = arrayStr[2].toUpperCase();
        operation = arrayStr[1];
        if (!(StringUtils.containsOnly(operation, '+', '-', '*', '/'))) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Не верная операция!");
                System.exit(0);
            }
        }

        boolean isRom = isRome(str1, str2);

        if (isRom) {
            result = convertToRome(calculation(convertToArab(str1), convertToArab(str2), operation));
        } else {
            result = Integer.toString(calculation(Integer.parseInt(str1), Integer.parseInt(str2), operation));
        }
        return result;
    }

    private static String convertToRome(int input) {
        if (input < 1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисе");
                System.exit(0);
            }
        }
        StringBuilder result = new StringBuilder();
        for (RomainArab numeral : RomainArab.values()) {
            while (input >= numeral.arabic) {
                result.append(numeral.roman);
                input -= numeral.arabic;
            }
        }
        return result.toString();
    }

    private static int convertToArab(String input) {

        int fours = countMatches(input, "IV");
        int nines = countMatches(input, "IX");
        int fourtys = countMatches(input, "XL");
        int ninetys = countMatches(input, "XC");

        int ones = countMatches(input, "I") - fours - nines;
        int fives = countMatches(input, "V") * 5 - fours;
        int tens = countMatches(input, "X") * 10 - nines - fourtys * 10 - ninetys * 10;
        int fivetys = countMatches(input, "L") * 50 - fourtys * 10;
        int hundreds = countMatches(input, "C") * 100 - ninetys * 10;


        return ones + fives + tens + fivetys + hundreds;
    }

    private static int countMatches(String input, String i) {
        return (input.length() - input.replace(i, "").length()) / i.length();
    }

    static boolean isRome(String num1, String num2) {
        boolean isRome1 = StringUtils.containsOnly(num1.toUpperCase(), 'I', 'V', 'X', 'M');
        boolean isRome2 = StringUtils.containsOnly(num2.toUpperCase(), 'I', 'V', 'X', 'M');
        if (isRome1 != isRome2) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                System.exit(0);
            }
        }
        return isRome1;
    }

    static int calculation(int num1, int num2, String operation) {
        if (1 > num1 || num1 > 10 || 1 > num1 || num1 > 10) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Вы не ввели чило от 1 до 10!");
                System.exit(0);
            }
        }
        int result = 0;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }

    enum RomainArab {
        HUNDRED(100, "C"), NINETY(90, "XC"), FIVETY(50, "L"), FOURTY(40, "XL"), TEN(10, "X"), NINE(9, "IX"), FIVE(5, "V"), FOUR(4, "IV"), ONE(1, "I");
        private final int arabic;
        private final String roman;

        RomainArab(int arabic, String roman) {
            this.arabic = arabic;
            this.roman = roman;
        }
    }
}