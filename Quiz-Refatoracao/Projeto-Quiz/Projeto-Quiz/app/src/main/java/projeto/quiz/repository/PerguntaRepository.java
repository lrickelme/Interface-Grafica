package projeto.quiz.repository;

import projeto.quiz.domain.Pergunta;
import java.util.List;

public class PerguntaRepository {
    private DataService dataService;
    private static PerguntaRepository instance;
    private List<Pergunta> perguntas; 

    private PerguntaRepository(DataService dataService) {
        this.dataService = dataService;
        this.perguntas = dataService.getAll();  
    }

    public static PerguntaRepository getInstance() {
        if (instance == null) {
            instance = new PerguntaRepository(new InMemoryDataService());
        }

        return instance;
    }

    public void setRepository(DataService dataService) {
        this.dataService = dataService;
        this.perguntas = dataService.getAll();  
    }

    public List<Pergunta> getAll() {
        return perguntas;
    }

    public void update(Pergunta p) {
        dataService.update(p);
    }

    public List<Pergunta> search(String termo) {
        return dataService.search(termo);
    }

    public void remove(Pergunta p) {
        dataService.remove(p);
    }

    public void add(Pergunta pergunta) {
        perguntas.add(pergunta);
        dataService.add(pergunta);  // Adicionando a pergunta também no DataService
    }
}
