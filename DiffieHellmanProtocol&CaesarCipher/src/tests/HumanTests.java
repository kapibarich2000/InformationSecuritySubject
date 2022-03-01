package tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import app.Human;

public class HumanTests{
    @Test
    public void testGetOwnPublicKey(){
        int g = 5;
        int p = 17;
        Human bob = new Human(2);
        bob.g=g;
        bob.p=p;
        assertEquals(8, bob.getOwnPublicKey()); 
    }
    @Test
    public void testCreateEncryptedMessage(){
        int g = 5;
        int p = 17;
        Human bob = new Human(2);
        bob.g=g;
        bob.p=p;
        bob.setRecivedPublicKey(11);
        // PrivateCommonKey should = 2  hence
        assertEquals("bA", bob.getEncryptedMessage("aZ"));
    }
    @Test
    public void testGetDecryptedMessage(){
        int g = 9;
        int p = 23;
        Human bob = new Human(3);
        bob.g=g;
        bob.p=p;
        bob.setRecivedPublicKey(14);
        // PrivateCommonKey should = 7  hence
        assertEquals("Коммуна:)", bob.getDecryptedMessage("оСППЦРГ"));
    }
}
