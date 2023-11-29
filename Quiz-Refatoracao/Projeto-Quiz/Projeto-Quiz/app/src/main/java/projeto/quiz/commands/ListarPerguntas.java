package projeto.quiz.commands;

import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.commands.Commands;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.domain.Pergunta;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.service.PerguntaService;

import java.util.List;

public class ListarPerguntas implements Commands {

    @Override
    public void execute() {
        try {
            listarPerguntasSemRespostas();
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarPerguntasSemRespostas() throws ListaVaziaException {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
        List<Pergunta> perguntas = perguntaService.getPerguntas();

        if (perguntas.isEmpty()) {
            throw new ListaVaziaException("Não há perguntas para listar sem respostas.");
        }

        System.out.println("Lista de Perguntas (sem respostas):");

        for (Pergunta pergunta : perguntas) {
            System.out.println("Título: " + pergunta.getTitulo());
            System.out.println("Área do Conhecimento: " + pergunta.getAreaDoConhecimento());
            System.out.println("Alternativas:");

            for (Alternativa alternativa : pergunta.getAlternativas()) {
                System.out.println("Opção: " + alternativa.getOpcao());
                System.out.println("Afirmativa: " + alternativa.getAfirmativa());
                // Não mostrar se é a opção correta
            }

            System.out.println();
        }
    }
}
