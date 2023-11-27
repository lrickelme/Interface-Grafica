package projeto.quiz.service;

import java.util.List;

import projeto.quiz.Refatorado.Pergunta;
import projeto.quiz.Repository.PerguntaRepository;

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
}
