package pfe.smi.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ElementCollection
    private List<String> options;

    private String correctAnswer;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam; // Lier à un examen
}