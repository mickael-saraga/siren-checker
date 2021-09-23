import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Siren {
  
  private String code;
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  
  private String[] sirenCharactersArray;
  public String[] getSirenCharactersArray() {
    return sirenCharactersArray;
  }
  public void setSirenCharactersArray(String[] sirenCharactersArray) {
    this.sirenCharactersArray = sirenCharactersArray;
  }
  
  private List<String> sirenCharacters;
  public List<String> getSirenCharacters() {
    return sirenCharacters;
  }
  public void setSirenCharacters(List<String> sirenCharacters) {
    this.sirenCharacters = sirenCharacters;
  }
  
  private List<String> processedSiren;
  public List<String> getProcessedSiren() {
    return processedSiren;
  }
  public void setProcessedSiren(List<String> processedSiren) {
    this.processedSiren = processedSiren;
  }
  
  public Siren(String code) {
    this.code = code;
    this.sirenCharactersArray = code.split("");
    this.sirenCharacters = Arrays.asList(sirenCharactersArray);
  }
  
  public boolean hasCorrectLength() {
    return this.sirenCharacters.size() == 9;
  }
  
  public boolean hasOnlyNumbers() {
    return hasCorrectLength() && this.code.chars().allMatch(Character::isDigit);
  }
  
  public boolean isWellFormed() {
    return this.sirenCharacters.size() == 9 && this.code.chars().allMatch(Character::isDigit);
  }
  
  public void processSirenCode() {
    processedSiren = null;
    if (hasCorrectLength() && hasOnlyNumbers()) {
      this.processedSiren = IntStream.range(0, sirenCharactersArray.length)
                                     .mapToObj(i -> {
                                       if ((i + 1) % 2 == 0) {
                                         String even = String.valueOf(2 * Integer.parseInt(sirenCharactersArray[i]));
                                         if (even.length() > 1) {
                                           Integer sum = Arrays.stream(even.split(""))
                                                               .mapToInt(Integer::parseInt)
                                                               .reduce(0, Integer::sum);
                                           return String.valueOf(sum);
                                         } else {
                                           return even;
                                         }
                                       } else {
                                         return sirenCharactersArray[i];
                                       }
                                     })
                                     .collect(Collectors.toList());
    }
  }
  
  public boolean isValid() {
    if (isWellFormed()) {
      processSirenCode();
      int sirenCharactersSum = processedSiren.stream()
                                             .mapToInt(Integer::parseInt)
                                             .reduce(0, Integer::sum);
      return (sirenCharactersSum % 10) == 0;
    } else {
      return false;
    }
  }
  
}
