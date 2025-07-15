package com.example.bad_words_service.Services.impl;

import com.example.bad_words_service.Entity.BadWord;
import com.example.bad_words_service.Repository.BadWordsRepository;
import com.example.bad_words_service.Services.BadWordsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BadWordsServiceImpl implements BadWordsService {

    private final BadWordsRepository badWordsRepository;

    @Override
    public List<BadWord> getAllWords() {

        return badWordsRepository.findAll();
    }

    @Override
    public boolean isBadWord(String word) {

        return badWordsRepository.existsByWord(word);
    }

    @Transactional
    @Override
    public void addBadWord(BadWord badWord) {
        String word = badWord.getWord();
        if(badWordsRepository.existsByWord(word)) {
            throw new IllegalArgumentException("Word already exists");
        }
        badWordsRepository.save(badWord);
    }

    @Transactional
    @Override
    public void deleteBadWord(String word) {
        if(!badWordsRepository.existsByWord(word)) {
            throw new IllegalArgumentException("Word does not exist");
        }
        badWordsRepository.deleteByWord(word);
    }
}
