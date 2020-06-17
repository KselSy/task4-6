package com.company;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String test1 = essay(10,7,"hello my name is Bessie and this is my essay");
        ArrayList<String> test2 = split("((()))(())()()(()())");
        String test = overTime(new double[]{9, 17, 30, 1.5});
        System.out.println(test);
        System.out.println();
        for (String s : test2) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public static String essay(int n, int k, String str) {
        String[] subStr = str.split(" ");
        if (subStr.length>n){return "error";}
        String result = "";
        int tmp = 0;
        for(int i = 0; i<subStr.length; i++){
            if(tmp+subStr[i].length()<=k){
                result += subStr[i] + " ";
                tmp += subStr[i].length();
            }
            else{
                result += "\n";
                tmp = 0;
                i--;
            }
        }
        return result;
    }

    public static ArrayList<String> split(String str) {
        int open = 0;
        int closed = 0;
        ArrayList<String> cluster = new ArrayList<String>();
        for (int j = 0; j < str.length(); ) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(')
                    open++;
                if (str.charAt(i) == ')')
                    closed++;
                if (open == closed) {
                    cluster.add(str.substring(j, i + 1));
                    open = 0;
                    closed = 0;
                    j = i + 1;
                }
            }
        }
        return cluster;
    }

    public static String toCamelCase(String str) {
        char current = '\u0000';
        char prev = '\u0000';

        StringBuilder camel = new StringBuilder();
        boolean nextUp = true;
        boolean firstLow = false;

        for (int i = 0; i < str.length(); i++) {
            current = str.charAt(i);

            if (!Character.isLetterOrDigit(current) || (
                    ((Character.isLetter(prev) && Character.isLowerCase(prev)) || Character.isDigit(prev)) &&
                            Character.isLetter(current) && Character.isUpperCase(current))) {
                nextUp = true;
                if (!Character.isLetterOrDigit(current)) {
                    prev = current;
                    continue;
                }
            }
            if (nextUp && firstLow) {
                camel.append(Character.toUpperCase(current));
            } else {
                camel.append(Character.toLowerCase(current));
            }
            nextUp = false;
            prev = current;
            firstLow = true;
        }
        return camel.toString();
    }

    public static String toSnakeCase(String str) {
        StringBuilder snake = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                if (Character.isUpperCase(c)) {
                    if (snake.length() > 0) {
                        snake.append("_");
                    }
                    snake.append(Character.toLowerCase(c));
                } else {
                    snake.append(c);
                }
            } else {
                snake.append("_");
            }
        }
        return snake.toString();
    }

    public static String overTime(double[] arr) {
        double start = arr[0];
        double end = arr[1];
        double rate = arr[2];
        double mpl = arr[3];
        double res;
        if (start >= 9 && end <= 17) {
            res = rate * (end - start);
        } else {
            res = rate * (17 - start) + (rate * (end - 17) * mpl);
        }
        DecimalFormat df = new DecimalFormat(".00");
        String result = "$" + df.format(res);
        return result;
    }

    public static String BMI(String weight, String height) {
        double weigh = Double.parseDouble(weight.substring(0, weight.indexOf(' ')));
        double heigh = Double.parseDouble(height.substring(0, height.indexOf(' ')));
        double res = 0;
        if (weight.endsWith("pounds")) {
            weigh = weigh / 2.205;
        }
        if (height.endsWith("inches")) {
            heigh = heigh / 39.37;
        }

        res = weigh / (heigh * heigh);

        String categ = "";
        if (res < 18.5) {
            categ = "Underweight";
        } else if (res >= 18.5 && res <= 24.9) {
            categ = "Normal weight";
        } else if (res >= 25) {
            categ = "Overweight";
        }
        DecimalFormat df = new DecimalFormat(".0");

        return String.valueOf(df.format(res)) + " " + categ;
    }

    public static int bugger(int n) {
        String num = String.valueOf(n);
        char[] chars = num.toCharArray();
        int[] numbers = new int[chars.length];
        int res = 1;

        for (int i = 0; i < chars.length; i++) {
            numbers[i] = Character.getNumericValue(chars[i]);
        }
        if (numbers.length == 1)
            return 0;
        else {
            int count = 0;
            int count1 = numbers.length;
            for (int i = 0; i <= count1; i++) {
                for (int j = 0; j < numbers.length; j++) {
                    res *= numbers[j];
                }
                if (String.valueOf(res).length() >= 1) {
                    count++;
                    chars = String.valueOf(res).toCharArray();
                    numbers = new int[chars.length];
                    for (int k = 0; k < numbers.length; k++)
                        numbers[k] = Character.getNumericValue((chars[k]));
                    res = 1;
                }
            }
            return count;
        }
    }

    public static String toStarShorthand(String str) {
        int count = 1;
        char c = '\u0000';

        try {
            c = str.charAt(0);
        } catch (Exception ex) {
            return "";
        }
        String res = "";

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != c) {
                if (count != 1)
                    res += c + "*" + Integer.toString(count);
                else
                    res += c;
                c = str.charAt(i);
                count = 1;
            } else count++;
        }
        if (count != 1)
            res += c + "*" + Integer.toString(count);
        else
            return res += c;
        return res;
    }

    public static boolean doesRhyme(String str1, String str2) {
        String end1 = str1.substring(str1.lastIndexOf(" ") + 1);
        String end2 = str2.substring(str2.lastIndexOf(" ") + 1);

        String glas = "aeiouyAEIOUY";
        String seq1 = "";
        String seq2 = "";

        for (int i = 0; i < end1.length(); i++) {
            if (glas.indexOf(end1.charAt(i)) != -1)
                seq1 += end1.charAt(i);
        }
        for (int i = 0; i < end2.length(); i++) {
            if (glas.indexOf(end2.charAt(i)) != -1)
                seq2 += end2.charAt(i);
        }

        seq1 = seq1.toLowerCase();
        seq2 = seq2.toLowerCase();

        if (seq1.equals(seq2))
            return true;
        else
            return false;
    }

    public static boolean trouble(long num1, long num2) {
        String str1 = Long.toString(num1);
        String str2 = Long.toString(num2);
        String buf1 = "";
        boolean flag = false;

        for (int i = 2; i < str1.length(); i++) {
            if (str1.charAt(i) == str1.charAt(i - 1) && str1.charAt(i) == str1.charAt(i - 2))
                buf1 += str1.charAt(i);
        }
        try {
            for (int i = 1; i < str2.length(); i++) {
                if (str2.charAt(i) == str2.charAt(i - 1) && str2.charAt(i) == buf1.charAt(0)) {
                    flag = true;
                    break;
                } else
                    flag = false;
            }
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    public static int countUniqueBooks(String str, char c) {
        int count = 1;
        int countChar = 0;
        int i = 0;
        for (int j = 2; j < str.length(); j++) {
            if (j == 2)
                i = 0;
            else
                i = j;
            if (str.charAt(i) == c) {
                countChar++;
                if (i == 0)
                    if (str.charAt(i + 1) == c)
                        countChar++;
            }
            if (countChar < 2 && countChar > 0) {
                if (str.charAt(j) != c && str.charAt(j) != str.charAt(j - 1))
                    count++;
            } else if (countChar == 0)
                continue;
            else
                countChar = 0;
        }
        if (count == 1)
            return 0;
        else
            return count;
    }
}
