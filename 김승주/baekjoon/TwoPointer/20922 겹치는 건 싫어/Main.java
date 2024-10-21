import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findLongestSubsequence(arr, N, K));
    }

    private static int findLongestSubsequence(int[] arr, int N, int K) {
        int maxLen = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;

        for (int right = 0; right < N; right++) {
            countMap.put(arr[right], countMap.getOrDefault(arr[right], 0) + 1);

            while (countMap.get(arr[right]) > K) {
                countMap.put(arr[left], countMap.get(arr[left]) - 1);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}