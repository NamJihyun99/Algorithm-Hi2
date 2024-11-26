import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> Li = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            Li.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(Li);
        Deque<Integer> deque = new ArrayDeque<>(Li);

        int count = 0;
        while (deque.size() >= 3) {
            int front = deque.pollFirst();
            if (front == 2) {
                deque.addFirst(1);
            }
            if (front > 2) {
                deque.addFirst(front - 2);
                deque.addFirst(1);
            }
            
            int rear = deque.pollLast();
            rear += deque.pollLast();
            deque.addLast(rear + 1);
            count++;
        }

        if (deque.size() == 2) {
            System.out.println(count + 1);
        } else {
            System.out.println(count);
        }
    }
}