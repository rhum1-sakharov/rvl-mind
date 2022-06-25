package org.rvl.mind.algorithm.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {

  private static String isPalindrome(String input) {
    StringBuilder sb = new StringBuilder();
    sb.append("%s ");
    

    return String.format(sb.toString(), input);
  }

  public static void main(String[] args) throws IOException {

    // Enter data using BufferReader
    BufferedReader reader = new BufferedReader(
      new InputStreamReader(System.in));

    // Reading data using readLine
    String input = reader.readLine();

    // Printing the read line
    System.out.println(isPalindrome(input));

  }

}
