/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
public class VerificaComando {

    private final Interpretador interpretador;
    // Construtor para injeção de dependência
    public VerificaComando(Interpretador interpretador)
    {
        this.interpretador = interpretador;
    }

    // Metodo que define qual comando foi informado e manda para o interpretador
    public void toInterpretacao(String comando)
    {
        String[] partes = comando.split(" ");
        if(partes.length < 3)
        {
            System.out.println("Linha sem um comando válido.");
            System.out.println("Linha: "+comando);
            return;
        }
        String instrucao = partes[1].toLowerCase();

        switch (instrucao) {
            case "mov":
                if (partes.length == 4)
                {
                    try
                    {
                        interpretador.executeMov(partes[2], partes[3], comando); // Executa o comando de mov
                    }catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando mov: ");
                    System.out.println("Linha: "+comando);
                }
                break;
            case "inc":
                if (partes.length == 3)
                {
                    try
                    {
                        interpretador.executeInc(partes[2], comando); // Executa o comando de inc
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando inc: ");
                    System.out.println("Linha: "+comando);
                }
                break;
            case "dec":
                if (partes.length == 3)
                {
                    try
                    {
                    interpretador.executeDec(partes[2], comando); // Executa o comando de dec
                    }catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando dec: ");
                    System.out.println("Linha: "+comando);
                }
                break;
            case "add":
                if (partes.length == 4)
                {
                    try
                    {
                        interpretador.executeAdd(partes[2].charAt(0), partes[3], comando); // Executa o comando de add
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando add: ");
                    System.out.println("Linha: "+comando);
                }
                break;
            case "sub":
                if (partes.length == 4)
                {
                    try
                    {
                        interpretador.executeSub(partes[2].charAt(0), partes[3], comando); // Executa o comando de sub
                    }catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando sub: ");
                    System.out.println("Linha: "+comando);
                }
                break;
            case "mul":
                if (partes.length == 4)
                {
                    try
                    {
                    interpretador.executeMul(partes[2].charAt(0), partes[3], comando); // Executa o comando de mul
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando mul: ");
                    System.out.println("Linha: "+comando);
                }
                break;
            case "div":
                if (partes.length == 4)
                {
                    try
                    {
                    interpretador.executeDiv(partes[2].charAt(0), partes[3], comando); // Executa o comando de div
                    }catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando div: ");
                    System.out.println("Linha: "+comando);
                }
                break;
            case "jnz":
                if (partes.length == 4)
                {
                    try
                    {
                        String linhaDestino = partes[3];
                        int linhaAtual = Integer.parseInt(partes[0]);
                        try
                        {
                            interpretador.executeJnz(partes[2].charAt(0), linhaDestino, linhaAtual, comando); // Executa o comando de jnz
                        }catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: linha inválida para o comando jnz.");
                        System.out.println("Linha: "+comando);
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando jnz: ");
                    System.out.println("Linha: "+comando);
                }
                break;
            case "out":
                if (partes.length == 3)
                {
                    try
                    {
                        interpretador.executeOut(partes[2].charAt(0), comando); // Executa o comando de out
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando out: ");
                    System.out.println("Linha: "+comando);
                }
                break;
            default:
                System.out.println("Erro: comando inválido."); // Caso o comando seja inválido
                System.out.println("Linha: "+comando);
                break;
        }
    }

    // Define todos os registradores como null
    public void limpaRegistradores()
    {
        interpretador.limpaRegistradores();
    }
}
