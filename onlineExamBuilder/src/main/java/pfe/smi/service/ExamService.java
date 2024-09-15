package pfe.smi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pfe.smi.model.Exam;
import pfe.smi.repository.ExamRepository;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }
}