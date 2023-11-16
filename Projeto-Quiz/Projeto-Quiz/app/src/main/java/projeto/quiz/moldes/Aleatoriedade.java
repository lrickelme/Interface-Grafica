package projeto.quiz.moldes;
import projeto.quiz.exception.ListaVaziaException;

import java.util.List;

public class Aleatoriedade {
    public static int gerarIndiceAleatorio(int tamanhoLista) {
        return (int) (Math.random() * tamanhoLista);
    }

    public static Pergunta selecionarPerguntaAleatoria(List<Pergunta> perguntas) throws ListaVaziaException {
        if (perguntas.isEmpty()) {
            throw new ListaVaziaException("Não há perguntas disponíveis.");
        }

        int indiceAleatorio = gerarIndiceAleatorio(perguntas.size());
        Pergunta perguntaSelecionada = perguntas.remove(indiceAleatorio);
        return perguntaSelecionada;
    }
}
