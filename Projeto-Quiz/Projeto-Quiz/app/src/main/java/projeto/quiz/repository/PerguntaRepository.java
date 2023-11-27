package projeto.quiz.Repository;

import projeto.quiz.Refatorado.Pergunta;
import java.util.List;

public class PerguntaRepository {
    private DataService dataService;
    private static PerguntaRepository instance;

    private PerguntaRepository(DataService dataService) {
        this.dataService = dataService;
    }

    public static PerguntaRepository getInstance() {
        if (instance == null) {
            instance = new PerguntaRepository(new InMemoryDataService());
        }

        return instance;
    }

    public void setRepository(DataService dataService) {
        this.dataService = dataService;
    }

    public List<Pergunta> getAll() {
        return dataService.getAll();
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
}

