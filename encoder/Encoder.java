package encoder;
import java.util.*;

public class Encoder {
    private Map<Character,Character> shiftTable = new HashMap<>();
    private String referenceTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()+*,-./";

    //default constructor
    public Encoder() {
    }

    //method to initialize shift table using an offsetchar, type 1 = decode, else = encode
    private void initializeTable(char offSetChar, int type) {

        int offset = 0;

        //convert to uppercase
        offSetChar = Character.toUpperCase(offSetChar);

        for (int i=0; i<referenceTable.length(); i++) {
            if (offSetChar == referenceTable.charAt(i)) {
                offset = i;
            }
        }

        if (type == 1) {
            for (int i=0; i<referenceTable.length(); i++) {
                char shiftChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()+*,-./".charAt((i + offset) % 44);
                shiftTable.put(referenceTable.charAt(i), shiftChar);
            }
        }
        else {
            for (int i=0; i<referenceTable.length(); i++) {
                char shiftChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()+*,-./".charAt((i + offset) % 44);
                shiftTable.put(shiftChar, referenceTable.charAt(i));
            }
        }
    }

    public String encode(String plainText) {

        Scanner input = new Scanner(System.in);
        StringBuilder encoded = new StringBuilder();

        //ask user for offset character
        System.out.println("Please enter a single offset char: (If you enter a line, the first letter will be used as offset) ");
        char offSetChar = input.nextLine().charAt(0);
        input.close();

        //add offsetchar encoded message
        encoded.append(Character.toUpperCase(offSetChar));

        //initialize shifttable using offsetchar
        initializeTable(offSetChar,2);

        for (char c : plainText.toCharArray()) {
            if (shiftTable.containsValue(c)) {
                encoded.append(shiftTable.get(c));
            }
            else {
                encoded.append(c);
            }
        }
        

        return encoded.toString();
    }

    public String decode(String encodedText) {

        StringBuilder decoded = new StringBuilder();

        //check if parameter is empty
        if (encodedText.isEmpty()) {
            return "";
        }

        //first letter of encodedtext = offset
        char offSetChar = encodedText.charAt(0);
        initializeTable(offSetChar,1);

        //remove first letter of encodedtext
        encodedText = encodedText.substring(1);

        for (char c : encodedText.toCharArray()) {
            if (shiftTable.containsValue(c)) {
                decoded.append(shiftTable.get(c));
            }
            else {
                decoded.append(c);
            }
        }

        return decoded.toString();
    }
}