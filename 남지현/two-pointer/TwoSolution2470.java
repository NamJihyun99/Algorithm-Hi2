import java.util.*;
import java.io.*;

// 백준 2470번 - 두 용액
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] features = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            features[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(features);
        int left = 0, right = N-1, one = 0, two = Integer.MAX_VALUE, absoluteMixed = Integer.MAX_VALUE;
        while (left < right) {
            int sum = features[left] + features[right];
            if (Math.abs(sum) < absoluteMixed) {
                one = features[left];
                two = features[right];
                absoluteMixed = Math.abs(sum);
            }
            if (sum >= 0) {
                right--;
            } else {
                left++;
            }
            if (absoluteMixed == 0) {
                break;
            }
        }
        System.out.println(one+" "+two);
    }
}
