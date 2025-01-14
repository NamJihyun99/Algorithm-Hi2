import java.util.*;
import java.io.*;

// 백준 2668번 - 숫자고르기
class Main {

    static int N;
    static Map<Integer, Integer> graph;
    static List<Integer> answer;
    static boolean[] visited;

    private static void search(int idx) {
        if (answer.contains(idx)) {
            return;
        }
        if (visited[idx]) {
            Set<Integer> result = new HashSet<>();
            write(idx, result);
            answer.addAll(result);
            return;
        }
        visited[idx] = true;
        search(graph.get(idx));
        visited[idx] = false;
    }

    private static void write(int number, Set<Integer> result) {
        result.add(number);
        if (!result.contains(graph.get(number))) {
            write(graph.get(number), result);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        for (int i=1; i<=N; i++) {
            graph.put(i, Integer.parseInt(br.readLine()));
        }
        answer = new ArrayList<>();
        visited = new boolean[N+1];
        for (int i=1; i<=N; i++) {
            if (!answer.contains(i)) {
                search(i);
            }
        }
        Collections.sort(answer);
        StringBuilder sb = new StringBuilder().append(answer.size());
        for (Integer e : answer) {
            sb.append("\n").append(e);
        }
        System.out.println(sb);
    }
}
