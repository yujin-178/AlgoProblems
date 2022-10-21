package TreeSet.최대로_연속한_숫자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TreeSet<Integer> set = new TreeSet<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        set.add(-1);
        set.add(N + 1);
        st = new StringTokenizer(br.readLine());
        int maxLen = set.last() - set.first() - 1;
        System.out.println(maxLen);
        for (int m = 0; m < M; m++) {
            int tmp = Integer.parseInt(st.nextToken());
            int h = set.higher(tmp);
            int l = set.lower(tmp);

            int nowLen = h - l - 1;

//            if()

        }

    }
}
