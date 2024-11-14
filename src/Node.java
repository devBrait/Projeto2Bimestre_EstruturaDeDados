/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
public class Node {
    private String comando;
    private Node proximo;
    private int numeroLinha;

    // Construtor
    public Node(String comando, int numeroLinha)
    {
        this.comando = comando;
        this.proximo = null;
        this.numeroLinha = numeroLinha;
    }

    // Métodos get e set de comandos
    public String getComando()
    {
        return comando;
    }

    public void setComando(String comando)
    {
        this.comando = comando;
    }

    // Métodos get e set para proximo
    public Node getProximo()
    {
        return proximo;
    }

    public void setProximo(Node proximo)
    {
        this.proximo = proximo;
    }

    // Métodos get e set  de numeroLinha
    public int getNumeroLinha()
    {
        return numeroLinha;
    }

    public void setNumeroLinha(int numeroLinha)
    {
        this.numeroLinha = numeroLinha;
    }
}
