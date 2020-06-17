package com.company;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        /*ArrayList <String> test =  sameVowelGroup(new String[] {"hoops", "chuff", "bot", "bottom"});
        for (String tmp: test){
            System.out.println(tmp);
        }*/
        //boolean test1 = validateCard (1234567890123456L);
        System.out.println(numToRus (24));

	// write your code here
    }
    public static int[] encrypt (String str) {
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (i == 0)
                arr[i] = (int) str.charAt(i);
            else
                arr[i] = (int) str.charAt(i) - (int) str.charAt(i - 1);
        }
        return arr;
    }

    public static String decrypt (int[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == 0)
                str += (char) arr[i];
            else
                str += (char) ((int) str.charAt(i - 1) + arr[i]);
        }
        return str;
    }

    public static boolean canMove (String figure, String loc, String pos) {
        boolean flag = false;
        char letterLoc = loc.charAt(0);
        int verLoc = Character.getNumericValue(loc.charAt(1));
        int gorLoc = 0;
        {
            if (letterLoc == 'A')
                gorLoc = 1;
            if (letterLoc == 'B')
                gorLoc = 2;
            if (letterLoc == 'C')
                gorLoc = 3;
            if (letterLoc == 'D')
                gorLoc = 4;
            if (letterLoc == 'E')
                gorLoc = 5;
            if (letterLoc == 'F')
                gorLoc = 6;
            if (letterLoc == 'G')
                gorLoc = 7;
            if (letterLoc == 'H')
                gorLoc = 8;
        }
        char letterPos = pos.charAt(0);
        int verPos = Character.getNumericValue(pos.charAt(1));
        int gorPos = 0;
        {
            if (letterPos == 'A')
                gorPos = 1;
            if (letterPos == 'B')
                gorPos = 2;
            if (letterPos == 'C')
                gorPos = 3;
            if (letterPos == 'D')
                gorPos = 4;
            if (letterPos == 'E')
                gorPos = 5;
            if (letterPos == 'F')
                gorPos = 6;
            if (letterPos == 'G')
                gorPos = 7;
            if (letterPos == 'H')
                gorPos = 8;
        }
        if (figure == "Knight") {
            if ((verPos == verLoc + 1 || verPos == verLoc - 1) &&
                    (gorPos == gorLoc - 2 || gorPos == gorLoc + 2))
                flag = true;
            else if ((gorPos == gorLoc + 1 || gorPos == gorLoc - 1) &&
                    (verPos == verLoc - 2 || verPos == verLoc + 2))
                flag = true;
            else
                flag = false;
        }
        if (figure == "Rook") {
            if (gorLoc == gorPos || verLoc == verPos)
                flag = true;
        }
        if (figure == "Pawn") {
            if (verLoc == 2 || verLoc == 7) {
                if (verLoc + 2 == verPos || verLoc + 1 == verPos && gorLoc == gorPos)
                    flag = true;
            }
            else if (verLoc + 1 == verPos && gorLoc == gorPos)
                flag = true;
        }
        if (figure == "Bishop") {
            if (Math.abs(gorLoc - gorPos) == Math.abs(verLoc - verPos))
                flag = true;
            else
                flag = false;
        }
        if (figure == "Queen") {
            if (verLoc == verPos || gorLoc == gorPos || Math.abs(verLoc - verPos) ==
                    Math.abs(gorLoc - gorPos))
                flag = true;
            else
                flag = false;
        }
        if (figure == "King") {
            if ((verLoc == verPos + 1 || verLoc == verPos - 1 || verLoc == verPos) &&
                    (gorLoc == gorPos + 1 || gorLoc == gorPos - 1 || gorLoc == gorPos))
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    public static boolean canComplete (String str1, String str2) {
        int count = 0;

        for (int i = 0; i < str1.length(); i++) {
            int i1 = str2.indexOf(str1.charAt(i));
            if (i == 0) {
                if (i1 != -1)
                    count++;
            }
            else {
                if (i1 != -1 && i1 <= str2.indexOf(str1.charAt(i - 1)))
                    continue;
                else
                if (i1 != -1)
                    count++;
            }
        }
        if (count == str1.length())
            return true;
        else
            return false;
    }

    public static int sumDigProd (int ... num) {
        int sum = 0;
        for (int value : num) {
            sum += value;
        }
        int mpl = 1;
        String numbers = Integer.toString(sum);
        char[] chars = numbers.toCharArray();
        for (int j = 0; j < numbers.length(); j++) {
            for (int i = 0; i < numbers.length(); i++) {
                mpl *= Character.getNumericValue(chars[i]);
            }
            if (mpl < 10)
                return mpl;
            else {
                numbers = Integer.toString(mpl);
                chars = numbers.toCharArray();
                mpl = 1;
            }
        }
        return mpl;
    }

    public static ArrayList <String> sameVowelGroup(String[] strings) {
        String glas = "aeiouyAEIOUY";
        String seq0 = "";
        String seq1 = "";

        ArrayList <String> words = new ArrayList<>();
        for (String string : strings) {
            words.add(string);
        }

        for (int i = 0; i < words.get(0).length(); i++) {
            if (glas.indexOf(words.get(0).charAt(i)) != -1)
                seq0 += words.get(0).charAt(i);
        }

        for (int i = words.size() - 1; i >= 0; i--) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (glas.indexOf(words.get(i).charAt(j)) != -1)
                    seq1 += words.get(i).charAt(j);
            }
            for (int k = 0; k < seq1.length();) {
                if (seq0.indexOf(seq1.charAt(k)) != -1)
                    k++;
                else {
                    words.remove(i);
                    seq1 = "";
                }
            }
        }
        return words;
    }

    public static boolean validateCard (long number) {
        ArrayList <Integer> arr = new ArrayList<>();
        String str = Long.toString(number);

        for (int i = 0; i < str.length(); i++) {
            arr.add(Character.getNumericValue(str.charAt(i)));
        }

        int control = arr.get(arr.size() - 1);
        arr.remove(arr.size() - 1);

        Collections.reverse(arr);
        int sum = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) % 2 != 0) {
                arr.set(i, arr.get(i) * 2);
                if (arr.get(i) > 9) {
                    str = arr.get(i).toString();
                    arr.set(i, Character.getNumericValue(str.charAt(0)) + Character.getNumericValue(str.charAt(1)));
                }
            }
            sum += arr.get(i);
        }
        str = Integer.toString(sum);

        return 10 - Character.getNumericValue(str.charAt(str.length() - 1)) == control;
    }

    public static String numToEng (int num) {
        String[] list1 = {"", "one ", "two ", "three ", "four ", "five ",
                "six ", "seven ", "eight ", "nine "};
        String[] list2 = {"", "eleven ", "twelve ", "thirteen ", "fourteen ",
                "fifteen ", "sixteen ", "seventeen ", "eighteen ", "nineteen "};
        String[] list3 = {"", "ten ", "twenty ", "thirty ", "forty ", "fifty ", "sixty ",
                "seventy ", "eighty ", "ninety "};

        String result = "";
        if (num == 0)
            return "ноль";
        else {
            if (num/100>0)
                result = list1[num / 100] + "hundred ";
            if (num % 100 >= 11 && num % 100 <= 19)
                result += list2[num % 10];
            else
                result += list3[num / 10 % 10] + list1[num % 10];
        }
        return result;
    }

    public static String numToRus (int num) {
        String[] list1 = {"", "один ", "два ", "три ", "четыре ", "пять ", "шесть ", "семь ", "восемь ", "девять "};
        String[] list2 = {"", "одиннадцать ", "двенадцать ", "тринадцать ", "четырнадцать ", "пятнадцать ", "шестнадцать ",
                "семнадцать ", "восемнадцать ", "девятнадцать "};
        String[] list3 = {"", "десять ", "двадцать ", "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ", "семьдесят ",
                "восемьдесят ", "девяносто "};
        String[] list4 = {"", "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ", "восемьсот ", "девятсот "};

        String result = "";

        if (num == 0)
            return "ноль";
        else {
            result = list4[num / 100];
            if (num % 100 >= 11 && num % 100 <= 19)
                result += list2[num % 100];
            else
                result += list3[num / 10 % 10] + list1[num % 10];
        }
        return result;
    }

    public static String getSha256Hash (String str) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static String correctTitle (String str) {
        String[] lower = {"and", "the", "of", "in"};
        String[] words = str.split("\\s");
        String result = "";

        for (int i = 0; i < lower.length; i++) {
            for (int j = 0; j < words.length; j++) {
                words[j] = words[j].toLowerCase();
                if (words[j].equals(lower[i]))
                    words[j] = words[j].toLowerCase();
                else {
                    words[j] = words[j].substring(0, 1).toUpperCase() + words[j].substring(1).toLowerCase();
                }
            }
        }
        for (int i = 0; i < words.length; i++)
            result += words[i] + " ";
        return result;
    }

    public static String hexLattice (int n) {
        String res = "";
        int min = 1;
        int max = 1;
        if ((3 + Math.sqrt(12 * n - 3)) % 1 == 0) {
            if (n == 1)
                res += "o";
            else {
                for (int i = 1; i < n;) {
                    min++;
                    max += 2;
                    i += 6 * min;
                }
            }
        }
        else
            return "Invalid";
        int max1 = max;
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < max;) {
            if (max > min) {
                String space = "";
                String circle = "";
                for (int j = 0; j < max - min; j++) {
                    space += " ";
                }
                for (int k = 0; k < min; k++) {
                    circle += "o ";
                }
                sb.append(space);
                sb.append(circle);
                sb.append('\n');
                min++;
                i++;
            }
            if (max == min && flag == true) {
                String space = "";
                String circle = "";
                for (int j = 0; j < min - max; j++) {
                    space += " ";
                }
                for (int k = 0; k < max; k++) {
                    circle += "o ";
                }
                sb.append(space);
                sb.append(circle);
                sb.append('\n');
                min++;
                flag = false;
                i++;
            }
            if (max < min) {
                max--;
                String space = "";
                String circle = "";
                for (int j = 0; j < min - max1; j++) {
                    space += " ";
                }
                for (int k = 0; k < max; k++) {
                    circle += "o ";
                }
                sb.append(space);
                sb.append(circle);
                sb.append('\n');
                min++;
            }
        }
        return sb.toString();
    }
}
