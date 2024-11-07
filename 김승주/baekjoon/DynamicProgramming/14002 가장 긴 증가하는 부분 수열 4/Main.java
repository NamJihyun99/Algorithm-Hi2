import java.io.*;
import java.util.*;

class Main {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(arr[i]);
        }
        List<Integer> result = findLongestSubArr();
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }

    private static List<Integer> findLongestSubArr() {
        List<Integer> subArrs[] = new List[A.length]; // subArrs[i]: A[i]를 포함하는 최장 길이 부분 수열
        for (int i = 0; i < A.length; i++) {
            subArrs[i] = new ArrayList<>();
        }
        int maxLength = 1;
        int maxIdx = 0;
        int maxElement = A[0]; // 현재까지의 최장 길이 부분 수열의 최댓값
        subArrs[0].add(A[0]);
        for (int i = 1; i < A.length; i++) {
            if (A[i] > maxElement) {
                maxLength++;
                maxElement = A[i];
                subArrs[i].addAll(subArrs[maxIdx]);
                subArrs[i].add(A[i]);
                maxIdx = i;
            } else {
                int temp = 0;
                List<Integer> tempList = new ArrayList<>();
                for (int j = i - 1; j >= 0; j--) {    
                    if (A[j] < A[i] && temp < subArrs[j].size()) {
                        temp = subArrs[j].size();
                        tempList.clear();
                        tempList.addAll(subArrs[j]);
                    }
                }

                subArrs[i].addAll(tempList);
                subArrs[i].add(A[i]);
                if (temp + 1 > maxLength) {
                    maxIdx = i;
                    maxElement = A[i];
                    maxLength = subArrs[i].size();
                    
                }
            }
        }

        return subArrs[maxIdx];
    }
}

