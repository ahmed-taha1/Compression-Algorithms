package Compression.LZ77;

import java.util.ArrayList;
import java.util.Stack;

class LZ77TagParser {
    static ArrayList<LZ77Tag> parse(String compressedData){
        ArrayList<LZ77Tag>tags = new ArrayList<>();
        Stack<String>stack = new Stack<>();
        String lastEntry = "";
        for(int i = 0 ;i<compressedData.length();i++){
            if (compressedData.charAt(i) == '>'){
                stack.push(lastEntry);
                lastEntry = "";
                String letter = stack.pop();
                String length = stack.pop();
                String offset = stack.pop();
                tags.add(new LZ77Tag(Integer.parseInt(offset),Integer.parseInt(length),letter.charAt(0)));
                System.out.println("tags"+tags.get(tags.size()-1));
            }else if(compressedData.charAt(i) == ','){
                stack.push(lastEntry);
                lastEntry = "";
            }else if (compressedData.charAt(i) != '<'){
                lastEntry += compressedData.charAt(i);
            }
        }
        return tags;
    }
}
