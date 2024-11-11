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
    private Node current;
    private int linhaAnterior;

    public LinkedList() {
        this.linhaAnterior = -1;
        this.head = null;
        this.isModified = false;
    }

    // Adiciona uma nova linha no final da lista
    public void addLine(String line) throws Exception
    {
        String[] partesLinha = line.split(" ");
        int linha = Integer.parseInt(partesLinha[0]);
        if(linha > 0 && linha > linhaAnterior)
        {
           linhaAnterior = linha;

            Node newNode = new Node(line, linha);
            if (head == null)
            {
                head = newNode;
            } else
            {
                Node atual = head;
                while (atual.proximo != null)
                {
                    atual = atual.proximo;
                }
                atual.proximo = newNode;
            }
        }else
        {
            throw new Exception("Impossível gravar arquivo com essa sequência de linhas!");
        }
    }

    // Adiciona ou sobrescreve uma linha em uma posição específica
    public void addLineAt(String line, int lineNumber)
    {
        // Verifica se o número da linha é válido
        if (lineNumber <= 0)
        {
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

        // Procura a posição correta para inserir ou atualizar a linha
        Node atual = head;
        Node anterior = null;

        while (atual != null && atual.numeroLinha < lineNumber) {
            anterior = atual;
            atual = atual.proximo;
        }

        // Se já existe uma linha com o número, substitui o conteúdo da linha
        if (atual != null && atual.numeroLinha == lineNumber)
        {
            atual.comando = line;  // Sobrescreve o comando da linha existente
            System.out.println("Linha " + lineNumber + " já existe. Conteúdo atualizado.");
            isModified = true;
        } else
        {
            // Insere o novo nó na posição correta
            newNode.proximo = atual;
            anterior.proximo = newNode;
            isModified = true;
            System.out.println("Linha " + lineNumber + " inserida.");
        }
    }

    // Deleta uma linha em uma posição específica
    public void delLine(int lineNumber) {
        if (head == null)
        {
            System.out.println("A lista está vazia!");
            return;
        }

        // Caso especial: remoção da primeira linha
        if (head.numeroLinha == lineNumber)
        {
            head = head.proximo;
            System.out.println("Linha " + lineNumber + " removida.");
            isModified = true;
            return;
        }

        Node atual = head;
        Node anterior = null;

        // Procura pela linha na lista
        while (atual != null && atual.numeroLinha != lineNumber)
        {
            anterior = atual;
            atual = atual.proximo;
        }

        // Se a linha não for encontrada
        if (atual == null)
        {
            System.out.println("Linha " + lineNumber + " não existe.");
        } else
        {
            // Remove a linha
            anterior.proximo = atual.proximo;
            isModified = true;
            System.out.println("Linha " + lineNumber + " removida.");
        }
    }

    // Deleta todas as linhas dentro do intervalo
    public void delLinesInRange(int linhaInicial, int linhaFinal)
    {
        // Verifica se o intervalo é válido
        if (linhaInicial <= 0 || linhaFinal <= 0 || linhaInicial > linhaFinal)
        {
            System.out.println("Intervalo inválido. Certifique-se de que o número da linha inicial seja menor ou igual à linha final.");
            return;
        }

        // Verifica se a lista está vazia
        if (head == null)
        {
            System.out.println("A lista está vazia!");
            return;
        }

        Node atual = head;
        Node anterior = null;
        boolean linhasRemovidas = false;
        StringBuilder resultado = new StringBuilder("Linhas removidas:\n");

        // Percorre a lista para remover as linhas dentro do intervalo
        while (atual != null)
        {
            // Verifica se a linha está dentro do intervalo de exclusão
            if (atual.numeroLinha >= linhaInicial && atual.numeroLinha <= linhaFinal)
            {
                // Caso especial: remoção da linha no início da lista
                if (anterior == null)
                {
                    head = atual.proximo; // Remove o primeiro nó
                } else
                {
                    anterior.proximo = atual.proximo; // Remove a linha do meio ou final
                }

                resultado.append(atual.numeroLinha).append(" ").append(atual.comando).append("\n");
                linhasRemovidas = true;
            } else
            {
                anterior = atual; // Avança o ponteiro anterior
            }

            atual = atual.proximo; // Avança para o próximo nó
        }

        // Verifica se alguma linha foi removida
        if (linhasRemovidas)
        {
            System.out.print(resultado);
            isModified = true;
        } else
        {
            System.out.println("Nenhuma linha encontrada no intervalo especificado.");
        }
    }



    // Exibe o conteúdo da lista com números de linha
    public void retornaLista() {
        Node current = head;

        if (head == null)
        {
            System.out.println("Nenhum arquivo foi carregado!");
            return;
        }

        while (current != null)
        {
            System.out.printf("%s%n", current.comando);
            current = current.proximo;
        }
    }

    public boolean isModified() {
        return isModified;
    }

    public void clearModification() {
        isModified = false;
    }

    // Metodo para apagar (limpar) a lista
    public void clearList() {
        head = null;
        isModified = false;
        linhaAnterior = -1;
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
