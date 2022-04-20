package org.rvl.mind.algorithm.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * One Away: There are three types of edits that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to check if they
 * are
 * one edit (or zero edits) away.
 */
public class OneAway {

  public boolean isOneAway(String a, String b) {

    if (Math.abs(a.length() - b.length()) > 1) {
      return false;
    }

    Map<Character, Integer> charsAMap = initMap(a);
    Map<Character, Integer> charsBMap = initMap(b);

    int nbEdit = 0;
    for (Map.Entry<Character, Integer> characterIntegerEntry : charsAMap.entrySet()) {
      Character key = characterIntegerEntry.getKey();
      Integer cntA = charsAMap.get(key);
      Integer cntB = Objects.isNull(charsBMap.get(key)) ? 0 : charsBMap.get(key);
      if (Math.abs(cntA - cntB) >= 1) {
        nbEdit++;
      }
      if (nbEdit > 1) {
        return false;
      }
    }
    return true;
  }

  private Map<Character, Integer> initMap(String str) {
    Map<Character, Integer> charsMap = new HashMap<>();
    char[] chars = str.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      charsMap.compute(chars[i], (k, v) -> Objects.isNull(v) ? 1 : v + 1);
    }
    return charsMap;
  }

}
