package projeto.quiz.repository;
import projeto.quiz.domain.Pergunta;
import java.util.List;

public interface Armazenamento {
    void add(Pergunta p);
    List<Pergunta> getPerguntas();
} 