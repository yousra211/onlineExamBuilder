package pfe.smi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pfe.smi.model.Question;
import pfe.smi.service.QuestionService;
import pfe.smi.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        Optional<Question> existingQuestion = questionRepository.findById(question.getId());
        if (existingQuestion.isPresent()) {
            return questionRepository.save(question);
        } else {
            throw new RuntimeException("Question not found with id " + question.getId());
        }
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Question not found with id " + id));
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}