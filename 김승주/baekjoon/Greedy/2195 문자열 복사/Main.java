import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        char[] P = br.readLine().toCharArray();
        int count = 0;
        
        for (int i = 0; i < P.length;) {
            int maxLength = 0;
            for (int j = 0; j < S.length; j++) {
                if (P[i] != S[j])   continue;

                int currLength = 1;
                int a = i;
                int b = j;
                while (a + 1 < P.length && b + 1 < S.length && P[a + 1] == S[b + 1]) {
                    a++; b++; currLength++;
                }
                maxLength = Math.max(maxLength, currLength);
            }
            count++;
            i += maxLength;
        }
        System.out.println(count);
    }
}