import java.io.*;
import java.util.*;

class Main {
    static int N;
    static Set<Integer>[] topology;
    static int[] duration;
    static Set<Integer> notVisited = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        topology = new Set[N + 1];
        duration = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            topology[i] = new HashSet<>();
            notVisited.add(i);
            StringTokenizer st = new StringTokenizer(br.readLine());
            duration[i] = Integer.parseInt(st.nextToken());
            int numOfBefore = Integer.parseInt(st.nextToken());
            for (int j = 0; j < numOfBefore; j++) {
                topology[i].add(Integer.valueOf(st.nextToken()));
            }
        }
        System.out.println(topologicalSort());
    }

    private static int topologicalSort() {
        int sum = 0;
        while (!notVisited.isEmpty()) {
            int minDuration = 101;
            List<Integer> startNodes = new ArrayList<>();
            for (int node : notVisited) {
                if (topology[node].isEmpty()) {
                    startNodes.add(node);
                    minDuration = Math.min(minDuration, duration[node]);
                } 
            }

            sum += minDuration;
            for (int startNode : startNodes) {
                duration[startNode] -= minDuration;
                if (duration[startNode] == 0) {
                    for (int node : notVisited) {
                        topology[node].remove(startNode);
                    }
                    notVisited.remove(startNode);
                }
            }
        }
        return sum;
    }
}