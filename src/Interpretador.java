/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
public class Interpretador {

    VerificaArquivo verificaArquivo = VerificaArquivo.getInstancia();
    private final VerificaComando verificaComando;

    // Construtor padrão
    public Interpretador()
    {
        this.verificaComando = new VerificaComando(this);
    }

    // Construtor com injeção de dependência para VerificaComando
    public Interpretador(VerificaComando verificaComando)
    {
        this.verificaComando = verificaComando;
    }


    // Registradores de 'a' até 'z'
    private final Integer[] registradores = new Integer[26];

    // Copia o valor de y para o registrador x
    public void executeMov(String x, String y, String comando) throws Exception
    {
        try
        {
            if (isNumero(y))
            {
                defineValorRegistrador(x.charAt(0), Integer.parseInt(y));
            } else if (retornaValorRegistrador(y.charAt(0)) == null)
            {
                System.out.printf("Erro: registrador %s inválido.%n", y);
                System.out.println("Linha "+comando);
                return;
            } else
            {
                defineValorRegistrador(x.charAt(0), retornaValorRegistrador(y.charAt(0)));
            }
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Linha: "+comando);
        }
        catch (Exception ex)
        {
            throw new Exception("Erro: impossível executar o comando mov.");
        }
    }

    // Incrementa o valor de x em 1
    public void executeInc(String x, String comando) throws Exception
    {
        try
        {
            if(isNumero(x))
            {
                System.out.println("Um registrador deve ser informado.");
                return;
            }

            Integer valorX = retornaValorRegistrador(x.charAt(0));

            if (valorX == null)
            {
                System.out.println("Registrador " + x + "não definido ainda.");
                System.out.println("Linha "+comando);
            }
            defineValorRegistrador(x.charAt(0), valorX + 1);
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Linha: "+comando);
        }
        catch (Exception ex)
        {
            throw new Exception("Erro: impossível executar o comando inc.");
        }
    }

    // Decrementa o valor de x em 1
    public void executeDec(String x, String comando) throws Exception
    {
        try
        {
            if(isNumero(x))
            {
                System.out.println("Um registrador deve ser informado.");
                return;
            }

            Integer valorX = retornaValorRegistrador(x.charAt(0));
            if (valorX == null)
            {
                System.out.println("Registrador " + x + "não definido ainda.");
                System.out.println("Linha "+comando);
            }
            defineValorRegistrador(x.charAt(0), valorX - 1);
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Linha: "+comando);
        }
        catch (Exception ex)
        {
            throw new Exception("Erro: impossível executar o comando dec.");
        }
    }

    // Adiciona o valor de y ao registrador x
    public void executeAdd(char x, String y, String comando) throws Exception
    {
        try
        {
            Integer valorX = retornaValorRegistrador(x);
            if (valorX == null)
            {
                System.out.printf("Erro: registrador %s inválido.%n", x);
                System.out.println("Linha "+comando);
                return;
            }

            if (isNumero(y))
            {
                defineValorRegistrador(x, retornaValorRegistrador(x) + Integer.parseInt(y));
            } else if (retornaValorRegistrador(y.charAt(0)) == null)
            {
                System.out.println("Registrador " + y + "não definido ainda.");
                System.out.println("Linha "+comando);
            } else
            {
                defineValorRegistrador(x, retornaValorRegistrador(x) + retornaValorRegistrador(y.charAt(0)));
            }
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Linha: "+comando);
        }
        catch (Exception ex)
        {
            throw new Exception("Erro: impossível executar o comando add.");
        }
    }

    // Subtrai o valor de y do registrador x
    public void executeSub(char x, String y, String comando) throws Exception
    {
        try
        {
            Integer valorX = retornaValorRegistrador(x);
            if (valorX == null)
            {
                System.out.printf("Erro: registrador %s inválido.%n", x);
                System.out.println("Linha "+comando);
                return;
            }

            if (isNumero(y))
            {
                defineValorRegistrador(x, retornaValorRegistrador(x) - Integer.parseInt(y));
            } else if (retornaValorRegistrador(y.charAt(0)) == null)
            {
                System.out.println("Registrador " + y + "não definido ainda.");
                System.out.println("Linha "+comando);
            } else
            {
                defineValorRegistrador(x, retornaValorRegistrador(x) - retornaValorRegistrador(y.charAt(0)));
            }
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Linha: "+comando);
        }
        catch (Exception ex)
        {
            throw new Exception("Erro: impossível executar o comando sub.");
        }
    }

    // Multiplica o valor de x pelo valor de y
    public void executeMul(char x, String y, String comando) throws Exception
    {
        try
        {
            Integer valorX = retornaValorRegistrador(x);
            if (valorX == null)
            {
                System.out.printf("Erro: registrador %s inválido.%n", x);
                System.out.println("Linha "+comando);
                return;
            }

            if (isNumero(y))
            {
                defineValorRegistrador(x, retornaValorRegistrador(x) * Integer.parseInt(y));
            } else if (retornaValorRegistrador(y.charAt(0)) == null)
            {
                System.out.println("Registrador " + y + "não definido ainda.");
                System.out.println("Linha "+comando);
            } else
            {
                defineValorRegistrador(x, retornaValorRegistrador(x) * retornaValorRegistrador(y.charAt(0)));
            }
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Linha: "+comando);
        }
        catch (Exception e)
        {
            throw new Exception("Erro: impossível executar o comando mul.");
        }
    }

