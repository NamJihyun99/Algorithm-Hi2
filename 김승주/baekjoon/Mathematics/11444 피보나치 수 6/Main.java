import java.io.*;

class Main {
    static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        if (n == 1 || n == 2) {
            System.out.println(1);
        } else {
            System.out.println(fibonacci(n));
        }
    }

    private static long fibonacci(long n) {
        long[][] fibonacciMatrix = {{1, 1}, {1, 0}}; // 피보나치 수열의 상태 전이 행렬
        long[][] resultMatrix = matrixPower(fibonacciMatrix, n - 1);
        return resultMatrix[0][0];
    }

    private static long[][] matrixPower(long[][] matrix, long power) {
        long[][] result = {{1, 0}, {0, 1}}; // 단위 행렬
        long[][] base = matrix;

        while (power > 0) {
            if (power % 2 == 1) {
                result = multiplyMatrices(result, base);
            }
            base = multiplyMatrices(base, base);
            power >>= 1;
        }
        return result;
    }

    private static long[][] multiplyMatrices(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        result[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        result[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        result[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;
        return result;
    }
}