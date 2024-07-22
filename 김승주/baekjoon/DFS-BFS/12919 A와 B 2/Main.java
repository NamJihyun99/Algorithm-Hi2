// https://making-a-scene.notion.site/12919-A-B-2-1cd49769040d40a6a7a75e37c46c3a68?pvs=4

import java.io.*;

class Main {
    static String S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        String T = br.readLine();

        dfs(new StringBuilder(T));
        System.out.println(0);
    }

    private static void dfs(StringBuilder currString) {
        if (currString.length() == S.length()) {
            if (currString.toString().equals(S)) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if (currString.charAt(currString.length() - 1) == 'A') {
            StringBuilder next1 = new StringBuilder(currString).deleteCharAt(currString.length() - 1);
            dfs(next1);
        } 
        if (currString.charAt(0) == 'B') {
            StringBuilder next2 = new StringBuilder(currString).deleteCharAt(0).reverse();
            dfs(next2);
        }
    }
}