/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Realiza todas operações básicas de arquivo
public class VerificaArquivo {
    Scanner s = new Scanner (System.in);
    private LinkedList lista;
    private boolean arquivoCarregado;

    // Construtor
    public VerificaArquivo()
    {
        this.lista = new LinkedList(); // Inicializa a lista
    }

    public void carregaArquivo(String arquivo)
    {
        try {
            // Limpar a lista antes de carregar novo arquivo
            lista = new LinkedList(); // Cria uma nova lista vazia

            // Criar um BufferedReader para ler o arquivo
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));

            // Variável para armazenar cada linha de comando
            String comando;

            // Ler o arquivo linha por linha
            while ((comando = reader.readLine()) != null)
            {
                // Adicionar cada linha à lista
                lista.addLine(comando);
            }

            // Fechar o leitor
            reader.close();

            // Resetar a flag de modificação após carregar o arquivo
            lista.clearModification();

            // Confirmação de carregamento
            System.out.println("Arquivo" + "("+arquivo+") carregado com sucesso.");
            arquivoCarregado = true;

        } catch (FileNotFoundException e)
        {
            System.out.println("Erro: Arquivo não encontrado - " + arquivo);
        } catch (IOException e)
        {
            System.out.println("Erro ao abrir o arquivo: " + arquivo);
        }
    }

    public LinkedList getLista() {
        return lista; // Retorna a instância de LinkedList que contém os comandos
    }

    // Metodo para verificar se a lista foi modificada
    public boolean isModified()
    {
        return lista.isModified();
    }

    public void retornaLista()
    {
        lista.retornaLista();
    }

    public boolean confirmarSalvamento()
    {
        System.out.println("Deseja salvar? (S/N)");
        String expressao = s.nextLine().trim().toUpperCase();

        // Verificação correta usando equals para comparação de strings
        if (!expressao.equals("S") && !expressao.equals("N"))
        {
            System.out.println("Comando inválido. Digite S ou N!");
            return false;
        }

        return expressao.equals("S");
    }

    public boolean arquivoCarregado()
    {
        return arquivoCarregado;
    }
}