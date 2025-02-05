import java.io.*;
import java.util.*;

class Main {
    static Map<Integer, Integer> profile = new HashMap<>();
    static List<Integer> sortedLocations;
    static int len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input1 = br.readLine().split(" ");
        if (N == 1) {
            System.out.println("권병장님, 중대장님이 찾으십니다");
            return;
        }
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            if (i == N - 1) {
                profile.put(Integer.valueOf(input1[i]), 0);
                continue;
            }
            profile.put(Integer.valueOf(input1[i]), Math.max(profile.getOrDefault(Integer.valueOf(input1[i]), 0), Integer.parseInt(input2[i]))) ;
        }
        
        sortedLocations = new ArrayList<>(profile.keySet());
        Collections.sort(sortedLocations);
        len = sortedLocations.size();
        search(0);
    }

    private static void search(int currLocationIdx) {
        if (currLocationIdx == len - 1) {
            System.out.println("권병장님, 중대장님이 찾으십니다");
            System.exit(0);
        }
        
        int maxDistance = sortedLocations.get(currLocationIdx) + profile.get(sortedLocations.get(currLocationIdx));
        int i = currLocationIdx + 1;
        for (; i < len && sortedLocations.get(i) <= maxDistance; i++) {

        }
        if (i - 1 == currLocationIdx) {
            System.out.println("엄마 나 전역 늦어질 것 같아");
            System.exit(0);
        }
        search(i - 1);
    }
}
