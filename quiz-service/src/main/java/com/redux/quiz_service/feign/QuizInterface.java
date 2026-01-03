package com.redux.quiz_service.feign;

import com.redux.quiz_service.model.api.QuestionResponse;
import com.redux.quiz_service.model.api.QuizSubmitAnswersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    //generate quiz
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);

    //getQuestions (questionids)
    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionResponse>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    //getScore
    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizSubmitAnswersRequest> answers );
}
