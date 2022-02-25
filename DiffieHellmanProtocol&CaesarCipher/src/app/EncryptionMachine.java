package app;
import enums.*;

public class EncryptionMachine {
    private int privateCommonKey;
    private int numberOfLettersInEncryAlphabetEng;
    private int numberOfLettersInEncryAlphabetRus;
    private char[] AlphabetEng=new char[] {'a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l',
    'L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','w','W','x','X','y','Y','z','Z'};
    private char[] AlphabetRus=new char[] {'а','А','б','Б','в','В','г','Г','д','Д','е','Е','ё','Ё','ж','Ж','з','З','и','И','й','Й','к','К','л',
    'Л','м','М','н','Н','о','О','п','П','р','Р','с','С','т','Т','у','У','ф','Ф','х','Х','ц','Ц','ч','Ч','ш','Ш','щ','Щ','ъ','Ъ','ы','Ы','ь',
    'Ь','э','Э','ю','Ю','я','Я'};
    private char[] shuffledAlphabetEng;
    private char[] shuffledAlphabetRus;
//    private Languages characterLanguage;
    private char [] currentAlphabed; // 
    private char [] currentShuffledAlphabed; // 
    private int charPossition;

    public EncryptionMachine(int privateCommonKey){
        numberOfLettersInEncryAlphabetEng=AlphabetEng.length;
        numberOfLettersInEncryAlphabetRus=AlphabetRus.length;
        this.privateCommonKey=privateCommonKey;
        shuffleAlphabets();
    }
    private void shuffleAlphabets(){
        shuffledAlphabetEng = new char[numberOfLettersInEncryAlphabetEng];
        shuffledAlphabetRus = new char[numberOfLettersInEncryAlphabetRus];
        int privateCommonKeyForShuffleEng=privateCommonKey%numberOfLettersInEncryAlphabetEng;
        int privateCommonKeyForShuffleRus=privateCommonKey%numberOfLettersInEncryAlphabetRus;

        for(int i=0;i<privateCommonKeyForShuffleEng;i++){ // Shuffle the lasn privateCommonKey numbers ENG
            shuffledAlphabetEng[numberOfLettersInEncryAlphabetEng-privateCommonKeyForShuffleEng+i]=AlphabetEng[i];
        }
        for(int i=0;i<privateCommonKeyForShuffleRus;i++){ // Shuffle the lasn privateCommonKey numbers RUS
            shuffledAlphabetRus[numberOfLettersInEncryAlphabetRus-privateCommonKeyForShuffleRus+i]=AlphabetRus[i];
        }
        for(int i=0;i<numberOfLettersInEncryAlphabetEng-privateCommonKeyForShuffleEng;i++){ //Eng
            shuffledAlphabetEng[i]=AlphabetEng[i+privateCommonKeyForShuffleEng];
        }
        for(int i=0;i<numberOfLettersInEncryAlphabetRus-privateCommonKeyForShuffleRus;i++){ //Rus
            shuffledAlphabetRus[i]=AlphabetRus[i+privateCommonKeyForShuffleRus];
        }
    }
    private void figureOutLanguage(char ch) throws NoSuchLetterInAlphabetException{
        char searchedChar = Character.toUpperCase(ch);
        if(searchedChar>='A'&&searchedChar<='Z'){ // English alphabet
//            characterLanguage = Language.Eng;
            currentAlphabed = AlphabetEng;
            currentShuffledAlphabed = shuffledAlphabetEng;}
        else if(searchedChar>='А'&&searchedChar<='Я'||searchedChar=='ё'||searchedChar=='Ё'){ // Rus alphabet
//            characterLanguage = Language.Rus;
            currentAlphabed = AlphabetRus;
            currentShuffledAlphabed = shuffledAlphabetRus;}
        else
            throw new NoSuchLetterInAlphabetException("No the \'"+ch+"\' char in available Languages");
    }
    private void findCharPosition(char ch, MachineTools tool) throws NoSuchLetterInAlphabetException{ //Eng
        this.charPossition=0;
        if(tool==MachineTools.DECRYPT){
            for(int i=0; i<currentShuffledAlphabed.length;i++)
                if(currentShuffledAlphabed[i]==ch){
                    charPossition=i;
                    break;
                }
        }
        else if(tool==MachineTools.ENCRYPT){
            for(int i=0; i<currentAlphabed.length;i++)
                if(currentAlphabed[i]==ch){
                    charPossition=i;
                    break;
                }
        }
        if(charPossition==currentAlphabed.length)
            throw new NoSuchLetterInAlphabetException("No the \'"+ch+"\' char in encry Alphabet");
    }

    public char getCharacter(char ch, MachineTools tool){
        try {
            figureOutLanguage(ch); // Programm choice language for work
            findCharPosition(ch,tool);// Find char in aphabet or in shuffled alphabed
            if(tool==MachineTools.DECRYPT)
                return currentAlphabed[charPossition];
            else if(tool==MachineTools.ENCRYPT)
                return currentShuffledAlphabed[charPossition];
            else
                throw new ExceptionInInitializerError("Error in program. No such action"); 
            }
        catch (NoSuchLetterInAlphabetException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Error in program");
        }
    }

    public String getMessage(String message,MachineTools tool){
        char[] processedMessage = new char [message.length()];
        char ch;
        for(int i=0;i<message.length();i++){
            ch=message.charAt(i);
            if(ch==' '){
                processedMessage[i]=' ';
                continue;
            }
            processedMessage[i]=getCharacter(ch,tool);
        }
        return new String(processedMessage);
    }
}
