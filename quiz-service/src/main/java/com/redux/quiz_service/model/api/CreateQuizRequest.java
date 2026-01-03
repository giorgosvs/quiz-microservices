package com.redux.quiz_service.model.api;

import lombok.Data;

@Data
public class CreateQuizRequest {
    String categoryName;
    Integer numQuestions;
    String title;

}
