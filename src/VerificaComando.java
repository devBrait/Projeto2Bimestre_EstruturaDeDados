/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
public class VerificaComando {

    Interpretador interpretador = new Interpretador();

    public void toInterpretacao(String comando){

        String[] partes = comando.split(" ");
        String instrucao = partes[1].toLowerCase();

        switch (instrucao) {
            case "mov":
                System.out.println("Copia o valor de y para o registrador x.");
                break;
            case "inc":
                System.out.println("Incrementa o valor armazenado no registrador x em 1.");
                break;
            case "dec":
                System.out.println("Decrementa o valor armazenado no registrador x em 1.");
                break;
            case "add":
                System.out.println("Adiciona o valor de y ao registrador x, armazenando o resultado em x.");
                break;
            case "sub":
                System.out.println("Subtrai o valor de y do registrador x, armazenando o resultado em x.");
                break;
            case "mul":
                System.out.println("Multiplica o valor de x pelo valor de y, armazenando o resultado em x.");
                break;
            case "div":
                System.out.println("Divide o valor de x pelo valor de y, armazenando o resultado em x.");
                break;
            case "jnz":
                System.out.println("Se o valor no registrador x for diferente de zero, pula para a linha de número y.");
                break;
            case "out":
                System.out.println("Exibe o valor do registrador x em tela e pula uma linha.");
                break;
            default:
                System.out.println("Erro: comando inválido.");
                break;
        }
    }
}
