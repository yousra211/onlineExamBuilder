package pfe.smi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pfe.smi.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}