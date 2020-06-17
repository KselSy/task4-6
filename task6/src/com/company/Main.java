package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
    public static int bell (int n) {
        int[][] arr = new int[n + 1][n + 1];
        arr[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            arr[i][0] = arr[i - 1][i - 1];
            for (int j = 1; j <= i; j++)
                arr[i][j] = arr[i - 1][j - 1] + arr[i][j - 1];
        }
        return arr[n][0];
    }

    public static String translateWord (String word) {
        String vowels = "aeiouAEIOU";
        int index = -1;
        for (int i = 0; i < word.length(); i++) {
            if (vowels.contains(word.substring(i, i + 1))) {
                index = i;
                break;
            }
        }

        if (vowels.contains(word.substring(0, 1)))
            return word + "yay";

        if (index == -1)
            return "-1";

        return word.substring(index) + word.substring(0, index) + "ay";
    }

    public static String translateSentence (String str) {
        String[] sen = str.split("\\s");
        String result = "";

        for (int i = 0; i < sen.length; i++) {
            sen[i] = translateWord(sen[i]);
            for (int j = 0; j < sen[i].length(); j++) {
                if (!(Character.isLetter(sen[i].charAt(j))))
                    sen[i] = sen[i].substring(0, j) + sen[i].substring(j+1, sen[i].length()) + sen[i].substring(j, j+1);
            }
        }
        sen[0] = sen[0].toLowerCase();
        String first = sen[0].substring(0, 1).toUpperCase();
        sen[0] = first + sen[0].substring(1);
        for (int i = 0; i < sen.length; i++)
            result += sen[i] + " ";
        return result;
    }

    public static boolean validColor (String str) {
        String type = str.substring(0, str.indexOf("("));
        String args = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        String[] arg = args.split(",");
        boolean flag = false;
        if (type.equals("rgb")) {
            if (arg.length == 3) {
                for (int i = 0; i < arg.length; i++) {
                    if (arg[i].matches("[-+]?\\d+") && Integer.parseInt(arg[i]) >= 0 && Integer.parseInt(arg[i]) <= 255)
                        flag = true;
                    else {
                        flag = false;
                        break;
                    }
                }
            }
            else
                flag = false;
        }
        if (type.equals("rgba")) {
            if (arg.length == 4) {
                for (int i = 0; i < arg.length; i++) {
                    if (i == arg.length - 1) {
                        if (Double.parseDouble(arg[i]) >= 0 && Double.parseDouble(arg[i]) <= 1)
                            flag = true;
                        else {
                            flag = false;
                            break;
                        }
                    }
                    else {
                        if (Integer.parseInt(arg[i]) <= 0 && Integer.parseInt(arg[i]) <= 255) {
                            flag = true;
                        }
                        else {
                            flag = false;
                            break;
                        }
                    }
                    if (arg[i].matches("-?\\d+(\\.\\d+)?")) {
                        flag = true;
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
            }
            else
                flag = false;
        }
        return flag;
    }

    public static String stripUrlParams (String url, String ... args) {
        String str = "";
        if (url.indexOf("?") == -1)
            return url;
        else {
            str = url.substring(url.indexOf("?") + 1);
        }
        char[] params = str.toCharArray();

        String stripped = "";
        for (char param : params) {
            if (Character.isLetter(param))
                if (!(stripped.contains(String.valueOf(param)))) {
                    if (args.length > 0) {
                        for (String arg : args) {
                            if (!(arg.contains(String.valueOf(param))))
                                stripped += str.substring(str.lastIndexOf(param), str.lastIndexOf(param) + 3) + "&";
                        }
                    }
                    else
                        stripped += str.substring(str.lastIndexOf(param), str.lastIndexOf(param) + 3) + "&";
                }
        }
        stripped = stripped.replaceFirst(".$", "");

        return url.substring(0, url.indexOf("?") + 1) + stripped;
    }

    public static String[] getHashTags (String str) {
        int count = 0;
        int max = 0;
        int id = 0;
        String[] hash;
        List<String> words = new ArrayList<>(Arrays.asList(str.split(" ")));
        if (words.size() > 3)
            hash = new String[3];
        else
            hash = new String[words.size()];
        for (int k = 0; k < hash.length; k++) {
            for (int i = 0; i < words.size(); i++) {
                for (int j = 0; j < words.get(i).length(); j++) {
                    if (Character.isLetter(words.get(i).charAt(j)))
                        count++;
                    else
                        words.set(i, words.get(i).substring(0, words.get(i).length() - 1));
                }
                if (count > max) {
                    max = count;
                    id = i;
                }
                count = 0;
            }
            hash[k] = "#" + words.get(id).toLowerCase();
            words.remove(id);
            max = 0;
        }
        return hash;
    }

    public static int ulam (int n) {
        Vector<Integer> arr = new Vector<Integer>();
        Set<Integer> s = new HashSet<Integer>();

        arr.add(1);
        s.add(1);

        arr.add(2);
        s.add(2);

        for (int i = 3; i < 2000; i++) {
            int count = 0;
            for (int j = 0; j < arr.size(); j++) {
                if (s.contains(i - arr.get(j)) && arr.get(j) != (i - arr.get(j)))
                    count++;
                if (count > 2)
                    break;
            }
            if (count == 2) {
                arr.add(i);
                s.add(i);
            }
        }
        return arr.get((n - 1));
    }

    public static String longestNonrepeatingSubstring (String input) {
        Map<Character, Integer> visited = new HashMap<>();
        String output = "";
        for (int start = 0, end = 0; end < input.length(); end++) {
            char currChar = input.charAt(end);
            if (visited.containsKey(currChar)) {
                start = Math.max(visited.get(currChar)+1, start);
            }
            if (output.length() < end - start + 1) {
                output = input.substring(start, end + 1);
            }
            visited.put(currChar, end);
        }
        return output;
    }

    public static String convertToRoman (int num) {
        if (num < 1 || num > 3999)
            return "Invalid Roman Number Value";
        String roman = "";
        while (num >= 1000) {
            roman += "M";
            num -= 1000;        }
        while (num >= 900) {
            roman += "CM";
            num -= 900;
        }
        while (num >= 500) {
            roman += "D";
            num -= 500;
        }
        while (num >= 400) {
            roman += "CD";
            num -= 400;
        }
        while (num >= 100) {
            roman += "C";
            num -= 100;
        }
        while (num >= 90) {
            roman += "XC";
            num -= 90;
        }
        while (num >= 50) {
            roman += "L";
            num -= 50;
        }
        while (num >= 40) {
            roman += "XL";
            num -= 40;
        }
        while (num >= 10) {
            roman += "X";
            num -= 10;
        }
        while (num >= 9) {
            roman += "IX";
            num -= 9;
        }
        while (num >= 5) {
            roman += "V";
            num -= 5;
        }
        while (num >= 4) {
            roman += "IV";
            num -= 4;
        }
        while (num >= 1) {
            roman += "I";
            num -= 1;
        }
        return roman;
    }

    public static boolean formula (String expr) {
        String[] parts = expr.split(" = ");
        String[] numbers;
        double[] results = new double[parts.length];
        double res = 0;
        for (int i = 0; i < parts.length; i++) {
            res = 0;
            numbers = parts[i].split(" \\W ");
            if (parts[i].indexOf("*") != -1) {
                res += 1;
                for (int j = 0; j < numbers.length; j++) {
                    res *= Integer.parseInt(numbers[j]);
                }
            }
            else if (parts[i].indexOf("/") != -1) {
                for (int j = 0; j < numbers.length; j++) {
                    if (j == 0)
                        res = Integer.parseInt(numbers[0]);
                    else
                        res /= Integer.parseInt(numbers[j]);
                }
            }
            else if (parts[i].indexOf("+") != -1) {
                for (int j = 0; j < numbers.length; j++) {
                    res += Integer.parseInt(numbers[j]);
                }
            }
            else if (parts[i].indexOf("-") != -1) {
                for (int j = 0; j < numbers.length; j++) {
                    if (j == 0)
                        res = Integer.parseInt(numbers[0]);
                    else
                        res -= Integer.parseInt(numbers[j]);
                }
            }
            else
                res = Integer.parseInt(parts[i]);
            results[i] = res;
        }
        boolean flag = false;
        for (int k = 0; k < results.length; k++) {
            if (k != results.length - 1) {
                if (results[k + 1] == results[k])
                    flag = true;
                else
                    flag = false;
            }
            else
            if (results[k] == results[k - 1])
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    public static boolean palindromeDescendant (int num) {
        boolean isSym = false;
        boolean isSymmetrical = false;
        String number = String.valueOf(num);
        String reverse = "";

        while (num > 9) {
            String numbers = String.valueOf(num);
            reverse = "";
            for (int i = numbers.length() - 1; i >= 0; i--)
                reverse += numbers.substring(i, i + 1);
            if (numbers.equals(reverse)) {
                isSymmetrical = true;
            }
            else {
                isSymmetrical = false;
            }
            if (isSymmetrical) {
                isSym = true;
                break;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numbers.length() - 1; i += 2) {
                int i1 = Integer.parseInt(Character.toString(numbers.charAt(i)));
                int i2 = Integer.parseInt(Character.toString((numbers.charAt(i + 1))));
                int sum = i1 + i2;
                sb.append(Integer.toString(sum));
            }
            num = Integer.parseInt(sb.toString());
        }
        return isSym;
    }
}
