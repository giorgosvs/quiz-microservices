package com.redux.quiz_service.service;

import com.redux.quiz_service.feign.QuizInterface;
import com.redux.quiz_service.model.Quiz;
import com.redux.quiz_service.model.api.QuestionResponse;
import com.redux.quiz_service.model.api.QuizSubmitAnswersRequest;
import com.redux.quiz_service.repo.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, Integer numQuestions, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQuestions).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionResponse>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found!"));

        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionResponse>> questions = quizInterface.getQuestionsFromId(questionIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizSubmitAnswersRequest> quizAnswers) {
        ResponseEntity<Integer> score = quizInterface.getScore(quizAnswers);

        return score;
    }
}
