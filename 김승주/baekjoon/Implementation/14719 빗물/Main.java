import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        st.nextToken(); // H 값은 쓸 데가 없다...
        int W = Integer.parseInt(st.nextToken());
        int[] blocks = new int[W];
        
        int maxBlock = -1;
        int maxBlockIdx = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
            if (blocks[i] > maxBlock) {
                maxBlock = blocks[i];
                maxBlockIdx = i;
            }
        }

        boolean flag = false; // 끝 부분이 막혀있는지 여부
        int init = 0;
        int sum = 0;
        int temp = 0;
        
        // 최대 높이의 블록이 나올 때까지는 왼쪽에서 오른쪽으로 탐색
        for (int i = 0; i <= maxBlockIdx; i++) {
            if (flag) {
                if (blocks[i] >= blocks[init]) {
                    sum += temp;
                    init = i;
                    temp = 0;
                } else {
                    temp += (blocks[init] - blocks[i]);
                }
            } else {
                if (blocks[i] == 0) {
                    continue;
                }
                flag = true;
                init = i;
            }
        }

        flag = false;
        init = W - 1;
        temp = 0;
        // 오른쪽에서 왼쪽으로 최대 높이의 블록이 나올 때까지 탐색
        for (int i = W - 1; i >= maxBlockIdx; i--) {
            if (flag) {
                if (blocks[i] >= blocks[init]) {
                    sum += temp;
                    init = i;
                    temp = 0;
                } else {
                    temp += (blocks[init] - blocks[i]);
                }
            } else {
                if (blocks[i] == 0) {
                    continue;
                }
                flag = true;
                init = i;
            }
        }

        System.out.println(sum);
    }
}