import java.util.*;

// 백준 2195번 - 문자열 복사

class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        String P = sc.next();
        int count = 0;
        int idx = 0;
        for (int i=0; i<P.length(); i++) {
            if (S.indexOf(P.substring(idx, i+1)) != -1) continue;
            count++;
            idx = i;
        }
        System.out.println(count+1);
        sc.close();
    }
}
