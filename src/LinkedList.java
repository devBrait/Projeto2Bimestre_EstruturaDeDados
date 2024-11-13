/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
public class LinkedList{

    private Node head;
    private boolean isModified;
    private Node current;
    private int linhaAnterior;
    private int indiceInicial = 0;

    public LinkedList()
    {
        this.linhaAnterior = -1;
        this.head = null;
        this.isModified = false;
    }

    // Adiciona uma nova linha no final da lista
    public void insert(String line) throws Exception
    {
        String[] partesLinha = line.split(" ");
        int linha = Integer.parseInt(partesLinha[0]);
        if(linha >= 0 && linha > linhaAnterior)
        {
           linhaAnterior = linha;

            Node newNode = new Node(line, linha);
            if (head == null)
            {
                head = newNode;
            } else
            {
                Node atual = head;
                while (atual.getProximo() != null)
                {
                    atual = atual.getProximo();
                }
                atual.setProximo(newNode);
            }
        }else
        {
            throw new Exception("Impossível gravar arquivo com essa sequência de linhas!");
        }
    }

    // Adiciona ou sobrescreve uma linha em uma posição específica
    public void insertAt(String line, int lineNumber)
    {
        // Verifica se o número da linha é válido
        if (lineNumber < 0)
        {
            System.out.println("Número de linha inválido!");
            return;
        }

        Node newNode = new Node(line, lineNumber);

        // Caso especial: inserção no início
        if (head == null || lineNumber < head.getNumeroLinha())
        {
            newNode.setProximo(head);
            head = newNode;
            isModified = true;
            System.out.println("Linha " + lineNumber + " inserida:");
            System.out.println(line);
            return;
        }

        // Procura a posição correta para inserir ou atualizar a linha
        Node atual = head;
        Node anterior = null;

        while (atual != null && atual.getNumeroLinha() < lineNumber)
        {
            anterior = atual;
            atual = atual.getProximo();
        }

        // Se já existe uma linha com o número, substitui o conteúdo da linha
        if (atual != null && atual.getNumeroLinha() == lineNumber)
        {
            System.out.println("Linha " + lineNumber + " já existe:");
            System.out.println(findLineByNumber(lineNumber));
            System.out.println("Atualizada para:");
            System.out.println(line);
            atual.setComando(line);  // Sobrescreve o comando da linha existente
            isModified = true;
        } else
        {
            // Insere o novo nó na posição correta
            newNode.setProximo(atual);
            anterior.setProximo(newNode);
            isModified = true;
            System.out.println("Linha " + lineNumber + " inserida:");
            System.out.println(line);
        }
    }

    // Deleta uma linha em uma posição específica
    public void delete(int lineNumber) {
        if (head == null)
        {
            System.out.println("A lista está vazia!");
            return;
        }

        // Caso especial: remoção da primeira linha
        if (head.getNumeroLinha() == lineNumber)
        {
            System.out.println("Linha " + lineNumber + " removida:");
            System.out.println(findLineByNumber(lineNumber));
            head = head.getProximo();
            isModified = true;
            return;
        }

        Node atual = head;
        Node anterior = null;

        // Procura pela linha na lista
        while (atual != null && atual.getNumeroLinha() != lineNumber)
        {
            anterior = atual;
            atual = atual.getProximo();
        }

        // Se a linha não for encontrada
        if (atual == null)
        {
            System.out.println("Linha " + lineNumber + " não existe.");
        } else
        {
            // Remove a linha
            System.out.println("Linha " + lineNumber + " removida:");
            System.out.println(findLineByNumber(lineNumber));
            anterior.setProximo(atual.getProximo());
            isModified = true;
        }
    }

    // Deleta todas as linhas dentro do intervalo
    public void deleteInRange(int linhaInicial, int linhaFinal)
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
            if (atual.getNumeroLinha() >= linhaInicial && atual.getNumeroLinha() <= linhaFinal)
            {
                // Caso especial: remoção da linha no início da lista
                if (anterior == null)
                {
                    head = atual.getProximo(); // Remove o primeiro nó
                } else
                {
                    anterior.setProximo(atual.getProximo()); // Remove a linha do meio ou final
                }

                resultado.append(atual.getComando()).append("\n");
                linhasRemovidas = true;
            } else
            {
                anterior = atual; // Avança o ponteiro anterior
            }

            atual = atual.getProximo(); // Avança para o próximo nó
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
    public void retornaLista() throws Exception
    {
        if (head == null)
        {
            throw new Exception("Nenhum arquivo foi carregado!");
        }

        Node current = head;
        int contador = 0;

        // Avança até o índice inicial
        for (int i = 0; i < indiceInicial && current != null; i++)
        {
            current = current.getProximo();
        }

        // Exibe 20 comandos ou até o final da lista
        while (contador < 20 && current != null)
        {
            System.out.printf("%s%n", current.getComando());
            current = current.getProximo();
            contador++;
        }

        // Atualiza o índice inicial para a próxima chamada
        indiceInicial += 20;

        // Se atingir o fim da lista, redefine para o início
        if (current == null)
        {
            indiceInicial = 0;
        }
    }

    public boolean isModified() {
        return isModified;
    }

    public void clearModification() {
        isModified = false;
    }

    // Metodo para apagar (limpar) a lista
    public void clearList()
    {
        head = null;
        isModified = false;
        linhaAnterior = -1;
    }

    public void resetaIterator() {
        current = head; // Reinicia para o início da lista
    }

    // Metodo para obter o próximo comando
    public String getNextCommand()
    {
        if (current == null)
        {
            return null; // Retorna null se não houver mais itens
        }

        String comando = current.getComando(); // Obtém o comando atual
        current = current.getProximo(); // Avança para o próximo nó
        return comando; // Retorna o comando
    }

    // Encontra e retorna o conteúdo da linha pelo número dela
    public String findLineByNumber(int lineNumber)
    {
        Node atual = head;

        // Percorre a lista de nós
        while (atual != null) {
            // Verifica se o número da linha atual corresponde ao `lineNumber` desejado
            if (atual.getNumeroLinha() == lineNumber) {
                return atual.getComando();
            }
            atual = atual.getProximo();
        }

        // Caso a linha não seja encontrada, exibe uma mensagem e retorna `null`
        return "Linha " + lineNumber + " não encontrada.";
    }

}
