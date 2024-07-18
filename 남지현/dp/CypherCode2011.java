import java.util.*;
import java.io.*;

// 백준 2011 - 암호코드 (골드5)

class Main {

    static final int MOD = 1_000_000; 

    static boolean isPositiveDigit(String str, int idx) {
        return str.charAt(idx)>'0' && str.charAt(idx)<='9';
    }
    
    static boolean isAlpha(String str, int lastIdx) {
        int ten = str.charAt(lastIdx-1)-'0';
        int num = 10*ten+(str.charAt(lastIdx)-'0');
        return ten>0 && num>=1 && num<=26;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        int[] counts = new int[input.length()+1];
        counts[0]=1;
        for (int i=1; i<counts.length; i++) {
            if (isPositiveDigit(input, i-1)) {
                counts[i] += counts[i-1];
            }
            if (i>=2 && isAlpha(input, i-1)) {
                counts[i] += counts[i-2];
            }
            counts[i]%=MOD;
        }
        System.out.println(counts[input.length()]);
    }
}
