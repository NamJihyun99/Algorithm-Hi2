import java.io.*;
import java.util.*;

class Main {
    static int[] union;
    static int N;
    static int M;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        
        // 진실을 아는 사람이 아무도 없으면 모든 파티에서 거짓 말하도록 출력하고 종료
        String[] input = br.readLine().split(" ");
        if (input.length == 1) {
            for (int i = 0; i < M; i++) br.readLine();
            System.out.println(M);
            return;
        }
        // 진실을 아는 사람의 목록 저장
        int[] knowing = new int[Integer.parseInt(input[0])];
        for (int i = 1; i < input.length; i++) {
            knowing[i - 1] = Integer.parseInt(input[i]);
        }

        // 날짜별 파티 참가자 목록을 저장하는 배열
        List<Integer>[] parties = new ArrayList[M];

        // union 배열 초기화
        union = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            union[i] = i;
        }

        for (int i = 0; i < M; i++) {
            // 각 파티의 참가자 번호 입력
            input = br.readLine().split(" ");

            // 참가자 번호 int 타입 변환 -> int 타입 리스트에 추가
            List<Integer> parsedInput = new ArrayList<>();
            for (int j = 1; j <= Integer.parseInt(input[0]); j++) {
                parsedInput.add(Integer.parseInt(input[j]));
            }

            // 함께 파티에 참가하는 사람들은 같은 부모 집합으로 union 수행
            parties[i] = parsedInput;
            for (int j = 0; j < parsedInput.size() - 1; j++) {
                getUnified(parsedInput.get(j),parsedInput.get(j + 1));
            }
        }

        // knowing과 같은 집합에 있는 원소들은 모두 부모 집합 번호를 0으로 변경
        for (int i = 0; i < knowing.length; i++) {
            int parent = union[knowing[i]];
            union[knowing[i]] = 0;
            for (int j = 1; j <= N; j++) {
                if (union[j] == parent) union[j] = 0;
            }
        }

        // 각 파티의 참석자들의 부모 집합이 0인지 확인 -> 부모 집합이 0인 참가자가 있다면 해당 파티에서는 진실만을 말해야 함.
        int result = 0;
        for (int i = 0; i < M; i++) {
            int j = 0;
            for (; j < parties[i].size(); j++) {
                // 부모 집합이 0인 참가자가 한 명이라도 있으면 진실을 말해야 함. 반복 중단하고 다음 파티 확인.
                if (union[parties[i].get(j)] == 0)  break;
            }
            // 위 반복문이 break되지 않았다면 파티 참여자들 중 부모 집합이 0인 참가자가 없었다는 뜻.. -> 해당 파티에서는 거짓을 말하기.
            if (j == parties[i].size()) result++;
        }
        System.out.println(result);
    }

    private static void getUnified(int a, int b) {
        if (union[a] == union[b])   return;
        int prev = union[b];
        union[b] = union[a];
        for (int i = 1; i <= N; i++) {
            if (union[i] == prev) {
                union[i] = union[a];
            }
        }
    }
}