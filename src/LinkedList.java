/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
class Node {
    String comando;
    Node proximo;
    int numeroLinha;

    public Node(String comando, int numeroLinha) {
        this.comando = comando;
        this.proximo = null;
        this.numeroLinha = numeroLinha;
    }
}

public class LinkedList{
    private Node head;
    private boolean isModified;
    private int proximaLinha;
    private Node current;

    public LinkedList() {
        this.head = null;
        this.isModified = false;
        this.proximaLinha = 0;
    }

    // Adiciona uma nova linha no final da lista
    public void addLine(String line) {
        int linha = Integer.parseInt(line.substring(0, 2));
        Node newNode = new Node(line, linha);
        if (head == null) {
            head = newNode;
        } else {
            Node atual = head;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = newNode;
        }
        proximaLinha += linha;
        isModified = true;
    }

    // Adiciona uma linha em uma posição específica
    public void addLineAt(String line, int lineNumber) {
        // Verifica se o número da linha é válido
        if (lineNumber <= 0) {
            System.out.println("Número de linha inválido!");
            return;
        }

        Node newNode = new Node(line, lineNumber);

        // Caso especial: inserção no início
        if (head == null || lineNumber < head.numeroLinha) {
            newNode.proximo = head;
            head = newNode;
            isModified = true;
            return;
        }

        // Procura a posição correta para inserir
        Node atual = head;
        Node anterior = null;

        while (atual != null && atual.numeroLinha < lineNumber) {
            anterior = atual;
            atual = atual.proximo;
        }

        // Verifica se já existe uma linha com esse número
        if (atual != null && atual.numeroLinha == lineNumber) {
            System.out.println("Já existe uma linha com o número " + lineNumber);
            return;
        }

        // Insere o novo nó na posição correta
        newNode.proximo = atual;
        anterior.proximo = newNode;
        isModified = true;
    }

    // Exibe o conteúdo da lista com números de linha
    public void retornaLista() {
        Node current = head;

        if (head == null) {
            System.out.println("Nenhum arquivo foi carregado!");
            return;
        }

        while (current != null) {
            System.out.printf("%s%n", current.comando);
            current = current.proximo;
        }
    }

    // Reorganiza os números das linhas para manter múltiplos de 10
    public void reorganizarLinhas() {
        if (head == null) return;

        int novoNumero = 10;
        Node atual = head;

        while (atual != null) {
            atual.numeroLinha = novoNumero;
            novoNumero += 10;
            atual = atual.proximo;
        }

        proximaLinha = novoNumero;
        isModified = true;
    }

    public boolean isModified() {
        return isModified;
    }

    public void clearModification() {
        isModified = false;
    }

    public void resetaIterator() {
        current = head; // Reinicia para o início da lista
    }

    // Metodo para obter o próximo comando
    public String getNextCommand() {
        if (current == null)
        {
            return null; // Retorna null se não houver mais itens
        }

        String comando = current.comando; // Obtém o comando atual
        current = current.proximo; // Avança para o próximo nó
        return comando; // Retorna o comando
    }
}
