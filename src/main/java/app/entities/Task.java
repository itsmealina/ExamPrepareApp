package app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    // Это правильное поле для ответа
    private String answer;

    // Если нужен hint
    private String hint;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private PracticumTheme theme;

    // Геттеры и сеттеры
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getHint() { return hint; }
    public void setHint(String hint) { this.hint = hint; }

    public PracticumTheme getTheme() { return theme; }
    public void setTheme(PracticumTheme theme) { this.theme = theme; }
}
