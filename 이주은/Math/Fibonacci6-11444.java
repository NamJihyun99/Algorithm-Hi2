//백준 11444번 피보나치수 6

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
	static long DIV = 1000000007L;

	static Map<Long, Long> f = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Long n = Long.parseLong(br.readLine());

		System.out.println(fibo(n));
	}

	static Long fibo(Long n) {
		if (n == 0)
			return 0L;
		else if (n == 1)
			return 1L;
		else if (n == 2)
			return 1L;

		if (f.getOrDefault(n, -1L) > 0)
			return f.get(n);

		if (n % 2 == 0) {
			long m = n / 2;
			long temp1 = fibo(m - 1);
			long temp2 = fibo(m);

			f.put(n, ((2L * temp1 + temp2) * temp2) % DIV);

			return f.get(n);
		}

		long m = (n + 1) / 2;
		long temp1 = fibo(m);
		long temp2 = fibo(m - 1);

		f.put(n, (temp1 * temp1 + temp2 * temp2) % DIV);
		
		return f.get(n);
	}
}
