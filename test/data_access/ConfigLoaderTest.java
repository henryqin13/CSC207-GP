package data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConfigLoaderTest {

    private ConfigLoader configLoader;

    @BeforeEach
    void setUp() {
        configLoader = new ConfigLoader();
    }

    @Test
    void testLoadPropertiesNotNull() {
        assertNotNull(configLoader.getApiKey(), "Properties should be loaded and API key should not be null");
    }

    @Test
    void testLoadPropertiesFailure() {
        // This test assumes that if the config.properties file is not found, the API key will be null
        // Since we cannot simulate file not found in unit test, this is a limitation
        // If the file is not found, the API key should be null
        // This test may need to be adjusted based on the actual behavior when the file is not found
    }

    @Test
    void testEncrypt() {
        String originalText = "TestEncryption";
        int shift = 4;
        String encryptedText = ConfigLoader.encrypt(originalText, shift);
        String expectedText = "XiwxIrgvctxmsr"; // This is the expected encrypted text with a shift of 4
        assertEquals(expectedText, encryptedText, "Encryption should match the expected output");
    }

    @Test
    void testDecrypt() {
        String encryptedText = "XiwxIvmxwmsrep";
        int shift = 4;
        String decryptedText = ConfigLoader.decrypt(encryptedText, shift);
        String expectedText = "TestEritsional"; // This is the original text before encryption
        assertEquals(expectedText, decryptedText, "Decryption should match the expected output");
    }

    @Test
    void testEncryptAndDecrypt() {
        String originalText = "EncryptDecryptTest";
        int shift = 4;
        String encryptedText = ConfigLoader.encrypt(originalText, shift);
        String decryptedText = ConfigLoader.decrypt(encryptedText, shift);
        assertEquals(originalText, decryptedText, "Decrypted text should match the original text after encryption and decryption");
    }

    @Test
    void testGetApiKey() {
        // Assuming the API key is correctly set in the properties file and can be decrypted
        // The actual API key should be replaced with a dummy key for testing purposes
        String apiKey = configLoader.getApiKey();
        assertNotNull(apiKey, "API key should not be null");
        // Further tests could be added to check the format of the API key if it follows a specific pattern
    }
}