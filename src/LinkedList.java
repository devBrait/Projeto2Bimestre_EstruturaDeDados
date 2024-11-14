/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
public class LinkedList{

    private Node head;
    private Node headErros;
    private boolean modificado;
    private Node atual;
    private int linhaAnterior;
    private int indiceInicial = 0;
    private boolean erroSequencia = false;
    private boolean erroSemLinha = false;

    public LinkedList()
    {
        this.linhaAnterior = -1;
        this.head = null;
        this.modificado = false;
    }

    // Adiciona uma nova linha no final da lista
    public void insere(String line)
    {
        String[] partesLinha = line.split(" ");
        int linha = -1;
        try
        {
            linha = Integer.parseInt(partesLinha[0]);
        }catch(NumberFormatException e)
        {
            erroSemLinha = true; // Define flag de comando sem linha
        }

        // Verifica a sequência
        if (linha >= 0 && linha > linhaAnterior)
        {
            linhaAnterior = linha;
        } else
        {
            erroSequencia = true; // Define flag de sequência incorreta

            // Node para armazenar todos os erros
            Node erros = new Node(line, linha);
            if (headErros == null)
            {
                headErros = erros;
            } else
            {
                Node atualErro = headErros;
                while (atualErro.getProximo() != null)
                {
                    atualErro = atualErro.getProximo();
                }
                atualErro.setProximo(erros);
            }
            // Atualiza linhaAnterior apenas se a linha for maior que o anterior
            if (linha > linhaAnterior)
            {
                linhaAnterior = linha;
            }
        }

        // Cria um novo nó e adiciona à lista
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
    }

