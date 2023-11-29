package projeto.quiz.commands;


import projeto.quiz.commands.Commands;
import projeto.quiz.domain.Pergunta;
import projeto.quiz.service.PerguntaService;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.Refatorado.Exception.ListaVaziaException;

import java.util.List;
import java.util.Scanner;

public class RemoverPergunta implements Commands {

    @Override
    public void execute() {
        try {
            removerPergunta();
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removerPergunta() throws ListaVaziaException {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
        List<Pergunta> perguntas = perguntaService.getPerguntas();

        if (perguntas.isEmpty()) {
            throw new ListaVaziaException("Não há perguntas para apagar.");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println(); // ajudar formatação
        System.out.println("Perguntas disponíveis para apagar:");
        System.out.println(); // ajudar formatação

        // Listar os títulos das perguntas disponíveis
        for (int i = 0; i < perguntas.size(); i++) {
            System.out.println(i + ". Título: " + perguntas.get(i).getTitulo());
        }

        System.out.println(); // ajudar formatação
        System.out.println("Digite o número da pergunta que deseja apagar: ");
        int escolha = scanner.nextInt();

        // Verificar se a escolha do usuário está dentro dos limites
        if (escolha >= 0 && escolha < perguntas.size()) {
            Pergunta perguntaRemover = perguntas.get(escolha);

            // Remover pergunta usando o serviço
            perguntaService.remover(perguntaRemover);

            System.out.println();
            System.out.println("Pergunta apagada com sucesso.");
            scanner.nextLine(); // Consumir a nova linha pendente
        } else {
            System.out.println();
            System.out.println("Escolha inválida. A pergunta não foi apagada.");
            scanner.nextLine(); // Consumir a nova linha pendente
        }
    }
}
