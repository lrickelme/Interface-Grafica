package projeto.quiz.Refatorado;

public class Alternativa {
    private String opcao;
    private String afirmativa;
    private boolean opcaoCorreta;

    public Alternativa(String opcao, String afirmativa, boolean opcaoCorreta) {
        this.opcao = opcao;
        this.afirmativa = afirmativa;
        this.opcaoCorreta = opcaoCorreta;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getAfirmativa() {
        return afirmativa;
    }

    public void setAfirmativa(String afirmativa) {
        this.afirmativa = afirmativa;
    }

    public boolean isOpcaoCorreta() {
        return opcaoCorreta;
    }
    
    public void setOpcaoCorreta(boolean opcaoCorreta) {
        this.opcaoCorreta = opcaoCorreta;
    }

}