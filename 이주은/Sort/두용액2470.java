import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[] arr;
    static int[] answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = new int[2];
        
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int left = 0, right = N-1, min = Integer.MAX_VALUE;

        while(left < right) {
            int sum = arr[left] + arr[right];

            if(min > Math.abs(sum)) {
                min = Math.abs(sum);

                answer[0] = arr[left];
                answer[1] = arr[right];
            }

            if(sum < 0)
                left ++;
            else if(sum > 0)
                right --;
            else
                break;
        }
        
        System.out.println(answer[0]+" "+answer[1]);
    }
}
