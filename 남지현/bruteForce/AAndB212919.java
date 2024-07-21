import java.util.*;
import java.io.*;

// 백준 12919 - A와 B 2 (골드5)

class Main {

    static String S, T;

    static boolean solve(String str) {
        if (str.length() == S.length()) {
            return S.equals(str.toString());
        }
        if (str.charAt(str.length()-1)=='A') {
            if (solve(str.substring(0, str.length()-1))) return true;
        } 
        if (str.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(str);
            if (solve(sb.deleteCharAt(0).reverse().toString())) return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        S = bf.readLine();
        T = bf.readLine();
        System.out.println(solve(T)? 1: 0);
    }
}
