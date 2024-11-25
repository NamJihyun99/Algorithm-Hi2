import java.util.*;

class Solution {
    static Map<String, Integer> inRecords = new HashMap<>();
    static Map<String, Integer> durations = new HashMap<>();
    static Map<Integer, Integer> results = new HashMap<>();

    public int[] solution(int[] fees, String[] records) {
        for (String record : records) { // 시각, 차량번호, 내역
            String[] splited = record.split(" ");
            
            if (splited[2].equals("IN")) {
                inRecords.put(splited[1], timeToMinute(splited[0]));
            } else {
                durations.put(splited[1], durations.getOrDefault(splited[1], 0) + (timeToMinute(splited[0]) - inRecords.get(splited[1])));
                inRecords.remove(splited[1]);
            }
        }
        
        for (String car : inRecords.keySet()) {
            durations.put(car, 
            durations.getOrDefault(car, 0) + (timeToMinute("23:59") - inRecords.get(car)));
        }

        for (String car : durations.keySet()) {
            results.put(Integer.parseInt(car), calculateFee(fees, durations.get(car)));
        }
        
        int[] answer = new int[results.size()];
        List<Integer> carNums = new ArrayList<>(results.keySet());
        Collections.sort(carNums);
        for (int i = 0; i < answer.length; i++) {
            answer[i] = results.get(carNums.get(i));
        }
        
        return answer;
    }
    
    private static int timeToMinute(String time) {
        String[] splited = time.split(":");
        return Integer.parseInt(splited[0]) * 60 + Integer.parseInt(splited[1]);
    }
    
    private static int calculateFee(int[] fees, int duration) {
        if (duration <= fees[0]) {
            return fees[1];
        }
        
        int exceededUnitCounts = ((duration - fees[0]) % fees[2] == 0)? (duration - fees[0]) / fees[2] : (duration - fees[0]) / fees[2] + 1;
        
        return fees[1] + exceededUnitCounts * fees[3];
        
    }
}