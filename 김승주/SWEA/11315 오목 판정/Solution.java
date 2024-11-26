import java.io.*;

class Solution {
    static char[][] arr;
    static int N;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new char[N][N];
            
            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine().toCharArray();
            }

            if (findByRow()) {
                continue;
            }
            
            if (findByColumn()) {
                continue;
            }
            
            if (findByDiagonal()) {
                continue;
            }
            
            System.out.println("NO");
 		}
	}
    
    private static boolean findByRow() {
        for (int i = 0; i < N; i++) {
            int consecutiveCount = 0;
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'o') {
                    consecutiveCount++;
                } else {
                    consecutiveCount = 0;
                }
                
                if (consecutiveCount == 5) {
                    System.out.println("YES");
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean findByColumn() {
        for (int i = 0; i < N; i++) {
            int consecutiveCount = 0;
            for (int j = 0; j < N; j++) {
                if (arr[j][i] == 'o') {
                    consecutiveCount++;
                } else {
                    consecutiveCount = 0;
                }
                
                if (consecutiveCount == 5) {
                    System.out.println("YES");
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean findByDiagonal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'o') {
                    boolean incrementFound = true;
                    boolean decrementFound = true;
                    for (int p = 0; p < 5; p++) {
                        if (incrementFound == false && decrementFound == false) {
                            break;
                        }
                        
                        if (i + p >= N || j + p >= N || arr[i + p][j + p] != 'o') {
                            incrementFound = false;
                        }
                        if (i + p >= N || j - p < 0 || arr[i + p][j - p] != 'o') {
                            decrementFound = false;
                        }
                    }
                    if (incrementFound == true || decrementFound == true) {
                        System.out.println("YES");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}