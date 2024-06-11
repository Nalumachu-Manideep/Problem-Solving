class DataEncryptionSystem {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-=_+[]{}|;:',.<>?/~`";
    private static final int SHIFT = 4; // Example shift for the cipher

    public static String encode(String message) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char ch : message.toCharArray()) {
            int index = CHARACTERS.indexOf(ch);
            if (index != -1) {
                int newIndex = (index + SHIFT) % CHARACTERS.length();
                encodedMessage.append(CHARACTERS.charAt(newIndex));
            } else {
                encodedMessage.append(ch); // Preserve characters not in CHARACTERS
            }
        }
        return encodedMessage.toString();
    }

    public static String decode(String message) {
        StringBuilder decodedMessage = new StringBuilder();
        for (char ch : message.toCharArray()) {
            int index = CHARACTERS.indexOf(ch);
            if (index != -1) {
                int newIndex = (index - SHIFT + CHARACTERS.length()) % CHARACTERS.length();
                decodedMessage.append(CHARACTERS.charAt(newIndex));
            } else {
                decodedMessage.append(ch); // Preserve characters not in CHARACTERS
            }
        }
        return decodedMessage.toString();
    }

    public static void main(String[] args) {
        String originalMessage = "Hello, Manideep! Your secret code is 9398.";
        String encodedMessage = encode(originalMessage);
        String decodedMessage = decode(encodedMessage);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encoded Message: " + encodedMessage);
        System.out.println("Decoded Message: " + decodedMessage);
    }
}

