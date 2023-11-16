package projeto.quiz.moldes;

import java.util.List;
import java.util.ArrayList;

public class Pergunta {
    public String titulo;
    public String areaDoConhecimento;
    private List<Alternativa> alternativas; // lista para armazenar as alternativas

    public Pergunta(String titulo, String areaDoConhecimento) {
        this.titulo = titulo;
        this.areaDoConhecimento = areaDoConhecimento;
        this.alternativas = new ArrayList<>(); // inicia a lista
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAreaDoConhecimento() {
        return areaDoConhecimento;
    }

    public void setAreaDoConhecimento(String areaDoConhecimento) {
        this.areaDoConhecimento = areaDoConhecimento;
    }

    // metodo para adicionar alternativas
    public void adicionarAlternativa(Alternativa alternativa) {
        alternativas.add(alternativa);
    }

    public boolean verificarResposta(String respostaUsuario) {
        for (Alternativa alternativa : alternativas) {
            if (alternativa.getOpcao().equalsIgnoreCase(respostaUsuario) && alternativa.isOpcaoCorreta()) {
                return true;
            }
        }
        return false;
    }

    public String obterRespostaCorreta() {
        for (Alternativa alternativa : alternativas) {
            if (alternativa.isOpcaoCorreta()) {
                return alternativa.getOpcao() + ". " + alternativa.getAfirmativa();
            }
        }
        return "Resposta não encontrada";
    }

}