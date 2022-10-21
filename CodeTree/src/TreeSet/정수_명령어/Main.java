package TreeSet.정수_명령어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int L = Integer.parseInt(br.readLine());
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < L; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String c = st.nextToken();
                if (c.equals("I")) {
                    set.add(Integer.parseInt(st.nextToken()));
                } else if (c.equals("D")) {
                    int nNum = Integer.parseInt(st.nextToken());
                    if (set.isEmpty())
                        continue;
                    if (nNum == 1) {
                        set.remove(set.last());
                    } else {
                        set.remove(set.first());
                    }
                }

            }
            sb.append(set.size() == 0 ? "EMPTY" : set.last() + " " + set.first()).append("\n");
        }
        System.out.println(sb);


    }
}
