package nl.tomasluken.helloworld3;

/**
 * Created by Luken on 12-9-2016.
 */
public class TextEncryptor {
    public String Decrypt(String encryptedString) {
        return new StringBuilder(encryptedString).reverse().toString();
    }
}
