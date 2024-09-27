import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] times;
    static int[] cnt;
    static List<List<Integer>> postJobs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        times = new int[N];
        cnt = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            postJobs.add(new ArrayList<>());
            times[i] = Integer.parseInt(st.nextToken());
            cnt[i] = Integer.parseInt(st.nextToken());

            for(int j = 0; j < cnt[i]; j++) {
                postJobs.get(Integer.parseInt(st.nextToken())-1).add(i);
            }
        }

        int t = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> times[a]));
        
        for(int i=0; i<N; i++) {
        	if(cnt[i] == 0)
        		pq.add(i);
        }

        while(!pq.isEmpty()) {
            int curr = pq.poll();
            t = Math.max(times[curr], t);
//            System.out.println("["+t+"] "+(curr+1));

            for(int i: postJobs.get(curr)) {
                cnt[i]--;
                if(cnt[i] == 0) {
                    times[i] += times[curr];
                    pq.add(i);
                }
            }
        }

        System.out.println(t);
    }
}
