package Compression.StanderdHuffman;

import Compression.ICompression;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StandardHuffmanCompression implements ICompression {

    @Override
    public String compress(String data) {
        StandardHuffmanTree standardHuffmanTree = new StandardHuffmanTree(data);
        Map<Character, String> codeTable = standardHuffmanTree.getCodeTable();
        return codeTable.toString();
    }

    @Override
    public String decompress(String data) {
        return null;
    }


}
