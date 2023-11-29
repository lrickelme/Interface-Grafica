package projeto.quiz.repository;

import java.util.ArrayList;
import java.util.List;

import projeto.quiz.domain.Pergunta;

public class InMemoryArmazenamento implements Armazenamento{
    protected List<Pergunta> perguntas = new ArrayList<>();

    @Override
    public void add(Pergunta p) {
        perguntas.add(p);
    }

    @Override
    public List<Pergunta> getPerguntas() {
        return perguntas;
    }
}
