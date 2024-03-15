package org.coding.test;

import java.util.ArrayList;
import java.util.List;

public class jz38 {
    private List<String> list = new ArrayList<>();
    private int n;

    public List<String> solution(int n) {
        this.n = n;
        dfs(new StringBuilder(), new boolean[n]);
        return list;
    }

    public void dfs(StringBuilder sb, boolean[] visited) {
        if (sb.length() == n) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                sb.append((char) ('a' + i));
                visited[i] = true;
                dfs(sb, visited);
                visited[i] = false;
                sb.delete(sb.length() - 1, sb.length());
            }
        }
    }


    public static void main(String[] args) {
        jz38 jz38 = new jz38();
        System.out.println(jz38.solution(3));
    }
}
