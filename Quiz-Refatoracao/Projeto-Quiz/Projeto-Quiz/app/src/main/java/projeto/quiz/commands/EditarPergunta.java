package projeto.quiz.commands;


import projeto.quiz.commands.Commands;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.domain.Pergunta;
import projeto.quiz.service.PerguntaService;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.Refatorado.Exception.ListaVaziaException;


import java.util.List;
import java.util.Scanner;

public class EditarPergunta implements Commands {

    @Override
    public void execute() {
        try {
            editarPergunta();
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editarPergunta() throws ListaVaziaException {
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
        List<Pergunta> perguntas = perguntaService.getPerguntas();

        if (perguntas.isEmpty()) {
            throw new ListaVaziaException("Não há perguntas para editar.");
        }

        Scanner sc = new Scanner(System.in);

        System.out.println(); // ajudar formatação

        // Listar os títulos das perguntas disponíveis
        for (int i = 0; i < perguntas.size(); i++) {
            System.out.println(i + ". Título: " + perguntas.get(i).getTitulo());
        }

        System.out.println(); // ajudar formatação
        System.out.print("Digite o número da pergunta que deseja editar: ");
        int escolhaEdit = sc.nextInt();

        // Verificar se a escolha do usuário está dentro dos limites
        if (escolhaEdit >= 0 && escolhaEdit < perguntas.size()) {
            sc.nextLine(); // Consumir a nova linha pendente
            System.out.println(); // ajudar formatação
            System.out.print("Digite o novo título (ou Enter para pular): ");
            String novoTitulo = sc.nextLine();

            if (!novoTitulo.trim().isEmpty()) {
                perguntas.get(escolhaEdit).setTitulo(novoTitulo);
            }

            System.out.println(); // ajudar formatação
            System.out.print("Digite a nova área do conhecimento (ou Enter para pular): ");
            String novaAreaDoConhecimento = sc.nextLine();

            if (!novaAreaDoConhecimento.trim().isEmpty()) {
                perguntas.get(escolhaEdit).setAreaDoConhecimento(novaAreaDoConhecimento);
            }

            // Editar as alternativas
            List<Alternativa> alternativas = perguntas.get(escolhaEdit).getAlternativas();

            for (int i = 0; i < alternativas.size(); i++) {
                System.out.println(); // ajudar formatação
                Alternativa alternativa = alternativas.get(i);
                System.out.println("Editar Alternativa " + (i + 1));

                // Editar a opção
                System.out.print("Nova opção (ou Enter para pular): ");
                String novaOpcao = sc.nextLine();
                if (!novaOpcao.trim().isEmpty()) {
                    alternativa.setOpcao(novaOpcao);
                }

                // Editar a afirmativa
                System.out.print("Nova afirmativa (ou Enter para pular): ");
                String novaAfirmativa = sc.nextLine();
                if (!novaAfirmativa.trim().isEmpty()) {
                    alternativa.setAfirmativa(novaAfirmativa);
                }

                // Editar se é a opção correta
                System.out.print("É a opção correta? (true/false) (ou Enter para pular): ");
                String respostaCorreta = sc.nextLine();
                if (!respostaCorreta.trim().isEmpty()) {
                    boolean novaOpcaoCorreta = Boolean.parseBoolean(respostaCorreta);
                    alternativa.setOpcaoCorreta(novaOpcaoCorreta);
                }
            }

            System.out.println("Pergunta editada com sucesso.");
        } else {
            System.out.println("Escolha inválida. A pergunta não foi editada.");
        }
    }
}
