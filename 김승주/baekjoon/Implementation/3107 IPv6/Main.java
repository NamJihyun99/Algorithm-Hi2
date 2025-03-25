import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String original = br.readLine();
        String[] temp = original.split("::");
        String[] result = new String[8];
        if (temp.length == 1) {
            String[] left = temp[0].split(":");
            for (int i = 0; i < left.length; i++) {
                result[i] = left[i];
            }
        } else {
            String[] left = temp[0].split(":");
            String[] right = temp[1].split(":");
            for (int i = 0, j = 0; i < left.length; i++) {
                if (left[i].isEmpty()) {
                    continue;
                }
                result[j++] = left[i];
            }
            for (int i = right.length - 1, j = 7; i >= 0; i--) {
                if (right[i].isEmpty()) {
                    continue;
                }
                result[j--] = right[i];
            }
        }

        for (int i = 0; i < 8; i++) {
            if (result[i] == null) {
                result[i] = "0000";
                continue;
            }
            if (result[i].length() != 4) {
                StringBuilder sb = new StringBuilder(result[i]);
                while (sb.length() < 4) {
                    sb.insert(0, '0');
                }
                result[i] = sb.toString();
            }
        }
        System.out.println(String.join(":", result));
    }
}