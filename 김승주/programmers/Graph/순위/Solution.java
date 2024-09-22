import java.util.*;

class Solution {
    static Set<Integer> candidates = new HashSet<>();
    static List<Integer>[] graph;

    public int solution(int n, int[][] results) {
        int[] degree = new int[n + 1];
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            candidates.add(i);
        }
        for (int[] result : results) {
            graph[result[0]].add(result[1]);
            degree[result[1]]++;
        }
        topologicalSort(degree, new boolean[n + 1]);
        return candidates.size();
    }

    private static void topologicalSort(int[] degree, boolean[] visited) {
        List<Integer> start = new ArrayList<>();
        for (int i = 1; i < degree.length; i++) {
            if (degree[i] == 0 && !visited[i]) {
                start.add(i);
            }
        }
        if (start.size() != 1) {
            candidates.removeAll(start);
            for (int num : start) {
                int[] nextDegree = Arrays.copyOf(degree, degree.length);
                boolean[] nextVisited = Arrays.copyOf(visited, visited.length);
                nextVisited[num] = true;
                for (int next : graph[num]) {
                    nextDegree[next]--;
                }
                topologicalSort(nextDegree, nextVisited);
            }
        } else {
            visited[start.get(0)] = true;
            for (int next : graph[start.get(0)]) {
                degree[next]--;
            }
            topologicalSort(degree, visited);
        }
    }
}