    // Divide o valor de x pelo valor de y
    public void executeDiv(char x, String y, String comando) throws Exception
    {
        try
        {
            Integer valorX = retornaValorRegistrador(x);

            if (valorX == null)
            {
                System.out.printf("Erro: registrador %s inválido.%n", x);
                System.out.println("Linha "+comando);
                return;
            }

            if (isNumero(y))
            {
                // Se y é um número, verifica se é zero
                int valorY = Integer.parseInt(y);
                if (valorY == 0)
                {
                    System.out.println("Erro: Divisão por zero!");
                    return;
                }
                defineValorRegistrador(x, valorX / valorY);
            } else
            {
                // Se y não é um número, verifica se é um registrador válido
                Integer valorYRegistrador = retornaValorRegistrador(y.charAt(0));
                if (valorYRegistrador == null)
                {
                    System.out.printf("Erro: registrador %s inválido.%n", y);
                    System.out.println("Linha: " + comando);
                    return;
                }

                // Verifica se o valor do registrador y é zero
                if (valorYRegistrador == 0)
                {
                    System.out.println("Erro: Divisão por zero!");
                    return;
                }

                defineValorRegistrador(x, valorX / valorYRegistrador);
            }
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Linha: "+comando);
        }
        catch (Exception e)
        {
            throw new Exception("Erro: impossível executar o comando div.");
        }
    }

    // Se o valor no registrador x for diferente de zero, pula para a linha de número y
    public void executeJnz(char x, String linhaDestino, int linhaAtual, String comandoLinha)
            throws Exception
    {
        try {
            Integer valorRegistrador = retornaValorRegistrador(x);
            Integer linhaDestinoNumero = null;

            if (valorRegistrador == null)
            {
                System.out.println("Registrador " + x + "não definido.");
                System.out.println("Linha "+comandoLinha);
                return;
            }

            if (isNumero(linhaDestino))
            {
                linhaDestinoNumero = Integer.parseInt(linhaDestino);
            } else
            {
                linhaDestinoNumero = retornaValorRegistrador(linhaDestino.charAt(0));
                if(linhaDestinoNumero == null)
                {
                    System.out.println("Registrador " + linhaDestino + "não definido.");
                    System.out.println("Linha "+comandoLinha);
                    return;
                }
            }

            // Se o valor do registrador for zero, não entra no laço
            LinkedList lista = verificaArquivo.retornaLista();
            if (valorRegistrador != 0)
            {
                String comando;
                lista.resetaIterator();

                // Processa os comandos até a linha atual
                while ((comando = lista.proximoComando()) != null)
                {
                    String[] partes = comando.split(" ");
                    int linhaComando = Integer.parseInt(partes[0]);

                    // Se a linha do comando estiver entre a linha de destino e a linha atual
                    if (linhaComando >= linhaDestinoNumero && linhaComando <= linhaAtual)
                    {
                        // Chama a função de interpretação do comando
                        verificaComando.toInterpretacao(comando);
                    }
                }
            } else // Caso for zero executa os comandos restantes na lista
            {
                String comando;
                lista.resetaIterator();

                // Processa os comandos para frente da linha atual
                while ((comando = lista.proximoComando()) != null)
                {
                    String[] partes = comando.split(" ");
                    int linhaComando = Integer.parseInt(partes[0]);

                    // Se a linha do comando estiver na frente da linha atual
                    if (linhaComando > linhaAtual)
                    {
                        verificaComando.toInterpretacao(comando);
                    }
                }
            }
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Linha: "+comandoLinha);
        }
        catch (Exception e)
        {
            throw new Exception("Erro: impossível executar o comando jnz.");
        }
    }

    // Exibe o valor do registrador x em tela
    public void executeOut(char x, String comando) throws Exception
    {
        try
        {
            Integer valorX = retornaValorRegistrador(x);
            if (valorX == null)
            {
                System.out.printf("Erro: registrador %s inválido.%n", x);
                System.out.println("Linha: " + comando);
                return;
            }
            System.out.println(retornaValorRegistrador(x));
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Linha: "+comando);
        }
        catch (Exception e)
        {
            throw new Exception("Erro: impossível executar comando out.");
        }
    }

    // Define todos registradores como null
    public void limpaRegistradores()
    {
        for(int i = 0; i<26; i++)
        {
            registradores[i] = null;
        }
    }

    // Métodos auxiliares privados

    // Retorna o valor de determinado registrador ou null
    private Integer retornaValorRegistrador(char reg)
    {
        int indice = reg - 'a';
        if(indice >= 0 && indice < 26)
        {
            return registradores[indice];
        }else
        {
            throw new IllegalArgumentException("Erro: registrador informado inválido. Use letras de A até Z");
        }
    }

    // Define o valor de determinado registrador
    private void defineValorRegistrador(char reg, int valor)
    {
        int indice = reg - 'a';
        if(indice >= 0 && indice < 26)
        {
            registradores[indice] = valor;
        }else
        {
            throw new IllegalArgumentException("Erro: registrador informado inválido. Use letras de A até Z");
        }
    }

    // Verifica se o valor é um número
    private boolean isNumero(String str)
    {
        try
        {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }
}