import java.util.*;

// trie 자료구조
// O(N)
class Solution {
    public int solution(String[] words) {
        int answer = 0;
        Node root = new Node(); // 루트 노드
        // 삽입
        for(String s : words) {
            Node node = new Node(); 
            for(int i = 0; i < s.length(); i++) {
                char t = s.charAt(i); // 문자
                node.child.putIfAbsent(t, new Node()); // 새로운 문자 넣어주기
                node = node.child.get(t);
                node.cnt += 1; // 해당 문자가 공유되는 횟수
            }
            node.end = true; // 마지막 문자
        }
        // 탐색
        for(String s : words) {
            Node node = root;
            for(int i = 0; i < s.length(); i++) {
                char t = s.charAt(i); // 문자
                node = node.child.get(t);
                answer += 1;
                if(node.cnt == 1) break;
            }
        }
        return answer;
    }
    
    
    class Node {
        Map<Character, Node> child;
        boolean end;
        int cnt;
        Node() {
            child = new HashMap<>();
            end = false;
            cnt = 0;
        }
    }
}
