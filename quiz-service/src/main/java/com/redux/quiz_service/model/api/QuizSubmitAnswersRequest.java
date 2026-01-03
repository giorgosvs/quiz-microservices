package com.redux.quiz_service.model.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizSubmitAnswersRequest {

    private Integer id;
    private String response;

}
