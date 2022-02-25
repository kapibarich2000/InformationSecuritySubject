package app;
import java.util.Scanner;
public class App {
    public static Scanner scanInt = new Scanner(System.in);
    public static Scanner scanStr = new Scanner(System.in, "windows-1251"); // For RUS NEEDED chcp 1251 (java deb console)
    private static Human alice;
    private static Human bob;
    public static void main(String[] args) throws Exception{
        // Digits 4 and 3 will not appear in the cansole, therefore no one will know
        alice=new Human(4); // a = 4
        bob=new Human(3); // b = 3
        exchangeByDiffieHellman();
        exchangeEncryptedMessages();

        scanInt.close();
        scanStr.close();
    }

    private static void exchangeByDiffieHellman(){
        // STEP 1
        // Choice p and g
        System.out.println("Choice module p and base g"); // p = 23 g = 9
        int publicP, publicG;

        System.out.print("p: ");
        publicP=scanInt.nextInt();
        alice.p=publicP;
        bob.p=publicP;
        System.out.print("g: ");
        publicG=scanInt.nextInt();
        alice.g=publicG;
        bob.g=publicG;

        // STEP 2
        // Private key difened at the time of creating instances of Human 
        
        // STEP 3
        // Calculate own private key In Human's method @calculatePublicOwnKey()@
        // STEP 4
        // Exchange public key
        alice.setRecivedPublicKey(bob.getOwnPublicKey());
        bob.setRecivedPublicKey(alice.getOwnPublicKey());

        System.out.println("Bob sent "+bob.getOwnPublicKey()+" publicKey");
        System.out.println("Alice sent "+alice.getOwnPublicKey()+" publicKey");

        // STEP 5
        // Calculate common private key (9) In Human's method @calculatePrivateCommonKey()@
    }
    
    private static void exchangeEncryptedMessages() {
        int choice = 1;
        while(choice!=0){
            try {
                System.out.print("Alice's writing: ");
                String encryptedMessage=alice.getEncryptedMessage(scanStr.nextLine());
                System.out.println("Encrypted message from Alice: "+encryptedMessage); 
                System.out.println("Decrypted message (Bob): "+bob.getDecryptedMessage(encryptedMessage));

                System.out.print("Bob's writing: ");
                encryptedMessage=alice.getEncryptedMessage(scanStr.nextLine());
                System.out.println("Encrypted message from Bob: "+encryptedMessage); 
                System.out.println("Decrypted message (Alice): "+alice.getDecryptedMessage(encryptedMessage));
            
                System.out.println("If you want to exit enter 0\n"+
                                    "If you want to continue enter whatever\n"+
                                    ": ");
                choice=scanInt.nextInt();
                System.out.println("\n");
            }
            catch (ExceptionInInitializerError e) {
                e.printStackTrace();
                break;
            }
            
        }
    }
    
}
