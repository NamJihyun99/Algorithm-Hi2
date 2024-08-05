import java.io.*;
import java.util.*;

class Main {
    static int[][] arr = new int[100][100];
    static int maxRow = 3;
    static int maxCol = 3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        while (arr[r - 1][c - 1] != k && count <= 100) {
            count++;
            if (maxRow >= maxCol) {
                R();
            } else {
                C();
            }
        }

        if (count <= 100) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }

    private static void R() {
        Map<Integer, Integer> map;
        int nextMaxCol = 0;
        for (int i = 0; i < maxRow; i++) {
            map = new HashMap<>();
            for (int j = 0; j < maxCol; j++) {
                if (arr[i][j] != 0) {
                    if (map.keySet().contains(arr[i][j])) {
                        map.put(arr[i][j], map.get(arr[i][j]) + 1);
                    } else {
                        map.put(arr[i][j], 1);
                    }
                }
            }

            List<Integer> keySet = new ArrayList<>(map.keySet());
            Collections.sort(keySet, new MyComparator(map));
            List<Integer> newRow = new ArrayList<>();
            for (int key : keySet) {
                newRow.add(key);
                newRow.add(map.get(key));
            }
            nextMaxCol = Math.max(nextMaxCol, newRow.size());
            for (int j = 0; j < 100; j++) {
                if (j < newRow.size()) {
                    arr[i][j] = newRow.get(j);
                } else {
                    arr[i][j] = 0;
                }
            }
        }
        maxCol = nextMaxCol;
    }

    private static void C() {
        Map<Integer, Integer> map;
        int nextMaxRow = 0;
        for (int j = 0; j < maxCol; j++) {
            map = new HashMap<>();
            for (int i = 0; i < maxRow; i++) {
                if (arr[i][j] != 0) {
                    if (map.keySet().contains(arr[i][j])) {
                        map.put(arr[i][j], map.get(arr[i][j]) + 1);
                    } else {
                        map.put(arr[i][j], 1);
                    }
                }
            }

            List<Integer> keySet = new ArrayList<>(map.keySet());
            Collections.sort(keySet, new MyComparator(map));
            List<Integer> newCol = new ArrayList<>();
            for (int key : keySet) {
                newCol.add(key);
                newCol.add(map.get(key));
            }
            nextMaxRow = Math.max(nextMaxRow, newCol.size());
            for (int i = 0; i < 100; i++) {
                if (i < newCol.size()) {
                    arr[i][j] = newCol.get(i);
                } else {
                    arr[i][j] = 0;
                }
            }
        }
        maxRow = nextMaxRow;
    }

    static class MyComparator implements Comparator<Integer> {
        Map<Integer, Integer> map;
        MyComparator(Map<Integer, Integer> map) {
            this.map = map;
        }
        @Override
        public int compare(Integer a, Integer b) {
            if (Objects.equals(map.get(a), map.get(b))) {
                return a - b;
            } else {
                return map.get(a) - map.get(b);
            }
        }
    }

}