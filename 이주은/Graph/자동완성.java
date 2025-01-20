import java.util.*;

class Solution {
    
    private final class Node {
        int cnt;
        Map<Character, Node> map;
        
        private Node() {
            this.cnt = 0;
            this.map = new HashMap<>();
        }
    }
    
    public int solution(String[] words) {
        int answer = 0;
        Node root = new Node();
        
        for(String word : words) {
            Node node = root;
                 
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                
                if(!node.map.containsKey(c)) {
                    node.map.put(c, new Node());
                }
                
                node = node.map.get(c);
                node.cnt++;
            }
        }
        
        for(String word : words) {
            Node node = root;
            
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                answer ++;
                
                node = node.map.get(c);
                
                if(node.cnt == 1)
                    break;
            }
        }
        
        return answer;
    }
}
