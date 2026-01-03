package com.redux.question_service.controller;

import com.redux.question_service.model.Question;
import com.redux.question_service.model.api.QuestionResponse;
import com.redux.question_service.model.api.QuizSubmitAnswersRequest;
import com.redux.question_service.service.QuestionService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    Environment environment; //Spring Environment to check what microservice is running

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }

    //generate quiz
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    //getQuestions (questionids)
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionResponse>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsFromId(questionIds);
    }

    //getScore
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizSubmitAnswersRequest> answers ){
        //check load balancer
//        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getScore(answers);
    }

}
