/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
import java.io.*;
import java.util.Scanner;

// Realiza todas operações básicas de arquivo
public class VerificaArquivo {
    private static VerificaArquivo verificaArquivo;
    private LinkedList lista;
    private boolean arquivoCarregado;
    private String nomeArquivoCarregado;
    Scanner s = new Scanner (System.in);

    // Construtor
    private VerificaArquivo()
    {
        lista = new LinkedList();
    }

    public static VerificaArquivo getInstancia()
    {
        if (verificaArquivo == null)
        {
            verificaArquivo = new VerificaArquivo();
        }
        return verificaArquivo;
    }

    public void carregaArquivo(String arquivo)
    {
        LinkedList novaLista = new LinkedList();  // Cria uma nova lista temporária vazia
        try
        {
            // Limpar a lista antes de carregar novo arquivo

            // Criar um BufferedReader para ler o arquivo
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));

            // Variável para armazenar cada linha de comando
            String comando;

            // Ler o arquivo linha por linha
            while ((comando = reader.readLine()) != null)
            {
                try
                {
                    // Adicionar cada linha à lista
                    lista.addLine(comando);
                    novaLista.addLine(comando);
                }catch (Exception e)
                {
                    // Erro durante a gravação de linhas
                    System.out.println("Erro: " + e.getMessage());
                    lista.clearList();
                }
            }
            // Fechar o leitor
            reader.close();

            // Resetar a flag de modificação após carregar o arquivo
            if(arquivoCarregado)
            {
                lista = novaLista;
            }
            lista.clearModification();

            // Confirmação de carregamento
            System.out.println("Arquivo" + " ("+arquivo+") carregado com sucesso.");
            nomeArquivoCarregado = arquivo;
            arquivoCarregado = true;

        } catch (FileNotFoundException e)
        {
            System.out.println("Erro: Arquivo não encontrado - " + arquivo);
        } catch (IOException e)
        {
            System.out.println("Erro ao abrir o arquivo: " + arquivo);
        }
    }

    public void salvaCodigoFonte()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivoCarregado)))
        {
            lista.getNextCommand();
            lista.resetaIterator();
            String comando;
            while ((comando = lista.getNextCommand()) != null)
            {
                writer.write(comando);
                writer.newLine();
            }

            lista.clearModification();
            System.out.println("Arquivo" + " ("+nomeArquivoCarregado+") salvo com sucesso.");

        } catch (IOException e)
        {
            System.out.println("Erro ao salvar o arquivo: " + nomeArquivoCarregado);
        }
    }

    public void salvaCodigoFonteEmArquivo(String arquivo) {
        File file = new File(arquivo);

        // Verifica se o arquivo existe e pede confirmação caso precise sobrescrever
        if (file.exists())
        {
            System.out.println("O arquivo já existe. Deseja sobrescrever? (S/N)");
            String resposta = s.nextLine().trim().toUpperCase();

            if (!resposta.equals("S"))
            {
                System.out.println("Arquivo não salvo.");
                return;
            }
        }

        // Tentativa de salvar o arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            lista.getNextCommand();
            lista.resetaIterator();
            String comando;
            while ((comando = lista.getNextCommand()) != null)
            {
                writer.write(comando);
                writer.newLine();
            }

            System.out.println("Arquivo" + " ("+arquivo+") salvo com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + arquivo);
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

    public void limpaLista(){
        lista.clearList();
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

    public boolean comparaArquivos(String arquivo)
    {
         if(arquivo.equals(nomeArquivoCarregado))
         {
             return true;
         }

         return false;
    }

    public boolean arquivoCarregado()
    {
        return arquivoCarregado;
    }
}