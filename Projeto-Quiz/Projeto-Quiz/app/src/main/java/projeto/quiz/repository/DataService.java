package projeto.quiz.Repository;

import java.util.List;

import projeto.quiz.Refatorado.Pergunta;

public interface DataService {
    void add(Pergunta p);
    List<Pergunta> getAll();
    void update(Pergunta p);
    List<Pergunta> search(String termo);
    void remove(Pergunta p);
}
