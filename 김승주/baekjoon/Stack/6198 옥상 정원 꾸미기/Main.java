import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> buildingNum = new HashMap<>(); // key: 빌딩 높이, value: 빌딩 번호
        Stack<Integer> stack = new Stack<>();
        long sum = 0;

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            boolean wasPoped = true;
            while (wasPoped) {
                if (stack.isEmpty() || h < stack.peek()) {
                    buildingNum.put(h, i + 1);
                    stack.push(h);
                    wasPoped = false;
                } else {
                    sum += (i - buildingNum.get(stack.pop()));
                }
            }
        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            sum += (N - buildingNum.get(stack.pop()));
        }
        System.out.println(sum);
    }
}