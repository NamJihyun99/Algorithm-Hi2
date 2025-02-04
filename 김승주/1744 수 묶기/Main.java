import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> negative = new ArrayList<>();
        List<Integer> greaterThanOne = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 1) {
                sum++;
            } else if (input > 1) {
                greaterThanOne.add(input);
            } else {
                negative.add(input);
            }
        }

        Collections.sort(negative);
        Collections.sort(greaterThanOne, Collections.reverseOrder());
        
		Iterator<Integer> iterator = negative.iterator();
		sum = getSum(iterator, sum);
		
		iterator = greaterThanOne.iterator();
		sum = getSum(iterator, sum);
		
        System.out.println(sum);
    }

    private static int getSum(Iterator<Integer> iterator, int sum) {
		while(iterator.hasNext()) {
			int currentVal = iterator.next();
			
			if(iterator.hasNext()) {
				int nextVal = iterator.next();
				sum += currentVal * nextVal;
			} else {
				sum += currentVal;
			}
		}
		return sum;
	}
}