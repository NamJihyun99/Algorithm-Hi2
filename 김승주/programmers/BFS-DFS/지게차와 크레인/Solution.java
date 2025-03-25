import java.util.*;

class Solution {
    static char[][] containers; // 'e'가 저장되어 있는 위치는 빈 공간이라는 뜻.
    static int answer;
    public int solution(String[] storage, String[] requests) {
        containers = new char[storage.length + 2][storage[0].length() + 2];
        answer = storage.length * storage[0].length(); // 초기 컨테이너 개수로 초기화
        
        // 컨테이너의 가장자리를 빈 공간('e')으로 채우기
        Arrays.fill(containers[0], 'e');
        Arrays.fill(containers[storage.length + 1], 'e');
        for (int i = 1; i <= storage.length; i++) {
            containers[i][0] = 'e';
            // 'e'로 둘러싸인 containers 내부에 주어진 storage의 한 문자씩 채우기
            for (int j = 1; j <= storage[0].length(); j++) {
                containers[i][j] = storage[i - 1].charAt(j - 1);
            }
            containers[i][storage[0].length() + 1] = 'e';
        }
        
        List<Character> removed = new ArrayList<>();
        for (String req : requests) {
            char target = req.charAt(0);
            
            // 이전에 크레인을 통해 모두 제거된 컨테이너에 대해 다시 요청이 들어오면 스킵.
            if (removed.contains(target)) {
                continue;
            }
            
            if (req.length() == 1) {
                useForklift(target);
            } else {
                useCrane(target);
                removed.add(target);
            }
        }
        return answer;
    }
    
    private static void useForklift(char target) {
        boolean[][] visited = new boolean[containers.length][containers[0].length];
        Queue<int[]> q = new LinkedList<>();
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        
        // bfs
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nRow = curr[0] + dx[i];
                int nCol = curr[1] + dy[i];
                if (nRow >= 0 && nRow < containers.length
                    && nCol >= 0 && nCol < containers[0].length
                    && !visited[nRow][nCol]) {
                    visited[nRow][nCol] = true;
                    // 접근 가능한 target 컨테이너를 찾은 경우
                    // 컨테이너를 꺼내고 해당 위치를 빈 공간('e')으로 변경
                    if (containers[nRow][nCol] == target) {
                        answer--;
                        containers[nRow][nCol] = 'e';
                    } else if (containers[nRow][nCol] == 'e') {
                        q.offer(new int[] {nRow, nCol});
                    }
                }
            }
        }
    }
    
    private static void useCrane(char target) {
        // 존재하는 모든 컨테이너를 순회하며 확인
        for (int row = 1; row < containers.length - 1; row++) {
            for (int col = 1; col < containers[0].length - 1; col++) {
                // 접근 가능한 target 컨테이너를 찾은 경우
                // 컨테이너를 꺼내고 해당 위치를 빈 공간('e')으로 변경
                if (containers[row][col] == target) {
                    answer--;
                    containers[row][col] = 'e';
                }
            }
        }
    }
}