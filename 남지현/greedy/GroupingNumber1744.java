import java.util.*;
import java.io.*;

// 백준 1744번 수 묶기
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(numbers);
        int answer = 0;
        int left = 0, right = N-1;
        if (left < right) {
            while (right >= 1 && numbers[right] > 1) {
                if (numbers[right-1] <= 1) {
                    break;
                }
                answer += numbers[right-1]*numbers[right];
                right -= 2;
            }
            while (left <= N-2 && numbers[left] <= 0) {
                if (numbers[left+1] > 0) {
                    break;
                }
                answer += numbers[left]*numbers[left+1];
                left += 2;
            }
        }
        while (left <= right) {
            if (left == right) {
                answer += numbers[left];
                break;
            }
            int mul = numbers[left]*numbers[left+1];
            int sum = numbers[left]+numbers[left+1];
            if (mul > sum) {
                answer += mul;
                left += 2;
            } else {
                answer += numbers[left];
                left += 1;
            }
        }
        System.out.println(answer);
    }
}
