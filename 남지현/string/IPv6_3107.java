import java.util.*;

// 백준 3107번 IPv6
class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ip = sc.nextLine();
        sc.close();
        String[] tokens = ip.split("::");
        if (tokens.length == 0) {
            System.out.println("0000:0000:0000:0000:0000:0000:0000:0000");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int empties = 0;
        for (String token : tokens) {
            empties += token.split(":").length;
        }
        empties = 8 - empties;
        for (int i=0; i<tokens.length; i++) {
            String[] groups = tokens[i].split(":");
            for (String group : groups) {
                int len = group.length();
                for (int j=len; j<4; j++) {
                    sb.append('0');
                }
                sb.append(group).append(":");
            }
            if (i == 0) {
                for (int j=0; j<empties; j++) {
                    sb.append("0000:");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }
}
