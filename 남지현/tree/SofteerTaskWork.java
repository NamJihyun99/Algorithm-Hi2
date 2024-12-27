import java.util.*;
import java.io.*;

// Softeer - [HSAT 5회 정기 코딩 인증평가 기출] 업무 처리
public class Main {

    static int sum, time;

    private static class Node {
        int idx;
        Node parent;
        ArrayDeque<Integer> left;
        ArrayDeque<Integer> right;

        Node(int idx) {
            this. idx = idx;
            this. parent = null;
            this.left = new ArrayDeque<>();
            this.right = new ArrayDeque<>();
        }

        void work() {
            int work = -1;
            if (time%2 == 0) {
                if (this.right.isEmpty()) return;
                work = this.right.pollFirst();
            }
            else {
                if (this.left.isEmpty()) return;
                work = this.left.pollFirst();
            }
            
            if (this.idx == 0) {
                sum += work;
            } else {
                if (this.isLeftChild()) {
                    this.parent.left.addLast(work);
                } else {
                    this.parent.right.addLast(work);
                }
            }
        }
        
        private boolean isLeftChild() {
            if (this.parent == null) return false;
            return (this.parent.idx+1)*2 != this.idx;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int H = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int L = (int) Math.pow(2, H); // 리프 노드의 개수 : 2^H
        int N = 2*L-1; // 총 노드의 개수 : 2^(H+1)-1
        // parent 노드 연결
        List<Node> CBT = new ArrayList<>(N);
        for (int i=0; i<N; i++) {
            CBT.add(new Node(i));
            if (i==0) continue;
            CBT.get(i).parent = CBT.get((i-1)/2);
        }
        // 리프노드 업무 분담
        for (int i=L-1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<K; j++) {
                if (j%2==0) 
                    CBT.get(i).left.addLast(Integer.parseInt(st.nextToken()));
                else 
                    CBT.get(i).right.addLast(Integer.parseInt(st.nextToken()));
            }
        }
        time = 1;
        for (; time<=R; time++) {
            for (int i=0; i<N; i++) {
                CBT.get(i).work();
            }
        }
        System.out.println(sum);
    }
}
