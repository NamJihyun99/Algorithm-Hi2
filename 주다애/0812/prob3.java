import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		System.out.println(fibonacci(n));
	}

	// 행렬 곱셈
	private static long[][] matrixMultiply(long[][] a, long[][] b) {
		return new long[][] {
			{
				(a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD,
				(a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD
			},
			{
				(a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD,
				(a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD
			}
		};
	}

	// 행렬 제곱
	private static long[][] matrixPower(long[][] matrix, long power) {
		long[][] result = { {1, 0}, {0, 1} }; // 단위 행렬
		while (power > 0) {
			if (power % 2 == 1) {
				result = matrixMultiply(result, matrix);
			}
			matrix = matrixMultiply(matrix, matrix);
			power /= 2;
		}
		return result;
	}

	private static long fibonacci(long n) {
		if (n == 0) return 0;
		if (n == 1) return 1;

		long[][] fibMatrix = { {1, 1}, {1, 0} };
		long[][] result = matrixPower(fibMatrix, n - 1);
		return result[0][0]; // F(n)은 결과 행렬의 [0][0] 위치에 있음
	}
}
