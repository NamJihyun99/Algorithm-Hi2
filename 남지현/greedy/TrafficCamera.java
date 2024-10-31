import java.util.*;

// 프로그래머스 - 단속카메라 (복습)

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (arr1, arr2) -> arr1[1]==arr2[1]? arr1[0]-arr2[0] : arr1[1]-arr2[1]);
        int idx = 0; int N = routes.length;
        while (idx<N) {
            int now = routes[idx][1];
            answer++; idx++;
            while (idx<N && routes[idx][0]<=now && routes[idx][1]>=now) {
                idx++;
            }
        }
        return answer;
    }
}
