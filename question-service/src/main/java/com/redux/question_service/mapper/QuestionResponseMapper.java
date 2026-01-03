package com.redux.question_service.mapper;

import com.redux.question_service.model.Question;
import com.redux.question_service.model.api.QuestionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class QuestionResponseMapper {

    public abstract QuestionResponse mapQuestionToQuestionResponse(Question question);
}
