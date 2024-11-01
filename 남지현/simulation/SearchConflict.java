import java.util.*;

// 프로그래머스 - 충돌 위험 찾기

class Solution {
    
    ArrayDeque<Point>[] queues;
    
    public int solution(int[][] points, int[][] routes) {
        int robotsNum = routes.length;
        int routeLength = routes[0].length;
        queues = new ArrayDeque[robotsNum];
        for (int i = 0; i < robotsNum; i++){
            queues[i] = new ArrayDeque<Point>();
            queues[i].addLast(new Point(points[routes[i][0]-1][0], points[routes[i][0]-1][1]));
            for (int j = 1; j < routeLength; j++) {
                addRoute(i, points[routes[i][j-1]-1], points[routes[i][j]-1]);
            }
        }
        int answer = 0; 
        while (true) {
            boolean finished = true;
            Map<Point, Integer> pointCount = new HashMap<>();
            for (int i = 0; i < robotsNum; i++) {
                if (!queues[i].isEmpty()) {
                    finished = false;
                    Point point = queues[i].pollFirst();
                    pointCount.put(point, pointCount.getOrDefault(point, 0)+1);
                }
            }
            if (finished) {
                break;
            }
            for (Map.Entry<Point, Integer> entry : pointCount.entrySet()) {
                if (entry.getValue() > 1) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    private void addRoute(int num, int[] dep, int[] dst) {
        int x = dep[0]; int y = dep[1];
        if (dep[0] < dst[0]) {
            for (x=dep[0]+1; x<=dst[0]; x++) {
                queues[num].addLast(new Point(x, y));
            }
            x--;
        } else if (dep[0] > dst[0]) {
            for (x=dep[0]-1; x>=dst[0]; x--) {
                queues[num].addLast(new Point(x, y));
            }
            x++;
        }
        if (dep[1] < dst[1]) {
            for (y=dep[1]+1; y<=dst[1]; y++) {
                queues[num].addLast(new Point(x, y));
            }
        } else if (dep[1] > dst[1]) {
            for (y=dep[1]-1; y>=dst[1]; y--) {
                queues[num].addLast(new Point(x, y));
            }
        }
    }
    
    static class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public boolean equals(Object o) {
            Point p = (Point) o;
            return this.x==p.x && this.y==p.y;
        }
        
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
