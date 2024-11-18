import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] play = new int[6][3];	//경기 결과 저장 배열
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            answer = 0;
            for (int j = 0; j < 18; j++) {
                play[j / 3][j % 3] = Integer.parseInt(st.nextToken());
            }
                
            boolean flag = false;
            for(int j=0;j<6;j++) {
                if(play[j][0] + play[j][1] + play[j][2] != 5) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                search(0, 1);
            }
                
            sb.append(answer).append(" ");
        }
        System.out.print(sb);	//결과 출력
    }

    private static void search(int idx, int nxt) {
        if (answer == 1)	
            return;
        if (idx == 5) {
            answer = 1;
            return;
        }
        //현재 국가가 상대 국가에게 이겼을 때
        if (play[idx][0] > 0 && play[nxt][2] > 0) {
            play[idx][0]--;
            play[nxt][2]--;
            if (nxt == 5) {	//현재 국가 탐색 완료
                search(idx + 1, idx + 2);
            }else
                search(idx, nxt + 1);
            play[idx][0]++;
            play[nxt][2]++;
        }
        //현재 국가와 상대 국가가 무승부 했을 때
        if (play[idx][1] > 0 && play[nxt][1] > 0) {
            play[idx][1]--;
            play[nxt][1]--;
            if (nxt == 5) {	//현재 국가 탐색 완료
                search(idx + 1, idx + 2);
            }else
                search(idx, nxt + 1);
            play[idx][1]++;
            play[nxt][1]++;
        }
        //현재 국가가 상대 국가에게 패배하였을 때
        if (play[idx][2] > 0 && play[nxt][0] > 0) {
            play[idx][2]--;
            play[nxt][0]--;
            if (nxt == 5) {	//현재 국가 탐색 완료
                search(idx + 1, idx + 2);
            }else
                search(idx, nxt + 1);
            play[idx][2]++;
            play[nxt][0]++;
        }

    }
}