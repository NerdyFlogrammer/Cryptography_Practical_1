import javafx.collections.transformation.SortedList;


public class main {
    public static void main(String[] args) {

        String plainText = "helloworld";
        //String plainText = "publicvoidmain";
        //String plainText = "thisisnotamiracle";
        String encryptionKey = "24153";


        Cipher cipher = new Cipher();
        String encryptedMessage = cipher.encrypt(encryptionKey, plainText);
        String decryptionKey = Cipher.generateDecryptionKey(encryptionKey);
        String decryptedMessage = cipher.decrypt(decryptionKey, encryptedMessage);

        System.out.println("Encryption key: " + encryptionKey);
        System.out.println("Encrypted message: " + encryptedMessage);
        System.out.println("Decryption key: " + decryptionKey);
        System.out.println("Decrypted message: " + decryptedMessage);


    }
}
