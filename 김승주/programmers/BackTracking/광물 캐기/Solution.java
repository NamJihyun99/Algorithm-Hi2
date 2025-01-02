import java.util.HashMap;
import java.util.Map;

class Solution {
    static String[] mineral;
    static int minStamina = 1250;
    public int solution(int[] picks, String[] minerals) {
        mineral = minerals;
        Map<Character, Integer> pickaxes = new HashMap<>();
        pickaxes.put('d', picks[0]);
        pickaxes.put('i', picks[1]);
        pickaxes.put('s', picks[2]);
        backtracking(0, pickaxes, 0);
        return minStamina;
    }
    
    private static void backtracking(int startIdx, Map<Character, Integer> pickaxes, int currStamina) {
        if (currStamina >= minStamina) {
            return;
        }
        if (startIdx >= mineral.length || (pickaxes.get('d') == 0 && pickaxes.get('i') == 0 && pickaxes.get('s') == 0)) {
            minStamina = currStamina;
            return;
        }
        
        if (pickaxes.get('d') > 0) {
            int increasedStamina = 0;
            for (int i = startIdx; i < mineral.length && i < startIdx + 5; i++) {
                increasedStamina++;
            }
            pickaxes.put('d', pickaxes.get('d') - 1);
            backtracking(startIdx + 5, pickaxes, currStamina + increasedStamina);
            pickaxes.put('d', pickaxes.get('d') + 1);
        }
        
        if (pickaxes.get('i') > 0) {
            int increasedStamina = 0;
            for (int i = startIdx; i < mineral.length && i < startIdx + 5; i++) {
                if (mineral[i].equals("diamond")) {
                    increasedStamina += 5;
                } else {
                    increasedStamina++;
                }
            }
            pickaxes.put('i', pickaxes.get('i') - 1);
            backtracking(startIdx + 5, pickaxes, currStamina + increasedStamina);
            pickaxes.put('i', pickaxes.get('i') + 1);
        }
        if (pickaxes.get('s') > 0) {
            int increasedStamina = 0;
            for (int i = startIdx; i < mineral.length && i < startIdx + 5; i++) {
                if (mineral[i].equals("diamond")) {
                    increasedStamina += 25;
                } else if (mineral[i].equals("iron")) {
                    increasedStamina += 5;
                } else {
                    increasedStamina++;
                }
            }
            pickaxes.put('s', pickaxes.get('s') - 1);
            backtracking(startIdx + 5, pickaxes, currStamina + increasedStamina);
            pickaxes.put('s', pickaxes.get('s') + 1);
        }
    }
}
