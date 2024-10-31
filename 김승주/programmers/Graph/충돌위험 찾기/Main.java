import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        Map<Integer, int[]> pointNums = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            pointNums.put(i + 1, points[i]);
        }
        int[] status = new int[routes.length];
        List<int[]> currLocation = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            currLocation.add(pointNums.get(routes[i][0]));
        }
        for (int sec = 0; ; sec++) {
            int k = 0;
            for (; k < routes.length; k++) {
                if (status[k] != routes[0].length - 1) {
                    break;
                }
            }
            if (k == routes.length) {
                break;
            }
            
            for (int i = 0; i < routes.length; i++) {
                if (status[i] == routes[0].length - 1) {
                    continue;
                }
                
                int[] nextLocation = pointNums.get(status[i + 1]);
                if (currLocation.get(i)[0] == nextLocation[0]) {
                    if (currLocation.get(i)[1] > nextLocation[1]) {
                        currLocation.get(i)[1]--;
                    } else {
                        currLocation.get(i)[1]++;
                    }
                } else if (currLocation.get(i)[0] > nextLocation[0]) {
                    currLocation.get(i)[0]--;
                } else {
                    currLocation.get(i)[0]++;
                }
                
                if (currLocation.get(i)[0] == nextLocation[0] && 
                    currLocation.get(i)[1] == nextLocation[1] ) {
                    status[i]++;
                }
            }
            List<int[]> tempLocation = new ArrayList<>();
            for (int i = 0; i < routes.length; i++) {
                if (status[i] != routes[0].length - 1) {
                    tempLocation.add(routes[i]);
                }
            }
            Set<int[]> tempLocationSet = new HashSet<>();
            tempLocationSet.addAll(tempLocation);
            answer += (tempLocation.size() - tempLocationSet.size());
        }
        return answer;
    }
}