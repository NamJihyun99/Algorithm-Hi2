import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, answer = 1_000_000_000;
    static int sinArr[], ssnArr[];


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sinArr = new int[N];
        ssnArr = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            sinArr[i] = Integer.parseInt(st.nextToken());
            ssnArr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            comb(i+1, sinArr[i], ssnArr[i]);
        }

        System.out.println(answer);
    }

    private static void comb(int start, int sin, int ssn) {
        answer = Math.min(answer, Math.abs(sin - ssn));

        for(int i=start; i<N; i++) {
            comb(i+1, sin*sinArr[i], ssn+ssnArr[i]);
        }
    }
}
