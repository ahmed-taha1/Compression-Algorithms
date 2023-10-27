package Compression.LZ77;

import Compression.ICompression;

import java.util.ArrayList;

public class LZ77Compression implements ICompression {
    @Override
    public String compress(String data) {
        ArrayList<LZ77Tag> compressedData = new ArrayList<LZ77Tag>();
        String window = "", current = "";

        for (int i = 0; i < data.length(); i++){
            current += data.charAt(i);
            if(window.lastIndexOf(current) == -1){
                addTag(compressedData, current, window);
                window += current;
                current = "";
            }
        }
        if(!current.isEmpty()){
            addTag(compressedData, current, window);
        }
        return compressedData.toString();
    }
    @Override
    public String decompress(String data){
        ArrayList<LZ77Tag> tags = this.parseTags(data);
        String decompressed = "decompressed";
        /**
         * TODO implement ALGO
         */
        return decompressed;
    }
    private ArrayList<LZ77Tag> parseTags(String tagsString){
        return  new ArrayList<LZ77Tag>();
    }
    private static void addTag(ArrayList<LZ77Tag> compressedData, String current, String window){
        int position = 0;
        int length = current.length() - 1;
        char next = current.charAt(current.length() - 1);

        if(current.length() > 1) {
            String matchString = current.substring(0, current.length() - 1);
            int lastIndexSearch = window.lastIndexOf(matchString);
            position = window.length() - lastIndexSearch;
        }
        compressedData.add(new LZ77Tag(position, length, next));
    }
}
