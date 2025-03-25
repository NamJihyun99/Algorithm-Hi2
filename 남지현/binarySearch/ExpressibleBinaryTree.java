import java.util.*;

// 프로그래머스 표현 가능한 이진 트리

class Solution {
    
    String tree;
    int answer;
    
    public int[] solution(long[] numbers) {
        // 포화이진트리 노드 개수:  2^N-1
        int[] result = new int[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            answer = 1;
            String str = Long.toString(numbers[i], 2);
            tree = fillZero(str, getExtraLen(str.length()));
            search(2, 0, tree.length()-1);
            result[i] = answer;
        } 
        return result;
    }
    
    private void search(int parent, int left, int right) {
        int mid = (left+right) / 2;
        char child = tree.charAt(mid);
        if (parent == '0' && child == '1') {
            answer = 0;
            return;
        }  
        if (left == right) {
            return;
        }
        search(child, left, mid-1);
        search(child, mid+1, right);
    }

    // 추가 비트만큼 문자열 앞에 0을 채운다
    private String fillZero(String before, int extraLen) {
        for (int i = 0; i<extraLen; i++) {
            before = "0" + before;
        }
        return before;
    }

    // 포화 이진트리 노드 개수 pow-1을 구하고 추가로 채워야 하는 비트의 개수를 구한다.
    private int getExtraLen(int len) {
        int pow = 2;
        for (; pow-1<len; pow*=2) ;
        return pow-len-1;
    }
}
