class Solution {
    int mod = 10007;
    public int solution(int n, int[] tops) {
        int answer = 0;
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        a[1] = 1;
        if(tops[0] == 1) {
            b[1] = 3;
        }
        else {
            b[1] = 2;
        }
        
        for(int i = 2; i <= n; i++) {
            a[i] = (a[i - 1] + b[i - 1]) % mod;
            
            // i번째 아래 방향 정삼각형 위에 정삼각형 o
            if(tops[i - 1] == 1) {
                b[i] = (2 * a[i - 1] + 3 * b[i - 1]) % mod;
            }
            // i번째 아래 방향 정삼각형 위에 정삼각형 x
            else {
                b[i] = (a[i - 1] + 2 * b[i - 1]) % mod;
            }
        }
        return (a[n] + b[n]) % mod;
    }
}
