package org.rvl.mind.algorithm.lookandsay;

public class LookAndSay {

  public String[] build(int n) {
    String[] strArr = new String[n];
    strArr[0] = "1";
    strArr[1] = "11";

    for (int i = 2; i < n; i++) {
      String str = generateString(strArr[i - 1]);
      strArr[i] = str;
    }

    return strArr;
  }

  private String generateString(String currentNumber) {

    String returnStr = "";
    char[] characters = (currentNumber + "$").toCharArray();

    int countOccurence = 1;
    for (int i = 1; i < characters.length; i++) {
      if (characters[i - 1] != characters[i]) {
        returnStr += countOccurence + "" + characters[i - 1];
        countOccurence = 1;
      } else {
        countOccurence++;
      }
    }

    return returnStr;
  }
}
