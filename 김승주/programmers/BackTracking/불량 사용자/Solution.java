import java.util.*;
import java.util.regex.Pattern;

class Solution {
    static int numOfBannedId;
    static List<Set<String>> nicknames = new ArrayList<>();
    static Map<String, Integer> num = new HashMap<>();
    static Set<Integer> result = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        numOfBannedId = banned_id.length;
        List<String> regex = new ArrayList<>();
        for (String id : banned_id) {
            regex.add(id.replace("*", "[a-z0-9]{1}"));
        }
        for (int j = 0; j < banned_id.length; j++) {
            nicknames.add(new HashSet<>());
            for (int i = 0; i < user_id.length; i++) {
                num.put(user_id[i], i);
                if (Pattern.matches(regex.get(j), user_id[i])) {
                    nicknames.get(j).add(user_id[i]);
                }
            }
        }
        backtracking(0, 0);
        return result.size();
    }

    private static void backtracking(int cnt, int bit) {
        if (cnt == numOfBannedId) {
            result.add(bit);
            return;
        }

        for (String id : nicknames.get(cnt)) {
            if ((bit & (1 << num.get(id))) == 0) {
                bit |= (1 << num.get(id));
                backtracking(cnt + 1, bit);
                bit &= ~(1 << num.get(id));
            }
        }

    }
}