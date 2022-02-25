package app;

public class NoSuchLetterInAlphabetException extends Exception {
    
    public NoSuchLetterInAlphabetException() {
    }

    public NoSuchLetterInAlphabetException(String message) {
        super(message);
    }
}
