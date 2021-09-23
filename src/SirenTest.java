import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SirenTest {
  
  @Test
  void isSirenCorrectlyInitialized() {
    Siren s = new Siren("610357891");
    assertEquals("610357891", s.getCode());
    assertEquals(Arrays.asList("610357891".split("")), s.getSirenCharacters());
  }
  
  @Test
  void isSirenComposedOnlyByNumbers() {
    Siren sirenWithOnlyNumbers = new Siren("153613546");
    assertTrue(sirenWithOnlyNumbers.hasOnlyNumbers());
    Siren sirenWithNumbersButIncorrectLength = new Siren("15361356");
    assertFalse(sirenWithNumbersButIncorrectLength.hasOnlyNumbers());
    Siren sirenWithNotOnlyNumbers = new Siren("2A515X105");
    assertFalse(sirenWithNotOnlyNumbers.hasOnlyNumbers());
  }
  
  @Test
  void isSirenCorrectlySized() {
    Siren sirenBadLength = new Siren("198645");
    assertFalse(sirenBadLength.hasCorrectLength());
    Siren sirenGoodLength = new Siren("138942369");
    assertTrue(sirenGoodLength.hasCorrectLength());
    Siren sirenNoLength = new Siren("");
    assertFalse(sirenNoLength.hasCorrectLength());
  }
  
  @Test
  void isSirenWellFormed() {
    Siren sirenNoLength = new Siren("");
    assertFalse(sirenNoLength.isWellFormed());
    Siren sirenBadLength = new Siren("198645");
    assertFalse(sirenBadLength.isWellFormed());
    Siren sirenGoodLength = new Siren("138942369");
    assertTrue(sirenGoodLength.isWellFormed());
    Siren sirenNoWellFormed = new Siren("_38x4ee30");
    assertFalse(sirenNoWellFormed.isWellFormed());
  }
  
  @Test
  void isSirenCorrectlyProcessed() {
    Siren correctSiren = new Siren("210357319");
    correctSiren.processSirenCode();
    assertNotNull(correctSiren.getProcessedSiren());
    Siren incorrectSiren = new Siren("118P3T041");
    incorrectSiren.processSirenCode();
    assertNull(incorrectSiren.getProcessedSiren());
  }
  
  @Test
  void isSirenCompletelyValid() {
    Siren sirenNotValid = new Siren("210357319");
    assertFalse(sirenNotValid.isValid());
    Siren sirenValid = new Siren("732829320");
    assertTrue(sirenValid.isValid());
  }
  
}