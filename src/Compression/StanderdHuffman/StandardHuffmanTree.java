package Compression.StanderdHuffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StandardHuffmanTree {
    StandardHuffmanNode root;
    Map<Character, String> codeTable;
    String data;
    public StandardHuffmanTree(String data){
        this.data = data;
        root = null;
        codeTable = new HashMap<>();
    }

    private void buildStandardHuffmanTree(){
        Map<Character, Integer> freq = new HashMap<>();
        for(int i = 0; i < data.length(); i++){
            if(freq.get(data.charAt(i)) == null){
                freq.put(data.charAt(i), 1);
            }
            else{
                freq.put(data.charAt(i), freq.get(data.charAt(i)) + 1);
            }
        }

        PriorityQueue<StandardHuffmanNode> tree = new PriorityQueue<>(freq.size(),new HuffmanNodeComparator());

        for (Character c: freq.keySet()) {
            StandardHuffmanNode standardHuffmanNode = new StandardHuffmanNode();
            standardHuffmanNode.data = freq.get(c);
            standardHuffmanNode.c = c;
            standardHuffmanNode.left = null;
            standardHuffmanNode.right = null;
            tree.add(standardHuffmanNode);
        }

        while (tree.size() > 1){
            StandardHuffmanNode l = tree.peek();
            tree.poll();

            StandardHuffmanNode r = tree.peek();
            tree.poll();

            StandardHuffmanNode parent = new StandardHuffmanNode();
            parent.left = l;
            parent.right = r;
            parent.data = l.data + r.data;
            parent.c = '-';

            root = parent;
            tree.add(parent);
        }
    }

    private void createCodeTable(StandardHuffmanNode root, String code){
        if(root.left == null && root.right == null && Character.isLetter(root.c)){
            codeTable.put(root.c, code);
            return;
        }

        createCodeTable(root.left, code + "0");
        createCodeTable(root.right, code + "1");
    }

    public Map<Character, String> getCodeTable(){
        buildStandardHuffmanTree();
        createCodeTable(root, "");
        return codeTable;
    }
}

class HuffmanNodeComparator implements Comparator<StandardHuffmanNode> {
    @Override
    public int compare(StandardHuffmanNode x, StandardHuffmanNode y) {
        return x.data - y.data;
    }
}