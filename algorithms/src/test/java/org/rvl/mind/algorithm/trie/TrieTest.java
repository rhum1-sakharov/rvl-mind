package org.rvl.mind.algorithm.trie;

import static com.google.common.truth.Truth.*;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rvl.mind.algorithm.tree.trie.Trie;
import org.rvl.mind.algorithm.tree.trie.TrieNode;

class TrieTest {

  private Trie trie;

  @BeforeEach
  void setUp() {
    trie = new Trie();
    trie.insert("Programming");
    trie.insert("is");
    trie.insert("a");
    trie.insert("way");
    trie.insert("of");
    trie.insert("life");
  }

  @Test
  void whenInsert_thenTrieIsNotEmpty() {
    trie.insert("!");

    assertThat(trie.isEmpty()).isFalse();
  }

  @Test
  void givenProgramming_whenContainsWord_thenReturnTrue() {
    // when
    boolean result = trie.containsWord("Programming");

    // then
    assertThat(result).isTrue();
  }

  @Test
  void givenProgrammings_whenContainsWord_thenReturnFalse() {
    // when
    boolean result = trie.containsWord("Programmings");

    // then
    assertThat(result).isFalse();
  }

  @Test
  void whenIsPrefix_thenBadSet(){
    // given
    trie = new Trie();
    String[] words = new String[]{"aab","defgab","abcde","aabcde","cedaaa","bbbbbbbbb","jabjjjad"};
    for(String word:words){
      trie.insert(word);
    }

    // when
    boolean isPrefix=false;
    for(String word:words){
      if(Objects.nonNull(trie.getPrefix(word))){
        isPrefix=true;
        System.out.println(word);
      }
    }

    // then
    assertThat(isPrefix).isTrue();
  }

  @Test
  void whenGetWords_thenReturnWords(){
    // given
    trie = new Trie();
    String[] words = new String[]{"aab", "aac","aabcde","aabee","jjjad"};
    for(String word:words){
      trie.insert(word);
    }
    TrieNode prefixNode = trie.getPrefix("aab");

    // when
    List<String> trieWords = trie.getWords(prefixNode, "aab");

    // then
    assertThat(trieWords).containsExactly("aabcde","aabee");
  }
}