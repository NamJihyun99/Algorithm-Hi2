import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        // 중복된 조합을 방지하기 위한 Set 사용
        Set<Set<String>> uniqueCombinations = new HashSet<>();
        
        // 조합 생성
        backtrack(user_id, banned_id, new HashSet<>(), 0, uniqueCombinations);
        
        return uniqueCombinations.size();
    }

    private void backtrack(String[] user_id, String[] banned_id, Set<String> current, int index, Set<Set<String>> uniqueCombinations) {
        if (index == banned_id.length) {
            // 모든 불량 사용자의 패턴을 체크했으면, 현재의 조합을 추가
            uniqueCombinations.add(new HashSet<>(current));
            return;
        }

        // 현재의 불량 사용자 패턴에 맞는 사용자 ID를 찾기
        for (String user : user_id) {
            if (current.contains(user) || !isMatch(user, banned_id[index])) {
                continue;
            }
            // 사용자 ID를 현재 조합에 추가
            current.add(user);
            // 다음 불량 사용자 패턴으로 이동
            backtrack(user_id, banned_id, current, index + 1, uniqueCombinations);
            // 현재 사용자 ID를 조합에서 제거
            current.remove(user);
        }
    }

    private boolean isMatch(String user, String banned) {
        if (user.length() != banned.length()) {
            return false; // 길이가 다르면 패턴 불일치
        }
        for (int i = 0; i < user.length(); i++) {
            char u = user.charAt(i);
            char b = banned.charAt(i);
            if (b != '*' && b != u) {
                return false; // 패턴과 문자 불일치
            }
        }
        return true;
    }
}
