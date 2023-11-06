package Compression.LZW;

import java.util.ArrayList;
import java.util.Arrays;

class LZWParser {
    static ArrayList<LZWTag> parse(String compressedTags){
        ArrayList<LZWTag> tags = new ArrayList<>();
        for (int i = 0 ;i<compressedTags.length();i++){
            tags.add(new LZWTag((int)compressedTags.charAt(i)));
        }
        return tags;
    }
}