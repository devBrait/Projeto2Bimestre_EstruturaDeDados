/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */

import java.io.*;
import java.util.Scanner;

// Realiza todas operações básicas de arquivo
public class VerificaArquivo {

    // Definição de variáveis e instâncias
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

    // Metodo para carregar o arquivo informado
    public void carregaArquivo(String arquivo) throws Exception
    {
        LinkedList novaLista = new LinkedList();
        try
        {
            // Criar um BufferedReader para ler o arquivo
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));

            String comando;

            // Ler o arquivo linha por linha
            while ((comando = reader.readLine()) != null)
            {
                try
                {
                    novaLista.insere(comando);
                }catch (Exception e)
                {
                    throw new Exception("Erro: " + e.getMessage());
                }
            }
            // Fechar o leitor
            reader.close();


            // Confirmação de carregamento
            System.out.println("Arquivo" + " ("+arquivo+") carregado com sucesso.");
            nomeArquivoCarregado = arquivo;
            arquivoCarregado = true;
            lista = novaLista;
            if(lista.retornaErroSemLinha())
            {
                System.out.println("Aviso: arquivo contém trechos sem linhas!");
            }

            if(lista.retornaErroSequencia())
            {
                lista.exibeErros();
            }
            lista.limpaModificacao();  // Reseta a flag de modificação após carregar o arquivo
        } catch (FileNotFoundException e)
        {
            System.out.println("Erro: Arquivo não encontrado - " + arquivo);
        } catch (IOException e)
        {
            System.out.println("Erro ao abrir o arquivo: " + arquivo);
        }
    }

    // Metodo para salvar o código feito na lista dentro do arquivo
    public void salvaCodigoFonte()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivoCarregado)))
        {
            lista.proximoComando();
            lista.resetaIterator();
            String comando;
            while ((comando = lista.proximoComando()) != null)
            {
                writer.write(comando);
                writer.newLine();
            }

            lista.limpaModificacao();
            System.out.println("Arquivo" + " ("+nomeArquivoCarregado+") salvo com sucesso.");

        } catch (IOException e)
        {
            System.out.println("Erro ao salvar o arquivo: " + nomeArquivoCarregado);
        }
    }

    // Metodo para salvar arquivo específico
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
            lista.proximoComando();
            lista.resetaIterator();
            String comando;
            while ((comando = lista.proximoComando()) != null)
            {
                writer.write(comando);
                writer.newLine();
            }

            System.out.println("Arquivo" + " ("+arquivo+") salvo com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + arquivo);
        }
    }

    // Retorna a instância de LinkedList que contém os comandos
    public LinkedList retornaLista()
    {
        return lista;
    }

    // Metodo para verificar se a lista foi modificada
    public boolean foiModificado()
    {
        return lista.foiModificado();
    }

    // Retorna se tem erros de sequência
    public boolean retornaErroSequencia()
    {
        return lista.retornaErroSequencia();
    }

    // Retorna erro de código sem linha
    public boolean retornaErroSemLinha()
    {
        return lista.retornaErroSemLinha();
    }

    // Exibe todos os erros em tela
    public void exibeErros()
    {
        lista.exibeErros();
    }

    // Exibe toda a lista
    public void exibeLista()
    {
        try
        {
            lista.exibeLista();
        } catch (Exception e)
        {
           if(arquivoCarregado)
           {
               System.out.println("Nenhum código foi inserido no arquivo.");
           }else
           {
               System.out.println(e.getMessage());
           }
        }
    }

    // Define a lista para o default
    public void limpaLista(){
        lista.limpaLista();
    }

    // Metodo para verificar se o arquivo vai ser salvo ou não
    public boolean confirmarSalvamento()
    {
        System.out.println("Deseja salvar? (S/N)");
        String expressao = s.nextLine().trim().toUpperCase();

        if (!expressao.equals("S") && !expressao.equals("N"))
        {
            System.out.println("Comando inválido. Digite S ou N!");
            return false;
        }

        return expressao.equals("S");
    }

    // Metodo para comparar se o arquivo é o mesmo que já está carregado
    public boolean comparaArquivos(String arquivo)
    {
         if(arquivo.equals(nomeArquivoCarregado))
         {
             return true;
         }

         return false;
    }

    // Metodo para voltar se um arquivo já foi carregado
    public boolean arquivoCarregado()
    {
        return arquivoCarregado;
    }
}