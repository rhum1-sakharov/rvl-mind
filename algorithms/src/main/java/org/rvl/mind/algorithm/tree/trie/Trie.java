package org.rvl.mind.algorithm.tree.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Trie {
  private TrieNode root;

  public Trie() {
    this.root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode current = root;
    for (char c : word.toCharArray()) {
      current = current.getChildren().computeIfAbsent(c, k -> new TrieNode());
    }
    current.setEndOfWord(true);
  }

  public boolean containsWord(String word) {
    TrieNode current = root;
    char[] chars = word.toCharArray();
    for (char c : chars) {
      current = current.getChildren().get(c);
      if (Objects.isNull(current)) {
        return false;
      }
    }
    return current.isEndOfWord();
  }

  public TrieNode getPrefix(String word) {
    TrieNode current = root;
    char[] chars = word.toCharArray();
    for (char c : chars) {
      current = current.getChildren().get(c);
      if (Objects.isNull(current)) {
        return null;
      }
    }
    if (current.getChildren().size() > 0) {
      return current;
    }
    return null;
  }

  public boolean isEmpty() {
    return Objects.isNull(root);
  }

  public List<String> getWords(TrieNode prefix, String prefixWord) {
    List<String> words = new ArrayList<>();
    List<String> suffixWord = new ArrayList<>();
    suffixWord.add("");

    dfsRecursive(prefix, prefixWord,suffixWord, words);

    return words;
  }


  private void dfsRecursive(TrieNode current,String prefixWord,List<String> suffixWord, List<String> words )
  {
    for (Map.Entry<Character, TrieNode> entry : current.getChildren().entrySet()) {
      suffixWord.set(0, suffixWord.get(0)+entry.getKey());
      if(entry.getValue().isEndOfWord()){
        words.add(prefixWord+suffixWord.get(0));
      }
      if(entry.getValue().getChildren().size()==0){
        suffixWord.set(0, "");
      }
      dfsRecursive(entry.getValue(),prefixWord,suffixWord,words);
    }
  }

}
