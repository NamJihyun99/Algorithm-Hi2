import java.util.*; 

class Solution {
    
    private final class Token implements Comparable<Token> {
        char c1;
        char c2;
        
        private Token(char c1, char c2) {
            this.c1 = Character.toUpperCase(c1);
            this.c2 = Character.toUpperCase(c2);
        }
        
        public int compareTo(Token other) {
            if(this.c1 != other.c1)
                return this.c1 - other.c1;
            else
                return this.c2 - other.c2;
        }
        
        public String toString() {
            return c1+""+c2;
        }
    }
    
    public int solution(String str1, String str2) {
        List<Token> list1 = new ArrayList<>();
        List<Token> list2 = new ArrayList<>();
        
        for(int i=1; i<str1.length(); i++) {
            char c1 = str1.charAt(i-1);
            char c2 = str1.charAt(i);
            
            if(Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                list1.add(new Token(c1, c2));
            }
        }
        
        for(int i=1; i<str2.length(); i++) {
            char c1 = str2.charAt(i-1);
            char c2 = str2.charAt(i);
            
            if(Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                list2.add(new Token(c1, c2));
            }
        }
        
        if(list1.size()==0 && list2.size() ==0)
            return 65536;
        
        
        Collections.sort(list1);
        Collections.sort(list2);
        
        int cnt = 0, idx1 = 0, idx2 = 0;
        
        while(idx1 < list1.size() && idx2 < list2.size()) {
            int compare = list1.get(idx1).compareTo(list2.get(idx2));
            
            if(compare < 0) {
                idx1++;
            } else if(compare > 0) {
                idx2++;
            } else {
                idx1++;
                idx2++;
                
                cnt++;
            }
        }
        
        double sum = list1.size() + list2.size() - cnt;
        if(sum == 0)
            return 0;

        int answer = (int) ((cnt / sum)*65536);
        
        return answer;
    }
}
