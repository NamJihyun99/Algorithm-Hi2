import java.util.*;
import java.io.*;

// 백준 5014번 - 스타트링크

class Main {

    static final String UNABLE = "use the stairs";
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        if (S == G) {
            System.out.println(0);
            return;
        }
        int[] count = new int[F+1];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{S, 0});
        while (!queue.isEmpty()) {
            int[] node = queue.pollFirst();
            if (node[0] == G) {
                break;
            }
            int up = node[0] + U;
            if (up <= F && count[up] == 0) {
                count[up] = node[1]+1;
                queue.addLast(new int[]{up, count[up]});
            }
            int down = node[0] - D;
            if (down >= 1 && count[down] == 0) {
                count[down] = node[1]+1;
                queue.addLast(new int[]{down, count[down]});
            }
        }
        System.out.println(count[G] == 0 ? UNABLE : count[G]);
    }
}
