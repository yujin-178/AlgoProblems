package Level2.P87946_피로도;

public class Solution1 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dun = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(k, dun));
    }

    static boolean[] chk;
    static int ans, len;
    static int[][] dun;
    static public int solution(int k, int[][] dungeons) {
        len = dungeons.length;
        dun = dungeons;
        chk = new boolean[len];

        dfs(0,k);

        return ans;
    }

    static void dfs(int dep, int hp){
        if(dep > ans){
            ans = dep;
        }

        if (dep == len || hp <= 0)
            return;

        for(int i = 0; i < len; i++){
            if(chk[i] || hp < dun[i][0]) continue;

            chk[i] = true;
            dfs(dep + 1, hp - dun[i][1]);
            chk[i] = false;
        }
    }


}
