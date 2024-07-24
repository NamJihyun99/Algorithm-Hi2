// https://making-a-scene.notion.site/2493-84d1dfd0edad4ecba90edd2d6cf3ac3d?pvs=4

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> towers = new HashMap<>();
        Stack<Integer> s = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            int curr = Integer.parseInt(st.nextToken());
            towers.put(curr, i);

            while (true) {
                if (s.isEmpty()) {
                    sb.append(0).append(" ");
                    s.push(curr);
                    break;
                }

                int prev = s.peek();
                if (prev < curr) {
                    s.pop();
                } else {
                    sb.append(towers.get(prev)).append(" ");
                    s.push(curr);
                    break;
                }
            }
        }
        System.out.println(sb.toString());
    }
}