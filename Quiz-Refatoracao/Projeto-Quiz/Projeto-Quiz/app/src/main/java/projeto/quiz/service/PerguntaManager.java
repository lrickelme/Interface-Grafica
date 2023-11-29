package projeto.quiz.service;

import java.util.List;
import java.util.Scanner;

import projeto.quiz.domain.Pergunta;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.repository.PerguntaRepository;

public class PerguntaManager {
    private final PerguntaRepository repository;

    public PerguntaManager(PerguntaRepository repository) {
        this.repository = repository;
    }

    public void adicionarPergunta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira o título da pergunta: ");
        String titulo = scanner.nextLine();

        System.out.println("Área do conhecimento da pergunta: ");
        String areaDoConhecimento = scanner.nextLine();

        Pergunta pergunta = new Pergunta(titulo, areaDoConhecimento);

        // Adiciona a pergunta ao repositório
        repository.add(pergunta);

        System.out.println("Pergunta adicionada com sucesso!");
    }

    public void listarPerguntas() {
        List<Pergunta> perguntas = repository.getAll();

        if (perguntas.isEmpty()) {
            System.out.println("Não há perguntas disponíveis.");
        } else {
            System.out.println("Lista de Perguntas:");
            for (int i = 0; i < perguntas.size(); i++) {
                Pergunta pergunta = perguntas.get(i);
                System.out.println(i + ". Título: " + pergunta.getTitulo());
                System.out.println("   Área do Conhecimento: " + pergunta.getAreaDoConhecimento());
                // Pode adicionar lógica para listar alternativas aqui, se necessário
            }
        }
    }

    public void removerPergunta() {
        listarPerguntas();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o número da pergunta que deseja remover: ");
        int escolha = scanner.nextInt();

        List<Pergunta> perguntas = repository.getAll();

        if (escolha >= 0 && escolha < perguntas.size()) {
            Pergunta perguntaRemovida = perguntas.get(escolha);
            repository.remove(perguntaRemovida);
            System.out.println("Pergunta removida com sucesso!");
        } else {
            System.out.println("Escolha inválida. A pergunta não foi removida.");
        }
    }

    public void editarPergunta() {
        List<Pergunta> perguntas = repository.getAll();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lista de Perguntas disponíveis para editar:");
        for (int i = 0; i < perguntas.size(); i++) {
            System.out.println(i + ". Título: " + perguntas.get(i).getTitulo());
        }

        System.out.println("Digite o número da pergunta que deseja editar: ");
        int escolha = scanner.nextInt();

        if (escolha >= 0 && escolha < perguntas.size()) {
            Pergunta perguntaEditada = perguntas.get(escolha);

            System.out.println("Digite o novo título (ou Enter para pular): ");
            String novoTitulo = scanner.nextLine().trim();
            if (!novoTitulo.isEmpty()) {
                perguntaEditada.setTitulo(novoTitulo);
            }

            System.out.println("Digite a nova área do conhecimento (ou Enter para pular): ");
            String novaAreaDoConhecimento = scanner.nextLine().trim();
            if (!novaAreaDoConhecimento.isEmpty()) {
                perguntaEditada.setAreaDoConhecimento(novaAreaDoConhecimento);
            }

            // Lógica para editar as alternativas pode ser adicionada aqui

            repository.update(perguntaEditada);
            System.out.println("Pergunta editada com sucesso!");
        } else {
            System.out.println("Escolha inválida. A pergunta não foi editada.");
        }
    }
}
