package encoder;

public class main {
    public static void main(String[] args) {
        Encoder encoder = new Encoder();

        String text = "HELLO WORLD"; //plain text to test encode and decode

        String test1 = encoder.encode(text);
        System.out.println("Encoded Text: " + test1);

        String test2 = encoder.decode(test1);
        System.out.println("Decoded Text: " + test2);
    }
}
