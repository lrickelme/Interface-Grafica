package projeto.quiz.menu;

import java.util.Scanner;

import projeto.quiz.menu.Placar;
import projeto.quiz.repository.Armazenamento;
import projeto.quiz.gerenciador.GerenciadorPergunta;
import projeto.quiz.exception.ListaVaziaException;

public class Menu {

    public static void main(String[] args) throws InterruptedException {
        Scanner name = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String nomeJogador = name.nextLine();

        GerenciadorPergunta gerenciador = new GerenciadorPergunta(nomeJogador, Armazenamento.getPerguntas());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("---------------- MENU ----------------");
            System.out.println();
            System.out.println("1- JOGAR");
            System.out.println("2- ADICIONAR PERGUNTAS");
            System.out.println("3- REMOVER PERGUNTAS");
            System.out.println("4- EDITAR PERGUNTAS");
            System.out.println("5- LISTAR PERGUNTAS");
            System.out.println("6- SAIR");
            System.out.println("7- PLACAR");
            System.out.println();

            // Use um try-catch para capturar entradas não numéricas
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                if (escolha < 1 || escolha > 7) {
                    System.out.println();
                    System.out.println("Opção inválida. Tente novamente.");
                    continue; // Volte para o início do loop
                }

                switch (escolha) {
                    case 1:
                        System.out.println();
                        System.out.println("Seja bem-vindo " + nomeJogador + ", você iniciará o jogo agora.");
                        System.out.println();
                        gerenciador.jogar();
                        break;
                    case 2:
                        gerenciador.criarPergunta();
                        break;
                    case 3:
                        try {
                            gerenciador.apagarPergunta();
                        } catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            gerenciador.editarPergunta();
                        } catch (ListaVaziaException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            gerenciador.listarPerguntas();
                        } catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println();
                        System.out.println("Encerrando o programa.");
                        System.out.println();
                        System.exit(0);
                        break;
                    case 7:
                        Placar.exibirHistorico(nomeJogador, gerenciador.getHistoricoPontuacoes());
                        break;
                
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println();
                System.out.println("Opção inválida. Tente novamente.");
                scanner.nextLine(); // Limpar o buffer de entrada
            }
        }
    }
}