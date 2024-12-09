import java.util.*;

// 프로그래머스 - 가장 많이 받은 선물

class Solution {
    
    String[] members;
    int[] result;
    List<String> state;
    List<String[]> draws;
    Map<String, Member> records;
    
    public int solution(String[] friends, String[] gifts) {
        members = friends;
        records = new HashMap<>();
        for (int i=0; i<members.length; i++) {
            records.put(members[i], new Member(i));
        }
        for (String record: gifts) {
            String[] tokens = record.split(" ");
            records.get(tokens[0]).gives.add(tokens[1]);
            records.get(tokens[1]).receives += 1;
        }
        state = new ArrayList<>();
        draws = new ArrayList<>();
        result = new int[members.length];
        dfs(-1, 0);
        for (String[] pair: draws) {
            int numberA = records.get(pair[0]).gives.size() - records.get(pair[0]).receives;
            int numberB = records.get(pair[1]).gives.size() - records.get(pair[1]).receives;
            if (numberA > numberB) {
                result[records.get(pair[0]).index] += 1;
            } else if (numberA < numberB) {
                result[records.get(pair[1]).index] += 1;
            }
        }
        int answer = 0;
        for (int value: result) {
            answer = Math.max(answer, value);
        }
        return answer;
    }
    
    private void dfs(int index, int depth) {
        if (depth == 2) {
            int A = Collections.frequency(records.get(state.get(0)).gives, state.get(1)); // 0->1
            int B = Collections.frequency(records.get(state.get(1)).gives, state.get(0)); // 1->0
            if (A == B) {
                draws.add(new String[]{state.get(0), state.get(1)});
            } else if (A > B) {
                result[records.get(state.get(0)).index] += 1;
            } else {
                result[records.get(state.get(1)).index] += 1;
            }
            return;
        }
        for (int i=index+1; i<members.length; i++) {
            state.add(members[i]);
            dfs(i, depth+1);
            state.remove(members[i]);
        }
    }
    
    static class Member {
        int index;
        int receives = 0;
        List<String> gives = new ArrayList<>();
        
        Member(int index) {
            this.index = index;
        }
    }
}