    // Adiciona ou sobrescreve uma linha em uma posição específica
    public void insereEspecifica(String line, int lineNumber)
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
            modificado = true;
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
            System.out.println(encontraLinhaNumero(lineNumber));
            System.out.println("Atualizada para:");
            System.out.println(line);
            atual.setComando(line);  // Sobrescreve o comando da linha existente
            modificado = true;
        } else
        {
            // Insere o novo nó na posição correta
            newNode.setProximo(atual);
            anterior.setProximo(newNode);
            modificado = true;
            System.out.println("Linha " + lineNumber + " inserida:");
            System.out.println(line);
        }
    }

    // Deleta uma linha em uma posição específica
    public void deleta(int lineNumber)
    {
        if (head == null)
        {
            System.out.println("A lista está vazia!");
            return;
        }

        boolean achou = false;
        Node atual = head;
        Node anterior = null;

        // Itera pela lista para procurar e remover todas as ocorrências da linha
        while (atual != null)
        {
            if (atual.getNumeroLinha() == lineNumber)
            {
                achou = true;

                // Verifica se a linha removida está na lista de erros
                verificaErroRemovido(lineNumber);

                // Se a linha a ser removida for a primeira da lista
                if (anterior == null)
                {
                    System.out.println("Linha " + lineNumber + " removida:");
                    System.out.println(encontraLinhaNumero(lineNumber));
                    head = atual.getProximo();
                } else
                {
                    System.out.println("Linha " + lineNumber + " removida:");
                    System.out.println(encontraLinhaNumero(lineNumber));
                    anterior.setProximo(atual.getProximo());
                }
                modificado = true;
            } else
            {
                anterior = atual;
            }
            atual = atual.getProximo();
        }

        // Se nenhuma linha foi removida
        if (!achou)
        {
            System.out.println("Linha " + lineNumber + " não existe.");
        }

        // Verifica se a lista de erros está vazia
        verificaTodosErrosRemovidos();
    }

    // Verifica se a linha que foi removida está na lista de erros e a remove se necessário
    private void verificaErroRemovido(int lineNumber)
    {
        Node atualErro = headErros;
        Node anteriorErro = null;

        while (atualErro != null)
        {
            if (atualErro.getNumeroLinha() == lineNumber)
            {
                if (anteriorErro == null)
                {
                    headErros = atualErro.getProximo();
                } else
                {
                    anteriorErro.setProximo(atualErro.getProximo());
                }
                return;
            }
            anteriorErro = atualErro;
            atualErro = atualErro.getProximo();
        }
    }

    // Verifica se todas as linhas de erro foram removidas e exibe a mensagem correspondente
    private void verificaTodosErrosRemovidos()
    {
        Node atualErro = headErros;
        if (atualErro == null)
        {
            erroSequencia = false;
        } else
        {
            exibeErros();
        }
    }

    // Deleta todas as linhas dentro do intervalo
    public void deletaMul(int linhaInicial, int linhaFinal)
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
            // Verifica se a linha está dentro do intervalo
            if (atual.getNumeroLinha() >= linhaInicial && atual.getNumeroLinha() <= linhaFinal)
            {
                // Caso especial: remoção da linha no início da lista
                if (anterior == null)
                {
                    head = atual.getProximo();
                } else
                {
                    anterior.setProximo(atual.getProximo());
                }

                resultado.append(atual.getComando()).append("\n");
                linhasRemovidas = true;

                // Verifica e remove a linha da lista de erros
                verificaErroRemovido(atual.getNumeroLinha());
            } else
            {
                anterior = atual;
            }

            atual = atual.getProximo();
        }

        // Verifica se alguma linha foi removida
        if (linhasRemovidas)
        {
            System.out.print(resultado);
            modificado = true;
            verificaTodosErrosRemovidos();
        } else
        {
            System.out.println("Nenhuma linha encontrada no intervalo especificado.");
        }
    }

    // Exibe o conteúdo da lista com números de linha
    public void exibeLista() throws Exception
    {
        if (head == null)
        {
            throw new Exception("Nenhum arquivo foi carregado!");
        }

        Node current = head;
        int contaLinhas = 0;

        // Avança até o índice inicial
        for (int i = 0; i < indiceInicial && current != null; i++)
        {
            current = current.getProximo();
        }

        // Exibe 20 comandos ou até o final da lista
        while (contaLinhas < 20 && current != null)
        {
            System.out.printf("%s%n", current.getComando());
            current = current.getProximo();
            contaLinhas++;
        }

        // Atualiza o índice inicial para a próxima chamada
        indiceInicial += 20;

        // Se atingir o fim da lista, redefine para o início
        if (current == null)
        {
            indiceInicial = 0;
        }
    }

    // Metodo para obter o próximo comando
    public String proximoComando()
    {
        if (atual == null)
        {
            return null;
        }

        String comando = atual.getComando();
        atual = atual.getProximo();
        return comando;
    }

    // Encontra e retorna o conteúdo da linha pelo número dela
    public String encontraLinhaNumero(int lineNumber)
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

    // Metodo para retornar se a lista foi modificada
    public boolean foiModificado() {
        return modificado;
    }

    // Metodo para limpar a lista de modificações
    public void limpaModificacao() {
        modificado = false;
    }

    // Metodo para apagar (limpar) a lista
    public void limpaLista()
    {
        head = null;
        headErros = null;
        modificado = false;
        linhaAnterior = -1;
    }

    // Metodo para ir para o início da lista
    public void resetaIterator()
    {
        atual = head;
    }

    // Metodo para retornar erro de sequência
    public boolean retornaErroSequencia()
    {
        return erroSequencia;
    }

    // Metodo para retornar erro de código sem linha
    public boolean retornaErroSemLinha()
    {
        return erroSemLinha;
    }

    // Metodo para exibir todos os erros
    public void exibeErros()
    {
        Node atualErro = headErros;
        System.out.println("Aviso: arquivo contém sequência incorreta de linhas. \nLinhas incorretas:");
        while (atualErro != null)
        {
            System.out.println(atualErro.getComando());
            atualErro = atualErro.getProximo();
        }
    }
}
