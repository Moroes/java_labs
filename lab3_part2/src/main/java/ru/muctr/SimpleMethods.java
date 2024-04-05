package ru.muctr;
import static java.lang.Math.max;

public class SimpleMethods {
    public static int[] maxNumbersOfArrays(int[] a, int[] b){
        int arrays_len = a.length;
        int[] result = new int[arrays_len];
        for (int i = 0; i < arrays_len; i++){
            result[i] = max(a[i], b[i]);
        }
        return result;
    }

    public boolean isHaveDigit(String word){
        boolean isHaveDigits = false;
        for(int i = 0; i < word.length(); i++) {
            if(Character.isDigit(word.charAt(i))) {
                isHaveDigits = true;
                break;
            }
        }
        return isHaveDigits;
    }
}
