package edu.kit.informatik;

/**
 * This class describes my answers to the week 2 D1-3 assignments.
 *
 * @author uogok
 *
 * @version 2.0
 */
public final class PasswordTools {

    private PasswordTools() {
        throw new AssertionError("Utility Classes must not be instantiated!");
    }

    // generates a string which contains all the lower case alphabets
    private static String generateSmallAlphabet() {

        // because of magic numbers warning
        final int alphabetLength = 26;
        final int startofASCIIalphabet = 97;

        String smallAlph = "";
        for (int i = 0; i < alphabetLength; i++) {

            smallAlph = smallAlph + (char) (startofASCIIalphabet + i);

        }

        return smallAlph;
    }

    // generates a string which contains all the upper case alphabets
    private static String generateBigAlphabet() {

        return generateSmallAlphabet().toUpperCase();
    }

    // concatenates("glues") a string to itself
    private static String concatenation(String password, int numberOfTimes, int remainder) {

        String glued = password;

        // concatenates("glues") the String to itself numberOfTimes times
        for (int i = 0; i <= numberOfTimes - 1; i++) {

            glued = glued + password;

        }
        // adds individual characters with the remainder
        for (int j = 0; j <= remainder - 1; j++) {

            glued = glued + password.charAt(j);

        }
        return glued;
    }

    /**
     * This method generates a password by taking the first letter of every word in
     * a sentence. The words are only separated by a " "(space).
     *
     * @param sentence  a password is made out of the first letters of this string
     * @param minLength the password must at least be as long as minLength
     * @return a password generated from the first letter of every word in the
     *         sentence
     */
    public static String generateFromSentence(String sentence, int minLength) {

        String password = ""; // declare the password

        // the first letter of the sentence is the first letter of the password as long
        // as its not a space
        if (sentence.charAt(0) != ' ') {

            password = password + sentence.charAt(0);

        }
        for (int i = 0; i <= sentence.length() - 1; i++) {

            // if the current char is a space then the next character must be a part of the
            // password as long as there is a character after this space
            if (sentence.charAt(i) == ' ') {

                // to avoid going out of the array/String
                if ((i + 1) <= (sentence.length() - 1) && (sentence.charAt(i + 1) != ' ')) {

                    password = password + sentence.charAt(i + 1);
                }

            }
        }

        if (minLength > password.length()) {

            int numberOfTimes = minLength / password.length();
            int remainder = minLength % password.length();

            password = concatenation(password, numberOfTimes - 1, remainder);
        }

        return password;

    }

    /**
     * This method checks if a password is secure enough. A passwords strength is
     * determined by 5 factors, which are the following contains special characters
     * contains Latin lower and upper case alphabets contains a Latin letter which
     * is followed by a number contains at least 8 characters This task could be
     * performed a lot easier with the method "matches​(String regex)" but I was not
     * sure if it was allowed so I have programmed without it
     *
     * @param password we have to check if the password is secure enough
     * @return returns true if the password is secure enough
     */
    public static boolean checkPassword(String password) {

        final int minLength = 8;
        boolean hasMinLength = false;
        boolean hasSmallAlphabet = false;
        boolean hasCapAlphabet = false;
        boolean hasSpecialCharacter = false;
        boolean hasNumber = false;

        // generates a String that contains all the special characters needed for the
        // password
        String specialCharacters = "$#?!_-=%";
        String numbers = "0123456789";

        if (password.length() >= minLength) {

            hasMinLength = true;

            String smallAlphabet = generateSmallAlphabet();
            String capAlphabet = generateBigAlphabet();

            for (int i = 0; i <= password.length() - 1; i++) {
                // check if the password has lower case Latin alphabets
                if ((!hasSmallAlphabet) && (smallAlphabet.indexOf(password.charAt(i)) != -1)) {

                    hasSmallAlphabet = true;

                }
                if ((!hasNumber) && (numbers.indexOf(password.charAt(i)) != -1)) { // check for existence of number

                    if (((i - 1) >= 0) && ((smallAlphabet.indexOf(password.charAt(i - 1)) != -1)
                            || (capAlphabet.indexOf(password.charAt(i - 1)) != -1))) { // check if predecessor is an
                                                                                       // alphabet

                        hasNumber = true;
                    }

                }
                // check if the password has capital Latin alphabets
                if ((!hasCapAlphabet) && (capAlphabet.indexOf(password.charAt(i)) != -1)) {

                    hasCapAlphabet = true;

                } // check if the password has special characters
                if ((!hasSpecialCharacter) && (specialCharacters.indexOf(password.charAt(i)) != -1)) {

                    hasSpecialCharacter = true;

                }

            }
        }
        // The password is only secure if all the conditions are true
        boolean secure = hasSmallAlphabet && hasSpecialCharacter && hasNumber && hasCapAlphabet && hasMinLength;

        return secure;

    }
    public static boolean isPasswordLeaked(String leakedDataset, String password) {

        
        if (password.length() == 0) {

            return true;
        }
        if (password.length() > leakedDataset.length()) {

            return false;

        }
        for (int l = 0; l <= leakedDataset.length() - 1; l++) {

            // check if the first character of the password is in the leaked data-set
            if (leakedDataset.charAt(l) == password.charAt(0)) {

                int matches = 1;

                for (int checker = 1; checker <= password.length() - 1; checker++) {

                    // to avoid going out of the array index/String and
                    // checks if the next characters of the password and leaked data-set are the
                    // same
                    if (((l + checker) <= (leakedDataset.length() - 1))
                            && (leakedDataset.charAt(l + checker) == password.charAt(checker))) {
                        // two conditions in the same if condition because the complexity of the code
                        // was level 5

                        matches = matches + 1;

                    }
                    if (matches == password.length()) {

                        return true;

                    }
                }

            }

        }
        return false;

    }
    
    public static void main(String args[]) {
        
        System.out.println(isPasswordLeaked("Programmieren_programmierenTutorien! ?Das_Studium_macht-Spass123;Password","mieren"));
    }

}
