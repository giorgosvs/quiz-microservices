package com.redux.quiz_service.controller;

import com.redux.quiz_service.model.api.CreateQuizRequest;
import com.redux.quiz_service.model.api.QuestionResponse;
import com.redux.quiz_service.model.api.QuizSubmitAnswersRequest;
import com.redux.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody CreateQuizRequest request){
        return quizService.createQuiz(request.getCategoryName(), request.getNumQuestions(), request.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionResponse>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizSubmitAnswersRequest> answers) {
        return quizService.calculateResult(id,answers);
    }

}
