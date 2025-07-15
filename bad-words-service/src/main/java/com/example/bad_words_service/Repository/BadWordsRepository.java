package com.example.bad_words_service.Repository;


import com.example.bad_words_service.Entity.BadWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BadWordsRepository extends JpaRepository<BadWord, Long> {

    void deleteByWord(String word);
    boolean existsByWord(String word);

}
