package org.coding.trie;


import java.util.LinkedList;
import java.util.Queue;

public class ACNode {
    char data; // 节点字符
    ACNode[] children; // 子节点数组
    ACNode fail; // 失败指针
    boolean isEndingChar; // 结尾字符
    String word; // 匹配的单词（可选）
    int count; // 单词出现的次数

    public ACNode(char data) {
        this.data = data;
        this.children = new ACNode[26]; // 假设只包含小写字母
        this.fail = null;
        this.isEndingChar = false;
        this.word = null;
        this.count = 0;
    }
}

class ACWithFrequency {
    private ACNode root;

    public ACWithFrequency() {
        root = new ACNode('/');
    }

    // 构建 AC 自动机
    public void buildAC(String[] keywords) {
        for (String keyword : keywords) {
            insert(keyword);
        }
        buildFailPointer();
    }

    // 向 Trie 树中插入字符串
    private void insert(String text) {
        ACNode p = root;
        for (char ch : text.toCharArray()) {
            int index = ch - 'a';
            if (p.children[index] == null) {
                p.children[index] = new ACNode(ch);
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
        p.word = text;
    }

    // 构建失败指针
    private void buildFailPointer() {
        Queue<ACNode> queue = new LinkedList<>();
        root.fail = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            ACNode p = queue.poll();
            for (int i = 0; i < 26; ++i) {
                ACNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                if (p == root) {
                    pc.fail = root;
                } else {
                    ACNode q = p.fail;
                    while (q != null) {
                        ACNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.offer(pc);
            }
        }
    }

    // AC 自动机匹配字符串，并统计词频
    public void match(String text) {
        int n = text.length();
        ACNode p = root;
        for (int i = 0; i < n; ++i) {
            int index = text.charAt(i) - 'a';
            while (p.children[index] == null && p != root) {
                p = p.fail;
            }
            p = p.children[index];
            if (p == null) {
                p = root;
            }
            ACNode tmp = p;
            while (tmp != root) {
                if (tmp.isEndingChar) {
                    tmp.count++; // 更新词频
//                    System.out.println("匹配到关键词：" + tmp.word + "，出现次数：" + tmp.count);
                }
                tmp = tmp.fail;
            }
        }
    }


    public static void main(String[] args) {
        /**
         * ac自动机
         */
        ACWithFrequency ac = new ACWithFrequency();
        String[] keywords = {"she", "he", "her", "hers"};
        ac.buildAC(keywords);
        ac.match("ashehed");
    }

}


