package projeto.quiz.service;

import java.util.List;

import projeto.quiz.domain.Pergunta;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.repository.PerguntaRepository;

public class PerguntaService {
    private final PerguntaRepository repository;

    public PerguntaService(PerguntaRepository repository) {
        this.repository = repository;
    }

    public Pergunta get(int index) {
        return repository.getAll().get(index);
    }

    public List<Pergunta> buscar(String termo) {
        return repository.search(termo);
    }

    public List<Pergunta> getPerguntas() {
        return repository.getAll();
    }

    public void editar(String titulo, String areaDoConhecimento) {
        repository.update(new Pergunta(titulo, areaDoConhecimento));
    }

    public void remover(Pergunta p) {
        repository.remove(p);
    }

    public void adicionarAlternativa(Pergunta pergunta, Alternativa alternativa) {
        pergunta.adicionarAlternativa(alternativa);
        repository.update(pergunta); 
    }

    public void adicionarPergunta(Pergunta pergunta) {
        repository.add(pergunta);
    }
}
