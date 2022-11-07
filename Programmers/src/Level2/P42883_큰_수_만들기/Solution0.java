package Level2.P42883_큰_수_만들기;

public class Solution0 {
    public static void main(String[] args) {
        Solution0 tmp = new Solution0();
        String number = "1924";
        int k = 2;
        System.out.println(tmp.solution(number, k));

    }
    static int len, depMax, K;
    static String ans;
    static int[] nums;

    public String solution(String number, int k) {
        K = k;
        len = number.length();
        depMax = len - k;
        nums = new int[len];
        char[] tmpNums = number.toCharArray();
        for(int i = 0; i < len; i++){
            nums[i] = tmpNums[i] - '0';
        }
        dfs(0, 0, "");

        return ans;
    }

    static void dfs(int dep, int start, String resNum){
        if(dep > depMax) return;
        if(dep >= depMax){
            ans = resNum;
            return;
        }
        int maxId = K + dep;
        int maxNum = -1;
        int maxPos = -1;
        for(int i = start; i <= maxId; i++){
            if(maxNum == 9) break;
            if(maxNum < nums[i]){
                maxNum = nums[i];
                maxPos = i;
            }
        }

        dfs(dep + 1, maxPos + 1, resNum + maxNum);
    }

}
