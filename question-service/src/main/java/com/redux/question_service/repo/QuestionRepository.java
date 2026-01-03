package com.redux.question_service.repo;

import com.redux.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer > {

    List<Question> findQuestionsByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQuestions;", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category,Integer numQuestions);
}
