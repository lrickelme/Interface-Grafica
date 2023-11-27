package projeto.quiz.Refatorado.Exception;

public class RespostaNaoEncontradaException extends Exception{
    public RespostaNaoEncontradaException(String NaoEncontrada){
        super(NaoEncontrada);
    }
}
