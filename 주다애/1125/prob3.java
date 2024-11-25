import java.util.*;
class Solution {
    static int[] answer;
    public int[] solution(int[] fees, String[] records) {
        Set<String> car = new HashSet<>();
        for(String s : records) {
            String[] temp = s.split(" ");
            car.add(temp[1]);
        }
        // 값 가져오기 위해서 Set -> List로 변환
        List<String> carList = new ArrayList<>(car);
        Collections.sort(carList);
        
        answer = new int[carList.size()];
        int idx = 0;
        for(String c : carList) {
            List<String> time = new ArrayList<>();
            for(String s : records) {
                String[] temp = s.split(" ");
                if(c.equals(temp[1])) time.add(temp[0]);
            }
            answer[idx] = calc(time, idx, fees);
            idx += 1;
        }
        return answer;
    }
    
    static int calc(List<String> time, int idx, int[] fees) {
        int len = time.size();
        int m = len / 2;
        int n = len % 2;
        int sum = 0;
        for(int i = 0; i < len - 1; i += 2) {
            String start = time.get(i);
            String end = time.get(i + 1);
            sum += getTime(start, end);
        }
        if(n == 1) {
            sum += getTime(time.get(len - 1), "23:59");
        }

        int feeSum = fees[1];
        // 기본 요금만 청구
        if(sum <= fees[0]) return feeSum;
        // 추가 시간까지 함께 청구
        int mod = (sum - fees[0]) % fees[2];
        if (mod == 0) feeSum += ((sum - fees[0]) / fees[2]) * fees[3];
        else feeSum += ((sum - fees[0]) / fees[2] + 1) * fees[3];
        return feeSum;
    }
    
    // 출차 시간 - 입차 시간
    static int getTime(String s, String e) {
        String[] st = s.split(":");
        String sh = st[0];
        String sm = st[1];
        if(sh.charAt(0) == '0') sh = sh.charAt(1) + "";
        if(sm.charAt(0) == '0') sm = sm.charAt(1) + "";
        int tst = Integer.parseInt(sm) + Integer.parseInt(sh) * 60;
        
        String[] et = e.split(":");
        String eh = et[0];
        String em = et[1];
        if(eh.charAt(0) == '0') eh = eh.charAt(1) + "";
        if(em.charAt(0) == '0') em = em.charAt(1) + "";
        int tet = Integer.parseInt(em) + Integer.parseInt(eh) * 60;
        
        return (tet - tst);
    }
}
