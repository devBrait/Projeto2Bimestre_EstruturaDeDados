/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
public class VerificaEntrada {

    Menu menu = new Menu();
    VerificaArquivo verificaArquivo = VerificaArquivo.getInstancia();
    VerificaComando verificaComando;
    Interpretador interpretador;

    public VerificaEntrada()
    {
        this.interpretador = new Interpretador();
        this.verificaComando = new VerificaComando(interpretador);
    }

    public void isValid(String expressao)
    {
        // Dividir a expressão em partes
        String[] partes = expressao.trim().split("\\s+");

        // Verificar o comando digitado
        switch(partes[0])
        {
            case "LOAD":
                // Verifica se o comando LOAD está correto
                if (partes.length == 2 && partes[1].matches(".*\\.ED1"))
                {
                    if(verificaArquivo.isModified())
                    {
                        System.out.println("Arquivo atual"+ "("+partes[1]+") contém alterações não salvas.");
                        if(verificaArquivo.confirmarSalvamento())
                        {
                            // Roda outra rotina antes que salva o arquivo atual
                            verificaArquivo.carregaArquivo(partes[1]);
                        }
                    }
                    verificaArquivo.carregaArquivo(partes[1]);
                } else
                {
                    System.out.println("Não é possível carregar um arquivo assim.");
                }
                break;
            case "LIST":
                // Verifica se o comando LIST foi digitado sem parâmetros
                if (partes.length == 1)
                {
                    verificaArquivo.retornaLista();
                } else
                {
                    System.out.println("Comando LIST não deve ter parâmetros");
                }
                break;
            case "RUN":
                // Verifica se o comando RUN foi digitado sem parâmetros
                if (partes.length == 1)
                {
                    if(verificaArquivo.arquivoCarregado())
                    {
                        LinkedList lista = verificaArquivo.getLista();
                        lista.resetaIterator();
                        String comando;
                        while ((comando = lista.getNextCommand()) != null)
                        {
                            verificaComando.toInterpretacao(comando);
                        }
                    }else
                    {
                        System.out.println("Nenhum arquivo foi carregado ainda.");
                    }

                } else
                {
                    System.out.println("Comando RUN não deve ter parâmetros");
                }
                break;
            case "INS":
                // Verifica se o comando INS tem pelo menos 3 partes (INS, número da linha, instrução)
                if (partes.length >= 3)
                {
                    // Verifica se o segundo parâmetro é um número
                    if (partes[1].matches("\\d+"))
                    {
                        System.out.println("Comando INS válido - Inserir linha " + partes[1]);
                    } else
                    {
                        System.out.println("Formato de INS inválido. Use INS <LINHA> <INSTRUÇÃO>");
                    }
                } else
                {
                    System.out.println("Formato de INS inválido. Use INS <LINHA> <INSTRUÇÃO>");
                }
                break;
            case "DEL":
                // Tratar dois formatos de DEL: DEL<LINHA> ou DEL <LINHA_I> <LINHA_F>
                if (partes.length == 2)
                {
                    // Formato DEL<LINHA> ou DEL <LINHA>
                    if (partes[1].matches("\\d+"))
                    {
                        System.out.println("Comando DEL válido para linha " + partes[1]);
                    } else
                    {
                        System.out.println("Formato de DEL inválido. Use DEL<LINHA> ou DEL <LINHA_I> <LINHA_F>");
                    }
                } else if (partes.length == 3) {
                    // Formato DEL <LINHA_I> <LINHA_F>
                    if (partes[1].matches("\\d+") && partes[2].matches("\\d+"))
                    {
                        System.out.println("Comando DEL válido para intervalo de linhas " + partes[1] + " a " + partes[2]);
                    } else
                    {
                        System.out.println("Formato de DEL inválido. Use DEL <LINHA_I> <LINHA_F>");
                    }
                } else
                {
                    System.out.println("Formato de DEL inválido.");
                }
                break;

            case "SAVE":
                // Tratar dois formatos de SAVE: SAVE ou SAVE <ARQUIVO.ED1>
                if (partes.length == 1)
                {
                    System.out.println("Comando SAVE válido - Salvar código fonte");
                } else if (partes.length == 2 && partes[1].matches(".*\\.ED1"))
                {
                    System.out.println("Comando SAVE válido para arquivo: " + partes[1]);
                } else
                {
                    System.out.println("Formato de SAVE inválido. Use SAVE ou SAVE <ARQUIVO.ED1>");
                }
                break;
            case "EXIT":
                if (partes.length == 1)
                {
                    System.out.println("Muito obrigado por utilizar nosso interpretador!");
                    System.exit(0);

                } else
                {
                    System.out.println("Comando EXIT não deve ter parâmetros");
                }
                break;
            case "HELP":
                if(partes.length == 1)
                {
                    menu.exibeMenu();
                    break;
                }
            default:
                System.out.println("Erro: comando inválido.");
        }
    }
}