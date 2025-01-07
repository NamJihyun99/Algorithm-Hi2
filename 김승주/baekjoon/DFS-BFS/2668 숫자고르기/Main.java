import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {
    static int[] table;
    static int[] isSelected;
    static Set<Integer>[] reversed;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        table = new int[N + 1];
        isSelected = new int[N + 1];
        reversed = new Set[N + 1];
        for (int i = 1; i <= N; i++) {
            reversed[i] = new HashSet<>();
        } 
        for (int i = 1; i <= N; i++) {
            table[i] = Integer.parseInt(br.readLine());
            reversed[table[i]].add(i);
        }

        Set<Integer> answer = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            if (dfs(i, new HashSet<>(), true)) {
                answer.add(i);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for (int num : answer.stream().sorted().collect(Collectors.toList())) {
            sb.append(num).append("\n");
        }
        System.out.print(sb.toString());
    }
    
    private static boolean dfs(int idx, Set<Integer> path, boolean isUpper) {
        if (isUpper) {
            if (isSelected[idx] == 1) {
                return true;
            }
            if (isSelected[idx] == -1) {
                return false;
            }
            if (reversed[idx].isEmpty()) {
                isSelected[idx] = -1;
                return false;
            }

            path.add(idx);
            Set<Integer> tempSet;
            boolean flag = false;
            for (int e : reversed[idx]) {
                tempSet = new HashSet<>(path);
                if (path.contains(e) || dfs(e, tempSet, true)) {
                    flag = true;
                    path.addAll(tempSet);
                }
            }

            if (flag && (path.contains(table[idx]) || dfs(idx, path, false))) {
                for (int e : path) {
                    isSelected[e] = 1;
                }
                return true;
            }
            isSelected[idx] = -1;
            return false;
        }

        if (isSelected[table[idx]] == 1) {
            return true;
        }
        if (isSelected[table[idx]] == -1) {
            return false;
        }

        path.add(table[idx]);
        if (path.contains(table[table[idx]]) || dfs(table[table[idx]], path, true)) {
            if (path.contains(idx) || dfs(idx, path, true)) {
                for (int e : path) {
                    isSelected[e] = 1;
                }
                return true;
            }
        }
        return false;
    }
}