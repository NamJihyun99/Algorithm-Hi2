class Solution {
    int answer = 0;
    boolean[] checked;

    public int solution(int[] info, int[][] edges) {
        checked = new boolean[(int) Math.pow(2, info.length)];
        dfs(0, new boolean[info.length], 0, 0, info, edges, 1);
        return answer;
    }

    private void dfs(int idx, boolean[] visited, int sheep, int wolf, int[] info, int[][] edges, int state) {
        visited[idx] = true;
        checked[state] = true;

        if (info[idx] == 0) {
            sheep++;
            answer = Math.max(sheep, answer);
        } else {
            wolf++;
        }

        if (sheep <= wolf) {
            return;
        }

        for (int[] edge : edges) {
            if (visited[edge[0]] && !visited[edge[1]] && !checked[state | (1 << edge[1])]) {
                boolean[] newVisitied = visited.clone();
                dfs(edge[1], newVisitied, sheep, wolf, info, edges, state | (1 << edge[1]));
            }
        }
    }
}