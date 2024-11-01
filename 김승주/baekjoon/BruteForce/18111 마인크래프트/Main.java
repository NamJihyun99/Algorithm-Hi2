import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] ground = new int[N * M];
        for (int i = 0, idx = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++, idx++) {
                ground[idx] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.sort(ground);
        int minSec = Integer.MAX_VALUE;
        int height = 0;
        for (int i = ground[ground.length - 1]; i >= ground[0]; i--) {
            int inventoryBlocks = B;
            int sec = 0;
            int j = ground.length - 1;
            for (; j >= 0; j--) {
                if (ground[j] < i) {
                    if (inventoryBlocks < i - ground[j]) {
                        break;
                    }
                    inventoryBlocks -= (i - ground[j]);
                    sec += (i - ground[j]);
                } else if (ground[j] > i) {
                    inventoryBlocks += (ground[j] - i);
                    sec += ((ground[j] - i) * 2);
                }
            }
            if (j == -1 && sec < minSec) {
                minSec = sec;
                height = i;
            } else if (sec >= minSec) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(minSec).append(" ").append(height);
        System.out.println(sb.toString());
    }
}