class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int len = queries.length;
        
        int[] answer = new int[len];
        int[][] map = new int[rows + 1][columns + 1];
        int t = 1;
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) {
                map[i][j] = t++;
            }
        }
        for(int i = 0; i < len; i++) {
            int[] q = queries[i];
            int r = q[2] - q[0] - 1;
            int c = q[3] - q[1] - 1;

            int ul = map[q[0]][q[1]];
            int ur = map[q[0]][q[3]];
            int dl = map[q[2]][q[1]];
            int dr = map[q[2]][q[3]];
            
            for(int k = q[3] - 1; k >= q[1] + 1; k--) {
                map[q[0]][k + 1] = map[q[0]][k];
            }
            for(int k = q[2] - 1; k >= q[0] + 1; k--) {
                map[k + 1][q[3]] = map[k][q[3]];
            }
            for(int k = q[1] + 1; k < q[3]; k++) {
                map[q[2]][k - 1] = map[q[2]][k];
            }
            for(int k = q[0] + 1; k < q[2]; k++) {
                map[k - 1][q[1]] = map[k][q[1]];
            }
            
            map[q[0]][q[1] + 1] = ul;
            map[q[0] + 1][q[3]] = ur;
            map[q[2]][q[3] - 1] = dr;
            map[q[2] - 1][q[1]] = dl;
            
            int a = Math.min(map[q[0]][q[1]], map[q[0]][q[3]]);
            int b = Math.min(map[q[2]][q[3]], map[q[2]][q[1]]);
            int min = Math.min(a, b); // 가장자리
            
            for(int k = q[1] + 1; k < q[3]; k++) {
                min = Math.min(map[q[0]][k], min);
            }
            for(int k = q[0] + 1; k < q[2]; k++) {
                min = Math.min(map[k][q[3]], min);
            }
            for(int k = q[1] + 1; k < q[3]; k++) {
                min = Math.min(min, map[q[2]][k]);
            }
            for(int k = q[0] + 1; k < q[2]; k++) {
                min = Math.min(map[k][q[1]], min);
            }
            answer[i] = min;
        }
        return answer;
    }

}
