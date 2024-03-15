package org.coding.kmp;


 public class KMP {
     public static void main(String[] args) {
         System.out.println(indexOf("lanqianyaochengweijiagoushi", "jiagoushi"));
         System.out.println(indexOf("ababaabaabac", "abaabac"));
         int[] arr2 = getNextArr("abaabac");
         for (int i = 0; i < arr2.length; ++i) {
             System.out.print(arr2[i]);
         }

/*
            //验证手搓的前缀树对不对
            //perfect
         Trie trie = new Trie();
         trie.insert("sh");
         trie.insert("she");
         trie.insert("s");
         trie.insert("ash");
         System.out.println(trie.search("ah"));*/
     }
    public static int[] getNextArr(String pattern) {
        char[] charArray = pattern.toCharArray();
        int[] next = new int[pattern.length()];
        int index = 1;
        int tmp = 0;
        while (index < pattern.length()) {
            if (charArray[index] == charArray[tmp]) {
                next[index] = tmp + 1;
                index++;
                tmp++;
            } else if (tmp > 0) {
                //这里这一步的意义在哪里？
                //哦哦，这一步的意义在于：进一步优化跳跃的步数，而不是从头开始匹配
                tmp = next[tmp];
            } else {
                index++;
            }
        }
        return next;
    }

    public static int indexOf(String str, String pattern) {
        int index = 0;
        int tmp = 0;
        int[] next = getNextArr(pattern);
        while (index < str.length()) {
            if (str.charAt(index) == pattern.charAt(tmp)) {
                index++;
                tmp++;
            } else if (tmp > 0) {
                tmp = next[tmp - 1];
            } else {
                index++;
            }

            if (tmp == pattern.length()) {
                return index - tmp;
            }
        }
        return 0;
    }

     public static int getIndexOf2(String s1,String s2){
         if(s2.length()>s1.length() || s1==null || s2==null || s1.length()<1){
             return -1;
         }
         int[] nextArr = getNextArr(s2);
         char[] chs1 = s1.toCharArray();
         char[] chs2 = s2.toCharArray();
         int i1=0;
         int i2=0;
         while(i1<s1.length() && i2<s2.length()){
             if(chs1[i1]==chs2[i2]){
                 i1++;
                 i2++;
             }else if(nextArr[i2]!=-1){
                 i2=nextArr[i2];
             }else{
                 i1++;
             }
         }
         return i2==s2.length()?i1-i2:-1;
     }

     public static int[] getNextArr2(String s){
         if(s.length()==1)return new int[]{-1};
         int[] next=new int[s.length()];
         next[0]=-1;
         next[1]=0;
         int cn=0;//当前要和i-1比较的位置
         int i=2;
         char[] chs = s.toCharArray();
         while(i<chs.length){
             if(chs[cn]==chs[i-1]){
                 next[i++]=++cn; //i位置的值已经确定下来了
             }else if(cn>0){
                 cn=next[cn];
             }else{
                 next[i++]=0;//没有与当前位置相同的字符
             }
         }
         return next;
     }


/*     手搓的KMP
     public static int[] next(String str) {
         char[] charArray = str.toCharArray();
         int[] next = new int[str.length()];
         int index = 1;
         int count = 0;
         while (index < str.length()) {
             if (charArray[index] == charArray[count]) {
                 next[index] = count + 1;
                 index++;
                 count++;
             } else if (count > 0) {
                 count = next[count];
             } else {
                 index++;
             }
         }
         return next;
     }

     public static int pattern(String str, String patter) {
         int[] next = next(patter);
         char[] charArray = str.toCharArray();
         char[] patterCharArray = patter.toCharArray();
         int index = 0;
         int count = 0;
         while (index < str.length()) {
             if (charArray[index] == patterCharArray[count]) {
                 index++;
                 count++;
             } else if (count > 0) {
                 count = next[count - 1];
             } else {
                 index++;
             }
             if (count == patter.length()) {
                 return index - count;
             }
         }
         return -1;
     }*/

}


class TreeNode {
     char value;
     TreeNode[] next;
     Boolean end;
     public TreeNode(char value) {
         next = new TreeNode[26];
         this.value = value;
     }
}

class Trie {
     public final TreeNode root;

     public Trie() {
         root = new TreeNode('a');
     }

     void insert(String str) {
         TreeNode node = root;
         for (int i = 0; i < str.length(); ++i) {
             char c = str.charAt(i);
             if (node.next[c - 'a'] == null) {
                 node.next[c - 'a'] = new TreeNode(c);
             }
             node = node.next[c - 'a'];
             if (i == str.length() - 1) {
                 node.end = true;
             }
         }
     }

     TreeNode sear(String str) {
         TreeNode node = root;
         for (int i = 0; i < str.length(); ++i) {
             char c = str.charAt(i);
             if (node.next[c - 'a'] == null) {
                 return null;
             }
             node = node.next[c - 'a'];
         }
         return node;
     }

     boolean search(String str) {
         TreeNode sear = sear(str);
         return sear != null && sear.end;
     }

     boolean startWith(String str) {
         TreeNode sear = sear(str);
         return sear != null;
     }



}

