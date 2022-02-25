package tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import app.EncryptionMachine;
import enums.MachineTools;

// encry array Eng: 
        // a A b B c C d D e E f F g G h H i I j J k K l L m M n N o O p P q Q r R s S t T u U v V w W x X y Y z Z
// (9) shuffled encry array Eng:
        // E f F g G h H I I j J k K l L m M n N o O p P q Q r R s S t T u U v V w W x X y Y z Z a A b B c C d D e

// encry array Rus: 
        // а А б Б в В г Г д Д е Е ё Ё ж Ж з З и И й Й к К л Л м М н Н о О п П р Р с С т Т у У ф Ф х Х ц Ц ч Ч ш Ш щ Щ ъ Ъ ы Ы ь Ь э Э ю Ю я Я
// (9) shuffled encry array Rus:
        // Д е Е ё Ё ж Ж з З и И й Й к К л Л м М н Н о О п П р Р с С т Т у У ф Ф х Х ц Ц ч Ч ш Ш щ Щ ъ Ъ ы Ы ь Ь э Э ю Ю я Я а А б Б в В г Г д

public class EncryptionMachineTests {

    @Test
    public void testAlphabetShuffle(){
        EncryptionMachine encryptionMachinePCK1 = new EncryptionMachine(1);
        assertEquals("", encryptionMachinePCK1.getMessage("", MachineTools.ENCRYPT));
        assertEquals("ab", encryptionMachinePCK1.getMessage("ZA", MachineTools.ENCRYPT)); // ENG
        assertEquals("е р", encryptionMachinePCK1.getMessage("Д П", MachineTools.ENCRYPT)); // RUS

        EncryptionMachine encryptionMachinePCK5 = new EncryptionMachine(20);
        assertEquals("Qy", encryptionMachinePCK5.getMessage("Go", MachineTools.ENCRYPT));
        assertEquals("ЪЮ", encryptionMachinePCK5.getMessage("РФ", MachineTools.ENCRYPT));

        // When PrivateCommonKey > Alphabet.length
        EncryptionMachine encryptionMachinePCK70 = new EncryptionMachine(70); 
        assertEquals("Rv", encryptionMachinePCK70.getMessage("Im", MachineTools.ENCRYPT)); // ENG   70%52 = 18
        assertEquals("дрлпв", encryptionMachinePCK70.getMessage("война", MachineTools.ENCRYPT)); // RUS   70%66 = 4
    }
    @Test
    public void testGetEncryptedCharacterEng(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals('E', encryptionMachine.getCharacter('a',MachineTools.ENCRYPT));
        assertEquals('Q', encryptionMachine.getCharacter('m',MachineTools.ENCRYPT));
        assertEquals('e', encryptionMachine.getCharacter('Z',MachineTools.ENCRYPT));
    }
    @Test
    public void testGetEncryptedCharacterRus(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals('Д', encryptionMachine.getCharacter('а',MachineTools.ENCRYPT));
        assertEquals('Ы', encryptionMachine.getCharacter('ч',MachineTools.ENCRYPT));
        assertEquals('д', encryptionMachine.getCharacter('Я',MachineTools.ENCRYPT));
    }
    @Test
    public void testGetDecryptedCharacterEng(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals('a', encryptionMachine.getCharacter('E',MachineTools.DECRYPT));
        assertEquals('O', encryptionMachine.getCharacter('t',MachineTools.DECRYPT));
        assertEquals('Z', encryptionMachine.getCharacter('e',MachineTools.DECRYPT));
    }
    @Test
    public void testGetDecryptedCharacterRus(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals('а', encryptionMachine.getCharacter('Д',MachineTools.DECRYPT));
        assertEquals('Л', encryptionMachine.getCharacter('р',MachineTools.DECRYPT));
        assertEquals('Я', encryptionMachine.getCharacter('д',MachineTools.DECRYPT));
    }
    @Test
    public void testGetEncryptedMessageEng(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals("ujfhj", encryptionMachine.getMessage("PEACE", MachineTools.ENCRYPT));
        assertEquals("sS AEV", encryptionMachine.getMessage("No war", MachineTools.ENCRYPT));
        assertEquals("EfsSDe", encryptionMachine.getMessage("aANozZ", MachineTools.ENCRYPT));
    }
    @Test
    public void testGetDecryptedMessageEng(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals("PEACE", encryptionMachine.getMessage("ujfhj", MachineTools.DECRYPT));
        assertEquals("No war", encryptionMachine.getMessage("sS AEV", MachineTools.DECRYPT));
        assertEquals("aANozZ", encryptionMachine.getMessage("EfsSDe", MachineTools.DECRYPT));
    }
    @Test
    public void testGetEncryptedMessageRus(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals("ДерНдГ", encryptionMachine.getMessage("аАЛйЯя", MachineTools.ENCRYPT));
        assertEquals("снх хДЕТЫМР", encryptionMachine.getMessage("МИР Рабочим", MachineTools.ENCRYPT));
        assertEquals("тИЦ ЁТНСИ", encryptionMachine.getMessage("Нет войне", MachineTools.ENCRYPT));

    }
    @Test
    public void testGetDecryptedMessageRus(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals("аАЛйЯя", encryptionMachine.getMessage("ДерНдГ", MachineTools.DECRYPT));
        assertEquals("МИР Рабочим", encryptionMachine.getMessage("снх хДЕТЫМР", MachineTools.DECRYPT));
        assertEquals("Нет войне", encryptionMachine.getMessage("тИЦ ЁТНСИ", MachineTools.DECRYPT));
    }
    @Test
    public void testGetEncryptedMessageRusEng(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals("снх ujfhj хДЕТЫМР", encryptionMachine.getMessage("МИР PEACE Рабочим", MachineTools.ENCRYPT));
    }
    @Test
    public void testGetDecryptedMessageRusEng(){
        EncryptionMachine encryptionMachine = new EncryptionMachine(9);
        assertEquals("МИР PEACE Рабочим", encryptionMachine.getMessage("снх ujfhj хДЕТЫМР", MachineTools.DECRYPT));
    }
}