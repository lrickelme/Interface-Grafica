package projeto.quiz.commands;

import projeto.quiz.domain.Alternativa;
import projeto.quiz.domain.Pergunta;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.service.PerguntaService;

public class AdicionarPergunta implements Commands {

    @Override
    public void execute() {
        System.out.println(); // ajudar formatação
        System.out.println("Insira o título da pergunta: ");
        String titulo = System.console().readLine();
        System.out.println(); // ajudar formatação
        System.out.println("Área do conhecimento da pergunta: ");
        String areaDoConhecimento = System.console().readLine();

        // pergunta criada
        PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());

        // criação de alternativas para pergunta
        Pergunta pergunta = new Pergunta(titulo, areaDoConhecimento);

        String opcao, afirmativa;
        boolean opcaoCorreta;

        for (int i = 1; i <= 4; i++) {
            System.out.println(); // ajudar formatação
            System.out.println("Insira a opção da pergunta " + i + ": ");
            opcao = System.console().readLine();
            System.out.println("Insira a afirmativa para a opção " + i + ": ");
            afirmativa = System.console().readLine();
            System.out.print("Essa é a opção correta? (true/false): "); 

            opcaoCorreta = Boolean.parseBoolean(System.console().readLine());

            // criando instância de alternativa
            Alternativa alternativa = new Alternativa(opcao, afirmativa, opcaoCorreta);

            // adicionando a alternativa à pergunta
            pergunta.adicionarAlternativa(alternativa);
        }

        // adicionando a pergunta à lista de perguntas
        perguntaService.adicionarPergunta(pergunta); 
    }
}
