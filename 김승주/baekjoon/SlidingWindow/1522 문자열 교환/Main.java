import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] ca = br.readLine().toCharArray();

        int count = 0;
        char front = ca[0];
        char rear = ca[ca.length - 1];
        int i = 0, j = ca.length - 1;

        while (i < j) {
            // 이전 문자와 현재 문자가 동일한 경우
            if (ca[i] == front && ca[j] == rear) {
                i++; j--;
            } else if (ca[i] != front && ca[j] != rear) {
                if (ca[i] == ca[j]) {
                    while (ca[i] == ca[j]) {
                        j--;
                    }
                    char temp = ca[i];
                    ca[i] = ca[j];
                    ca[j] = temp;
                    count++;
                    i++; j--;
                } else {
                    char temp = ca[i];
                    ca[i] = ca[j];
                    ca[j] = temp;
                    count++;
                    i++; j--;
                }
            } else if (ca[i] == front && ca[j] != rear) {
                while (ca[i] == front) {
                    i++;
                }
            } else if (ca[i] != front && ca[j] == rear) {
                while (ca[j] == rear) {
                    j--;
                }
            }
        }
        
        System.out.println(count);
    }
}