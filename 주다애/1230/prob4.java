import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 호 안에 수류탄이야!!(실버 3)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
        if (n == 1) {
			System.out.println("권병장님, 중대장님이 찾으십니다");
			return;
		}
		Bomb[] bomb = new Bomb[n];
		int[] now = new int[n];
		int[] dist = new int[n];
		for (int i = 0; i < n; i++) {
			now[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }	
		dist[n - 1] = 0;
		for (int i = 0; i < n; i++) {
			bomb[i] = new Bomb(now[i], dist[i]);
		}
		int tb = 0;
		// 폭탄 던지기
		for (int i = 0; i < n; i++) {
			if (tb < bomb[i].now) {
				System.out.println("엄마 나 전역 늦어질 것 같아");
				return;
			}
			if (tb < bomb[i].now + bomb[i].dist) {
				tb = bomb[i].now + bomb[i].dist;;
			}
		}
		System.out.println("권병장님, 중대장님이 찾으십니다");
	}

	static class Bomb {
		int now;
		int dist;

		public Bomb(int now, int dist) {
			this.now = now;
			this.dist = dist;
		}
	}
}
