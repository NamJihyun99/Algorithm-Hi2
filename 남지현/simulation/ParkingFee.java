import java.util.*;

// 프로그래머스 - 주차 요금 계산

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Car> cars = new HashMap<>();
        for (String record: records) {
            String[] token = record.split(" ");
            Car car = cars.putIfAbsent(token[1], new Car(token[1]));
            if (car == null) car = cars.get(token[1]);
            car.update(token[0]);
        }
        List<Car> carList = new ArrayList<>();
        for (Map.Entry<String, Car> entry: cars.entrySet()) {
            carList.add(entry.getValue());
        }
        Collections.sort(carList, (car1, car2) -> car1.number.compareTo(car2.number));
        int[] answer = new int[carList.size()];
        for (int i=0; i<carList.size(); i++) {
            Car car = carList.get(i);
            if (car.buffer != -1) {
                car.update("23:59");
            }
            int total = fees[1];
            if (car.sum > fees[0]) {
                total += Math.ceil((double)(car.sum - fees[0])/fees[2])*fees[3];
            }
            answer[i] = total;
        }
        return answer;
    }
    
    static class Car {
        String number;
        int sum;
        int buffer;
        
        Car(String number) {
            this.number = number;
            sum = 0;
            buffer = -1;
        }
        
        void update(String time) {
            String[] token = time.split(":");
            if (buffer == -1) {
                // 입차
                buffer = Integer.parseInt(token[0])*60+Integer.parseInt(token[1]);
            } else {
                // 출차
                sum += (Integer.parseInt(token[0])*60+Integer.parseInt(token[1])) - buffer;
                buffer = -1;
            }
        }
    }
}
