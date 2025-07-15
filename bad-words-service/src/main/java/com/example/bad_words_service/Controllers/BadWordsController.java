package com.example.bad_words_service.Controllers;


import com.example.bad_words_service.Entity.BadWord;
import com.example.bad_words_service.Services.BadWordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bad-words")
@RequiredArgsConstructor
public class BadWordsController {

    private final BadWordsService badWordsService;

    @GetMapping
    public ResponseEntity<List<BadWord>> getBadWords() {
        return ResponseEntity.ok(badWordsService.getAllWords());
    }
    @PostMapping
    public ResponseEntity<BadWord> createBadWord(@RequestBody BadWord badWord) {
        badWordsService.addBadWord(badWord);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    public ResponseEntity<BadWord> deleteBadWord(@RequestParam String badWord) {
        badWordsService.deleteBadWord(badWord);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/exist")
    public ResponseEntity<Boolean> existBadWord(@RequestParam("badWord") String badWord) {
        return ResponseEntity.ok(badWordsService.isBadWord(badWord));
    }

}
