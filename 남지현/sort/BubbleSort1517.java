import java.util.*;
import java.io.*;

// 백준 1517번 버블 소트
class Main {
    
    static int N;
    static long count;
    static int[] arr, tmp;

    static void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left+right)/2;
            mergeSort(left, mid);
            mergeSort(mid+1, right);
            merge(left, mid, right);
        }
    }

    static void merge(int left, int mid, int right) {
        tmp = new int[right-left+1];
        int i = left, j = mid+1;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                // i-left + j-mid-1
                tmp[i+j-left-mid-1] = arr[i];
                i++;
            } else {
                tmp[i+j-left-mid-1] = arr[j];
                j++;
                count += (mid-i+1);
            } 
        }
        if (j <= right) {
            // right가 남았다.
            for (; j<=right; j++) {
                tmp[i+j-left-mid-1] = arr[j];
            }
        } else if (i <= mid) {
            // left가 남았다
            for (; i<=mid; i++) {
                tmp[i+j-left-mid-1] = arr[i];
            }
        }
        for (int n=0; n<tmp.length; n++) {
            arr[left+n] = tmp[n];
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        count = 0;
        mergeSort(0, N-1);
        System.out.println(count);
    }
}
