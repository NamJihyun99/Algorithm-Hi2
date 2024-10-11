import java.util.*;

//프로그래머스 아날로그 시계
class Solution {   
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int start = h1*3600+m1*60+s1;
        int end = h2*3600+m2*60+s2;
        int count=0;
        if (start%360==0 || start%360==12) count++;
      
        for (int time=start; time<end; time++) {
            double hAngle = (time/(double)120)%360;
            double mAngle = (time/(double)10)%360;
            double sAngle = (time*6)%360;
          
            double hNextAngle = ((time+1)/(double)120)%360;
            double mNextAngle = ((time+1)/(double)10)%360;
            double sNextAngle = ((time+1)*6)%360;
          
            if (hNextAngle==0) hNextAngle=360;
            if (mNextAngle==0) mNextAngle=360;
            if (sNextAngle==0) sNextAngle=360;
          
            if (sAngle<hAngle && sNextAngle>=hNextAngle) count++;
            if (sAngle<mAngle && sNextAngle>=mNextAngle) count++;
            if (hNextAngle==mNextAngle) count--;
        }
        return count;
    }
}
