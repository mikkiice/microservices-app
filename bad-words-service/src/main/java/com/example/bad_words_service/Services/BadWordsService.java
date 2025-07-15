package com.example.bad_words_service.Services;


import com.example.bad_words_service.Entity.BadWord;

import java.util.List;

public interface BadWordsService {

    List<BadWord> getAllWords();
    boolean isBadWord(String word);
    void addBadWord(BadWord badWord);
    void deleteBadWord(String word);

}
