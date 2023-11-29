package projeto.quiz.repository;

import projeto.quiz.domain.Pergunta;

import java.util.ArrayList;
import java.util.List;



public class InMemoryDataService implements DataService {
    protected List<Pergunta> perguntas = new ArrayList<>();
    @Override
    public void add(Pergunta p) {
        perguntas.add(p);
    }

    @Override
    public List<Pergunta> getAll() {
        return perguntas;
    }

    @Override
    public void update(Pergunta p) {
        int index = perguntas.indexOf(p);
        perguntas.set(index, p);
    }

    @Override
    public List<Pergunta> search(String termo) {
        return perguntas.stream().filter(p -> p.getTitulo().toLowerCase().contains(termo.toLowerCase())).toList();
    }

    @Override
    public void remove(Pergunta p) {
        perguntas.remove(p);
    }
}
