import java.util.*;
import java.io.*;

// 백준 17140번 - 이차원 배열과 연산

class Main {

    static int[][] arr;
    static int row, col;

    private static void R() {
        for (int i=0; i<row; i++) {
            int[] count = new int[101];
            for (int j=0; j<col; j++) {
                if (arr[i][j]!=0) {
                    count[arr[i][j]]++;
                }
            }
            List<int[]> buffer = new ArrayList<>();
            for (int j=1; j<101; j++) {
                if (count[j] > 0) {
                    buffer.add(new int[]{j, count[j]});
                }
            }
            Collections.sort(buffer, (arr1, arr2)->arr1[1]==arr2[1]? arr1[0]-arr1[0]: arr1[1]-arr2[1]);
            int size = buffer.size()>50? 50: buffer.size();
            col = Math.max(col, 2*size);
            for (int j=0; j<size; j++) {
                arr[i][j+j] = buffer.get(j)[0];
                arr[i][j+j+1] = buffer.get(j)[1];
            }
            for (int j=2*size; j<col; j++) {
                arr[i][j] = 0;
            }
        }
    }

    private static void C() {
        for (int i=0; i<col; i++) {
            int[] count = new int[101];
            for (int j=0; j<row; j++) {
                if (arr[j][i]!=0) {
                    count[arr[j][i]]++;
                }
            }
            List<int[]> buffer = new ArrayList<>();
            for (int j=1; j<101; j++) {
                if (count[j] > 0) {
                    buffer.add(new int[]{j, count[j]});
                }
            }
            Collections.sort(buffer, (arr1, arr2)->arr1[1]==arr2[1]? arr1[0]-arr1[0]: arr1[1]-arr2[1]);
            int size = buffer.size()>50? 50: buffer.size();
            row = Math.max(row, 2*size);
            for (int j=0; j<size; j++) {
                arr[j+j][i] = buffer.get(j)[0];
                arr[j+j+1][i] = buffer.get(j)[1];
            }
            for (int j=2*size; j<row; j++) {
                arr[j][i] = 0;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int count = 0;
        arr = new int[100][100];
        for (int i=0; i<3; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        row=3; col=3;
        while (count<=100 && arr[r-1][c-1]!=k) {
            if (row >= col) R();
            else C();
            count++;
        }
        System.out.println(count>100? -1: count);
    }
}
