package projeto.quiz.gerenciador;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import projeto.quiz.moldes.Pergunta;
import projeto.quiz.exception.ListaVaziaException;
import projeto.quiz.menu.TimerPergunta;
import projeto.quiz.moldes.Alternativa;
import projeto.quiz.moldes.Aleatoriedade;
import projeto.quiz.menu.Placar;

public class GerenciadorPergunta {

    private String nomeJogador;
    private int placar;
    private TimerPergunta timerPergunta;
    private List<Integer> historicoPontuacoes = new ArrayList<>();
    private List<Pergunta> perguntas;

    public GerenciadorPergunta(String nomeJogador, List<Pergunta> perguntas) {
        this.nomeJogador = nomeJogador;
        this.placar = 0;
        this.timerPergunta = new TimerPergunta();
        this.perguntas = perguntas;
    }
 
    // Método iniciar o jogo
    public void jogar(){
        Scanner li = new Scanner(System.in);
        
        try {
            // Jogue 5 vezes
            for (int i = 0; i < 5; i++) {
                Pergunta pergunta = Aleatoriedade.selecionarPerguntaAleatoria(perguntas);
    
                // Exibe a pergunta
                System.out.println("Pergunta " + (i + 1) + ": " + pergunta.getTitulo());
                System.out.println("Área do Conhecimento: " + pergunta.getAreaDoConhecimento());
                System.out.println("Timer iniciado, você tem 60 segundos para responder!");
                System.out.println();
    
                // Exibe as alternativas
                List<Alternativa> alternativas = pergunta.getAlternativas();
                for (Alternativa alternativa : alternativas) {
                    System.out.println(alternativa.getOpcao() + ". " + alternativa.getAfirmativa());
                }

                // Inicia o timer para a pergunta atual
                timerPergunta.iniciarTimer(60, () -> {
                    System.out.println("Tempo esgotado! Pressione Enter para continuar ");
                });
    
                // Solicita a resposta do usuário
                System.out.print("Escolha a opção correta: ");
                String respostaUsuario = li.nextLine();
    
                // Cancela o timer
                timerPergunta.cancelarTimer();

                // Verifica se a resposta está correta ou se o tempo esgotou
                if (timerPergunta.isTempoEsgotado()) {
                }else if (pergunta.verificarResposta(respostaUsuario)) {
                    System.out.println("Resposta correta!");
                    System.out.println();
                    placar ++;
                } else {
                    System.out.println("Resposta incorreta. A resposta correta é: " + pergunta.obterRespostaCorreta());
                    System.out.println();
                }

                // Aguarda 1.5 segundos entre as perguntas
                Thread.sleep(1500);
            }

            historicoPontuacoes.add(placar);

            Placar.exibirPontuacaoFinal(nomeJogador, placar);

        } catch (InterruptedException | ListaVaziaException e) {
            System.out.println("Não há perguntas disponíveis. Adicione perguntas antes de jogar.");
        }
    }    

    public void exibirHistorico() {
        Placar.exibirHistorico(nomeJogador, historicoPontuacoes);
    }

    public List<Integer> getHistoricoPontuacoes() {
        return historicoPontuacoes;
    }

    // Criar pergunta (Create)
    public void criarPergunta() {

        System.out.println(); // ajudar formatação
        System.out.println("Insira o título da pergunta: ");
        String titulo = System.console().readLine();
        System.out.println(); // ajudar formatação
        System.out.println("Área do conhecimento da pergunta: ");
        String areaDoConhecimento = System.console().readLine();

        // pergunta criada
        Pergunta pergunta = new Pergunta(titulo, areaDoConhecimento);

        // criação de alternativas para pergunta
        String opcao, afirmativa;
        boolean opcaoCorreta;

        for (int i = 1; i <= 4; i++) {
            System.out.println(); // ajudar formatação
            System.out.println("Insira a opção da pergunta " + i + ": ");
            opcao = System.console().readLine();
            System.out.println("Insira a afirmativa para a opção " + i + ": ");
            afirmativa = System.console().readLine();
            System.out.print("Essa é a opção correta? (true/false): "); // Correção na mensagem

            opcaoCorreta = Boolean.parseBoolean(System.console().readLine());

            // criando instância de alternativa
            Alternativa alternativa = new Alternativa(opcao, afirmativa, opcaoCorreta);

            // adicionando a alternativa a pergunta
            pergunta.adicionarAlternativa(alternativa);
        }

        // adicionando a pergunta à lista de perguntas
        perguntas.add(pergunta);
    }

    // Metodo Read do CRUD

    public void listarPerguntas() throws ListaVaziaException{
        if (perguntas.isEmpty()) {
            throw new ListaVaziaException("Não há perguntas para listar.");
        }
    
        Scanner sc = new Scanner(System.in);
    
        System.out.println();
        System.out.println("Escolha uma opção de listagem:");
        System.out.println("1. Listar com respostas");
        System.out.println("2. Listar sem respostas");
        System.out.println();
        System.out.print("Digite o número da opção desejada: ");
        System.out.println();
        
        int escolha = sc.nextInt();
    
        if (escolha == 1) {
            listarPerguntasComRespostas();
        } else if (escolha == 2) {
            listarPerguntasSemRespostas();
        } else {
            System.out.println("Opção inválida.");
        }
    }
    
    public void listarPerguntasComRespostas() {
        System.out.println("Lista de Perguntas (com respostas):");
        for (Pergunta pergunta : perguntas) {
            System.out.println("Título: " + pergunta.getTitulo());
            System.out.println("Área do Conhecimento: " + pergunta.getAreaDoConhecimento());
            System.out.println("Alternativas:");
            for (Alternativa alternativa : pergunta.getAlternativas()) {
                System.out.println("Opção: " + alternativa.getOpcao());
                System.out.println("Afirmativa: " + alternativa.getAfirmativa());
                System.out.println("Opção Correta: " + alternativa.isOpcaoCorreta());
            }
            System.out.println();
        }
    }
    
    public void listarPerguntasSemRespostas() {
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
    

    // Metodo Delete do CRUD

    public void apagarPergunta() throws ListaVaziaException{
        
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
            perguntas.remove(escolha);
            System.out.println();
            System.out.println("Pergunta apagada com sucesso.");
            scanner.nextLine(); // Consumir a nova linha pendente
        } else {
            System.out.println();
            System.out.println("Escolha inválida. A pergunta não foi apagada.");
            scanner.nextLine(); // Consumir a nova linha pendente
        }
    }
    
   // Método Edit do CRUD

    public void editarPergunta() throws ListaVaziaException {
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

