package projeto.quiz.Refatorado;

import java.util.List;

import projeto.quiz.Refatorado.Exception.ListaVaziaException;

public class RandomNum {
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
