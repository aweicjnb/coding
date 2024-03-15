package org.coding.trie;

class TrieNode {
    Boolean end;
    TrieNode[] next;

    public TrieNode(Boolean end) {
        next = new TrieNode[26];
        this.end = end;
    }
}

class Trie {
    TrieNode root;
    //初始化数的子节点
    public Trie() {
        root = new TrieNode(false);
    }

    public void insert(String str) {
        TrieNode cur = this.root;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new TrieNode(false);
            }
            cur = cur.next[c - 'a'];
            if (i == str.length() - 1) {
                cur.end = true;
            }
        }
    }

    public boolean search(String str) {
        TrieNode search = sear(str);
        return search!= null && search.end;
    }

    public boolean startWith(String str) {
        TrieNode search = sear(str);
        return search != null;
    }

    private TrieNode sear(String str) {
        TrieNode cur = this.root;
        for (int i = 0; i < str.length(); ++i) {
            TrieNode next = cur.next[str.charAt(i) - 'a'];
            if (next == null) {
                return null;
            }
            cur = next;
        }
        return cur;
    }
}
