import java.util.ArrayList;

public class lz77 {
    public static ArrayList<tag> compress(String data){
        ArrayList<tag> compressedData = new ArrayList<tag>();
        String window = "", current = "";

        for (int i = 0; i < data.length(); i++){
            current += data.charAt(i);

            if(window.lastIndexOf(current) == -1){
                addTag(compressedData, current, window);
                window += current;
                current = "";
            }
        }
        if(current.length() != 0)
            addTag(compressedData, current, window);
        return  compressedData;
    }

    public static String decompress(ArrayList<tag> data){
        // ************************* empty ****************************

        return null;
    }

    private static void addTag(ArrayList<tag> compressedData, String current, String window){
        int position = 0;
        int length = current.length() - 1;
        char next = current.charAt(current.length() - 1);

        if(current.length() > 1) {
            String matchString = current.substring(0, current.length() - 1);
            int lastIndexSearch = window.lastIndexOf(matchString);
            position = window.length() - lastIndexSearch;
        }


        compressedData.add(new tag(position, length, next));
    }
}
