import java.util.*;
import java.io.*;

// 백준 2143번 두 배열의 합
class Main {

    static int T, A, B;
    static int[] sumA, sumB;

    private static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        int n = Integer.parseInt(br.readLine());
        A = n*(n+1)/2;
        sumA = new int[A];
        StringTokenizer st = new StringTokenizer(br.readLine());
        sumA[0] = Integer.parseInt(st.nextToken());
        for (int i=1; i<n; i++) {
            sumA[i] = sumA[i-1] + Integer.parseInt(st.nextToken());
        }
        int idx = n;
        for (int i=1; i<n; i++) {
            for (int j=i; j<n; j++) {
                sumA[idx] = sumA[j] - sumA[i-1];
                idx++;
            }
        }
        
        int m = Integer.parseInt(br.readLine());
        B = m*(m+1)/2;
        sumB = new int[B];
        st = new StringTokenizer(br.readLine());
        sumB[0] = Integer.parseInt(st.nextToken());
        for (int i=1; i<m; i++) {
            sumB[i] = sumB[i-1] + Integer.parseInt(st.nextToken());
        }
        idx = m;
        for (int i=1; i<m; i++) {
            for (int j=i; j<m; j++) {
                sumB[idx] = sumB[j] - sumB[i-1];
                idx++;
            }
        }
    }

    private static long twoPointer() {
        int left = 0, right = B-1;
        long count = 0;
        while (left < A && right >= 0) {
            int aValue = sumA[left], bValue = sumB[right], sum = aValue + bValue;
            if (sum == T) {
                long aCount = 0, bCount = 0;
                while (left < A && sumA[left] == aValue) {
                    left++;
                    aCount++;
                }
                while (right >= 0 && sumB[right] == bValue) {
                    right--;
                    bCount++;
                }
                count += aCount*bCount;
            } else if (sum > T) {
                right--;
            } else {
                left++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) throws Exception {
        readInput();
        Arrays.sort(sumA);
        Arrays.sort(sumB);
        System.out.println(twoPointer());
    }
}
