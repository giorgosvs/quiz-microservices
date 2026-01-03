package com.redux.question_service.service;

import com.redux.question_service.mapper.QuestionResponseMapper;
import com.redux.question_service.model.Question;
import com.redux.question_service.model.api.QuestionResponse;
import com.redux.question_service.model.api.QuizSubmitAnswersRequest;
import com.redux.question_service.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionResponseMapper questionResponseMapper;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findQuestionsByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionRepository.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepository.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionResponse>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionResponse> responses = new ArrayList<>();

        for(Integer id : questionIds) {
            responses.add(questionResponseMapper.mapQuestionToQuestionResponse(questionRepository.findById(id).get()));
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<QuizSubmitAnswersRequest> quizAnswers) {
        int right = 0;

        for(QuizSubmitAnswersRequest answer : quizAnswers) {
            Question question = questionRepository.findById(answer.getId()).orElseThrow(() -> new RuntimeException("Question not found."));
            if(answer.getResponse().equals(question.getRightAnswer()))
                right++;
        }


        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
