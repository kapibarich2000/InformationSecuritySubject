package app;
import enums.MachineTools;
import java.lang.Math;
public class Human{
    public int p;
    public int g;
    private int privateOwnKey; // MAIN ATTRIBUTE. No one knows about this
    private int ownPublicKey;
    public int recivedPublicKey;
    private int privateCommonKey; // For Message encryption
    private EncryptionMachine encryptionMachine;

    private void calculatePublicOwnKey(){
        this.ownPublicKey=(int)(Math.pow(g, privateOwnKey)%p);
    }

    private void calculatePrivateCommonKey(){
        privateCommonKey = (int)Math.pow(recivedPublicKey, privateOwnKey)%p;
        encryptionMachine = new EncryptionMachine(privateCommonKey); // creating an encription machine
    }

    public void setRecivedPublicKey(int recivedPublicKey) {
        this.recivedPublicKey = recivedPublicKey;
        calculatePrivateCommonKey();
    }

    public int getOwnPublicKey() {
        calculatePublicOwnKey();
        return ownPublicKey;
    }

    public Human(int privateKey){
        this.privateOwnKey=privateKey;
    }
    public String getEncryptedMessage(String message){
        return encryptionMachine.getMessage(message,MachineTools.ENCRYPT);
    }
    public String getDecryptedMessage(String encryptedMessage){
        return encryptionMachine.getMessage(encryptedMessage, MachineTools.DECRYPT);
    }
}