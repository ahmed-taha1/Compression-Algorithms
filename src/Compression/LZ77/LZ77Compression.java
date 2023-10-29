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
        ArrayList<LZ77Tag> tags = LZ77TagParser.parse(data);
        String decompressedData = "";
        for(int i = 0 ;i<tags.size();i++){
            int start = decompressedData.length() - tags.get(i).offset;
            int end = start + tags.get(i).length;
            decompressedData += decompressedData.substring(start,end);
            if(Character.isLetterOrDigit(tags.get(i).next)){
                decompressedData += tags.get(i).next;
            }
        }
        return decompressedData;
    }
    private static void addTag(ArrayList<LZ77Tag> compressedData, String current, String window){
        int length = current.length() - 1;
        char next = current.charAt(current.length() - 1);
        String matchString = current.substring(0, current.length() - 1);
        int lastIndexSearch = window.lastIndexOf(matchString);
        int position = window.length() - lastIndexSearch;

        // if all chars at the current found at the window that's mean the loop has been terminated and there is remaining chars
        if(window.lastIndexOf(current) != -1){
            next = '-';
            length += 1;
        }

        // first appear to char
        else if(current.length() == 1) {
            position = 0;
        }
        compressedData.add(new LZ77Tag(position, length, next));
    }
}
