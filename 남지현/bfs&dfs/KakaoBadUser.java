import java.util.*;
import java.util.regex.*;

class Solution {   
    Pattern[] patterns;
    String[] ids;
    Set<Integer> answers;
    int N;
    
    public int solution(String[] user_id, String[] banned_id) {
        N = banned_id.length;
        patterns = new Pattern[N];
        ids = user_id;
        answers = new HashSet<>();
        for (int i=0; i<N; i++) {
            patterns[i] = Pattern.compile(banned_id[i].replaceAll("\\*", "\\."));
        }
        dfs(0, 0);
        return answers.size();
    }
    
    private void dfs(int depth, int used) {
        if (depth == N) {
            answers.add(used);
            return;
        }
        for (int i=0; i<ids.length; i++) {
            int next = used;
            if (patterns[depth].matcher(ids[i]).matches() && (next&1<<i)==0) {
                next |= 1<<i;
                dfs(depth+1, next);
            }
        }
    }
}
