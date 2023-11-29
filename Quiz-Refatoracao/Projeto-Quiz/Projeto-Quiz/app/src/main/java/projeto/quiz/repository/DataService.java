package projeto.quiz.repository;

import java.util.List;

import projeto.quiz.domain.Pergunta;

public interface DataService {
    void add(Pergunta p);
    List<Pergunta> getAll();
    void update(Pergunta p);
    List<Pergunta> search(String termo);
    void remove(Pergunta p);
}
