package app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "practicum_tasks")
public class PracticumTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question; // то, что ты используешь в шаблоне

    private String answer; // правильный ответ

    // связь много задач → одна тема
    @ManyToOne
    @JoinColumn(name = "theme_id")
    private PracticumTheme theme;

    public PracticumTask() {}

    public PracticumTask(String question, String answer, PracticumTheme theme) {
        this.question = question;
        this.answer = answer;
        this.theme = theme;
    }

    public Long getId() { return id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public PracticumTheme getTheme() { return theme; }
    public void setTheme(PracticumTheme theme) { this.theme = theme; }
}
