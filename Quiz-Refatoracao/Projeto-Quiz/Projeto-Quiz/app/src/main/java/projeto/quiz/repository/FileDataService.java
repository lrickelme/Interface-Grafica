package projeto.quiz.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import projeto.quiz.domain.Pergunta;

public class FileDataService extends InMemoryDataService {
    public FileDataService() {
        File data = new File("data.bin");
        if (data.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.bin"))) {
                perguntas = (List<Pergunta>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Pergunta p) {
        super.add(p);
        write();
    }

    @Override
    public void update(Pergunta p) {
        super.update(p);
        write();
    }

    @Override
    public void remove(Pergunta p) {
        super.remove(p);
        write();
    }

    private void write() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.bin"))) {
            oos.writeObject(getAll());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}