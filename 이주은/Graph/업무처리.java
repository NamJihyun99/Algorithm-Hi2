import java.io.*;
import java.util.*;

public class Main {
    static int H, K, R;

    private static final class Node {
        Deque<Integer> left;
        Deque<Integer> right;

        Node() {
            this.left = new ArrayDeque<>();
            this.right = new ArrayDeque<>();
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        List<Node> tree = new ArrayList<>();

        int N = 1 << (H-1)+1;
        for(int i=0; i<=N; i++) {
            tree.add(new Node());
        }

        for(int i=(1<<H); i<(1<<H+1); i++) {
            Node parent = tree.get(i/2);
            Queue queue;
            
            //왼쪽 자식 노드일 경우
            if(i%2 == 0) {
                queue = parent.left;
            }
            //오른쪽 자식 노드일 경우
            else {
                queue = parent.right;
            }
            
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<K; j++) {
                int task = Integer.parseInt(st.nextToken());
                queue.add(task);
            }
        }

        for(int day=2; day<=R; day++) {
            for(int i=1; i<=N; i++) {
                Deque<Integer> parent;
                Deque<Integer> child;

                if(day%2 == 0) {
                    parent = tree.get(i/2).right;
                    child = tree.get(i).right;
                }
                else {
                    parent = tree.get(i/2).left;
                    child = tree.get(i).left;
                }

                if(i%2 == 0) {
                   parent = tree.get(i/2).left;
                }
                    
                else {
                   parent = tree.get(i/2).right;
                }

                if(!child.isEmpty()) {
                    parent.add(child.poll());
                }
            }
        }

        int answer = 0;
        
        Deque<Integer> done = tree.get(0).left;
        while(!done.isEmpty()) {
            answer += done.poll();
        }

        done = tree.get(0).right;
        while(!done.isEmpty()) {
            answer += done.poll();
        }

        System.out.println(answer);
    }
}
