package pfe.smi.service;

import java.util.List;

import pfe.smi.model.Question;

public interface QuestionService {
    Question addQuestion(Question question);
    Question updateQuestion(Question question);
    List<Question> getAllQuestions();
    Question getQuestionById(Long id);
    void deleteQuestion(Long id);
}