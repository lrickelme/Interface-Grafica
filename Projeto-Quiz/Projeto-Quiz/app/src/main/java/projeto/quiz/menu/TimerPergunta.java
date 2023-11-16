package projeto.quiz.menu;

import java.util.Timer;
import java.util.TimerTask;

public class TimerPergunta {
    private Timer timer;
    private boolean tempoEsgotado;

    public void iniciarTimer(int segundos, Runnable onTempoEsgotado) {
        tempoEsgotado = false;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tempoEsgotado = true;
                onTempoEsgotado.run();
                cancelarTimer();
            }
        }, segundos * 1000);
    }

    public boolean isTempoEsgotado() {
        return tempoEsgotado;
    }

    public void cancelarTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }
}
