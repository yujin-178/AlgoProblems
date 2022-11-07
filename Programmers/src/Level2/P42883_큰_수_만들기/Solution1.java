package Level2.P42883_큰_수_만들기;

import java.util.LinkedHashSet;
import java.util.PriorityQueue;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 tmp = new Solution1();
        String number = "1924";
        int k = 2;
        System.out.println(tmp.solution(number, k));

    }

    static int len, depMax;
    static PriorityQueue<Info> pq;

    public String solution(String number, int k) {
        len = number.length();
        depMax = len - k;
        char[] tmpNums = number.toCharArray();
        pq = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            pq.add(new Info(tmpNums[i] - '0', i));
        }
        String ans = "";



        while(ans.length() < depMax){
            Info tmp = pq.poll();
            if(tmp.idx <= ans.length() + k){
                ans += tmp.num;
            }
        }

        return ans;
    }


    static class Info implements Comparable<Info> {
        int num, idx;

        public Info(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        public int compareTo(Info o) {
            return -Integer.compare(this.num, o.num);
        }

    }
}
