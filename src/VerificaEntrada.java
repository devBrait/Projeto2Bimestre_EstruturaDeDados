/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
public class VerificaEntrada {

    // Declaração de variáveis e instâncias
    Menu menu = new Menu();
    VerificaArquivo verificaArquivo = VerificaArquivo.getInstancia();
    VerificaComando verificaComando;
    Interpretador interpretador;

    // Construtor
    public VerificaEntrada()
    {
        this.interpretador = new Interpretador();
        this.verificaComando = new VerificaComando(interpretador);
    }

    // Verifica se a expressao informada é válida
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
                    if(verificaArquivo.foiModificado()) // Verifica se o arquivo atual foi alterado
                    {
                        System.out.println("Arquivo atual"+ "("+partes[1]+") contém alterações não salvas.");
                        if(verificaArquivo.confirmarSalvamento())
                        {
                            verificaArquivo.salvaCodigoFonte(); // Salva o código na lista no arquivo atual
                            System.out.print("\n");
                        }
                    }
                    if(verificaArquivo.comparaArquivos(partes[1])) // Verifica se o load está abrindo o arquivo que já está aberto
                    {
                        try
                        {
                            verificaArquivo.limpaLista();
                            verificaArquivo.carregaArquivo(partes[1]);
                        }catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }else
                    {
                        try
                        {
                            verificaArquivo.carregaArquivo(partes[1]); // Carrega o novo arquivo
                        }catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }

                } else
                {
                    System.out.println("Não é possível carregar um arquivo assim.");
                }
                break;
            case "LIST":
                // Verifica se o comando LIST foi digitado sem parâmetros
                if (partes.length == 1)
                {
                    verificaArquivo.exibeLista(); // Exibe a lista em tela

                    if (verificaArquivo.retornaErroSemLinha())
                    {
                        System.out.println("Aviso: Seu arquivo contém erros, que não permitem executar ele.");
                    }

                    if(verificaArquivo.retornaErroSequencia())
                    {
                        verificaArquivo.exibeErros();
                    }
                } else
                {
                    System.out.println("Comando LIST não deve ter parâmetros");
                }
                break;
            case "RUN":
                // Verifica se o comando RUN foi digitado sem parâmetros
                if (partes.length == 1)
                {
                    if (!verificaArquivo.arquivoCarregado())
                    {
                        System.out.println("Nenhum arquivo foi carregado!");
                        return;
                    }

                    if (verificaArquivo.retornaErroSemLinha())
                    {
                        System.out.println("Aviso: Seu arquivo contém erros, que não permitem executar ele.");
                        return;
                    }

                    if(verificaArquivo.retornaErroSequencia())
                    {
                        verificaArquivo.exibeErros();
                        return;
                    }

                    LinkedList lista = verificaArquivo.retornaLista();
                    lista.resetaIterator();
                    verificaComando.limpaRegistradores();

                    // Caso nenhum código tenha sido inserido no arquivo
                    if (lista.proximoComando() == null)
                    {
                        System.out.println("Nenhum código foi inserido no arquivo.");
                        return;
                    }

                    lista.resetaIterator();
                    String comando;
                    while ((comando = lista.proximoComando()) != null)
                    {
                        verificaComando.toInterpretacao(comando); // Manda os comandos para serem interpretados
                    }

                } else
                {
                    System.out.println("Comando RUN não deve ter parâmetros.");
                }
                break;
            case "INS":
                // Verifica se o comando INS tem pelo menos 3 partes (INS, número da linha, instrução)
                if (partes.length >= 3)
                {
                    if (!verificaArquivo.arquivoCarregado())
                    {
                        System.out.println("Nenhum arquivo foi carregado!");
                        return;
                    }

                    if (verificaArquivo.retornaErroSemLinha())
                    {
                        System.out.println("Aviso: Seu arquivo contém erros, que não permitem executar ele.");
                        return;
                    }

                    if(verificaArquivo.retornaErroSequencia())
                    {
                        verificaArquivo.exibeErros();
                        return;
                    }

                    if (!partes[1].matches("\\d+"))
                    {
                        System.out.println("Formato de INS inválido. Use INS <LINHA> <INSTRUÇÃO>");
                        return;
                    }
                    LinkedList lista = verificaArquivo.retornaLista();
                    lista.resetaIterator();

                    String expressaoNova = expressao.substring(4);
                    lista.insereEspecifica(expressaoNova.toLowerCase(), Integer.parseInt(partes[1])); // Insere uma linha específica no código
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
                        if(!verificaArquivo.arquivoCarregado())
                        {
                            System.out.println("Nenhum arquivo foi carregado!");
                            return;
                        }

                        if(verificaArquivo.retornaErroSemLinha())
                        {
                            System.out.println("Aviso: Seu arquivo contém erros, que não permitem executar ele.");
                            return;
                        }

                        LinkedList lista = verificaArquivo.retornaLista();
                        lista.resetaIterator();
                        lista.deleta(Integer.parseInt(partes[1])); // Deleta exatamente uma linha no código
                    } else
                    {
                        System.out.println("Formato de DEL inválido. Use DEL<LINHA> ou DEL <LINHA_I> <LINHA_F>");
                    }
                } else if (partes.length == 3)
                {
                    // Formato DEL <LINHA_I> <LINHA_F>
                    if (partes[1].matches("\\d+") && partes[2].matches("\\d+"))
                    {
                        if(!verificaArquivo.arquivoCarregado())
                        {
                            System.out.println("Nenhum arquivo foi carregado!");
                        }

                        if(verificaArquivo.retornaErroSemLinha())
                        {
                            System.out.println("Aviso: Seu arquivo contém erros, que não permitem executar ele.");
                            return;
                        }

                        LinkedList lista = verificaArquivo.retornaLista();
                        lista.resetaIterator();
                        lista.deletaMul(Integer.parseInt(partes[1]), Integer.parseInt(partes[2])); // Deleta todas as linhas dentro do range

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
                if(verificaArquivo.arquivoCarregado())
                {
                    if (partes.length == 1)
                    {
                        verificaArquivo.salvaCodigoFonte(); // salva o código no arquivo que está aberto
                    } else if (partes.length == 2 && partes[1].matches(".*\\.ED1"))
                    {
                        verificaArquivo.salvaCodigoFonteEmArquivo(partes[1]); // salva o código em um arquivo especificio
                    } else
                    {
                        System.out.println("Formato de SAVE inválido. Use SAVE ou SAVE <ARQUIVO.ED1>");
                    }
                }else
                {
                    System.out.println("Nenhum arquivo foi carregado!");
                }
                break;
            case "EXIT":
                // Realiza o fim do sistema
                if (partes.length == 1)
                {
                    if(verificaArquivo.foiModificado())
                    {
                        System.out.println("Arquivo atual contém alterações não salvas.");
                        if(verificaArquivo.confirmarSalvamento())
                        {
                            verificaArquivo.salvaCodigoFonte();
                        }else
                        {
                            System.out.println("Muito obrigado por utilizar nosso interpretador!");
                            System.exit(0);
                        }
                    }
                    System.out.println("Muito obrigado por utilizar nosso interpretador!");
                    System.exit(0);

                } else
                {
                    System.out.println("Comando EXIT não deve ter parâmetros");
                }
                break;
            case "HELP":
                // Exibe o menu de ajuda com todos os comandos
                if(partes.length == 1)
                {
                    menu.exibeMenu();
                    break;
                }
                // Caso a expressao não seja um comando válido
            default:
                System.out.println("Erro: comando inválido.");
        }
    }
}