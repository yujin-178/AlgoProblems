package TreeSet.가까운_숫자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int maxLen = Integer.MAX_VALUE;
        StringBuilder sb =
                new StringBuilder();
        for (int n = 0; n < N; n++) {
            int next = Integer.parseInt(st.nextToken());
            set.add(next);
            if (set.lower(next) != null) {
                int tmp = set.lower(next);
                maxLen = Math.min(maxLen, next - tmp);
            }

            if (set.higher(next) != null) {
                int tmp = set.higher(next);
                maxLen = Math.min(maxLen, tmp - next);
            }
            sb.append(maxLen).append("\n");
        }

        System.out.println(sb);


    }
}
