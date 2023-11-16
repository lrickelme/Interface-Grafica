package projeto.quiz.menu;

import java.util.List;

public class Placar {

    public static void exibirHistorico(String nomeJogador, List<Integer> historicoPontuacoes) {
        System.out.println();
        System.out.println("Histórico de Desempenho para " + nomeJogador + ":");
        
        if (historicoPontuacoes.isEmpty()) {
            System.out.println();
            System.out.println("Você ainda não jogou nenhuma vez.");
        } else {
            for (int i = 0; i < historicoPontuacoes.size(); i++) {
                int pontuacaoQuiz = i == 0 ? historicoPontuacoes.get(i) : historicoPontuacoes.get(i) - historicoPontuacoes.get(i - 1);
                System.out.println("Quiz " + (i + 1) + ": " + pontuacaoQuiz + " ponto(s)");
            }
        }
    }   
    
    public static void adicionarPontuacao(List<Integer> historicoPontuacoes, int placar) {
        historicoPontuacoes.add(placar);
    }

    public static void exibirPontuacaoFinal(String nomeJogador, int placar) {
        System.out.println("Placar final para " + nomeJogador + ": " + placar + " ponto(s)");
    }
}
