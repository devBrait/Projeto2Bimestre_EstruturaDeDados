public class VerificaComando {

    private Interpretador interpretador;

    // Construtor para injeção de dependência
    public VerificaComando(Interpretador interpretador)
    {
        this.interpretador = interpretador;
    }

    public void toInterpretacao(String comando)
    {
        String[] partes = comando.split(" ");
        String instrucao = partes[1].toLowerCase();

        switch (instrucao) {
            case "mov":
                if (partes.length == 4) {
                    interpretador.executeMov(partes[2], partes[3]);
                } else {
                    System.out.println("Erro: formato incorreto para o comando mov.");
                }
                break;

            case "inc":
                if (partes.length == 3) {
                    interpretador.executeInc(partes[2].charAt(0));
                } else {
                    System.out.println("Erro: formato incorreto para o comando inc.");
                }
                break;

            case "dec":
                if (partes.length == 3) {
                    interpretador.executeDec(partes[2].charAt(0));
                } else {
                    System.out.println("Erro: formato incorreto para o comando dec.");
                }
                break;

            case "add":
                if (partes.length == 4) {
                    interpretador.executeAdd(partes[2].charAt(0), partes[3].charAt(0));
                } else {
                    System.out.println("Erro: formato incorreto para o comando add.");
                }
                break;

            case "sub":
                if (partes.length == 4) {
                    interpretador.executeSub(partes[2].charAt(0), partes[3]);
                } else {
                    System.out.println("Erro: formato incorreto para o comando sub.");
                }
                break;

            case "mul":
                if (partes.length == 4) {
                    interpretador.executeMul(partes[2].charAt(0), partes[3].charAt(0));
                } else {
                    System.out.println("Erro: formato incorreto para o comando mul.");
                }
                break;

            case "div":
                if (partes.length == 4) {
                    interpretador.executeDiv(partes[2].charAt(0), partes[3].charAt(0));
                } else {
                    System.out.println("Erro: formato incorreto para o comando div.");
                }
                break;

            case "jnz":
                if (partes.length == 4)
                {
                    try
                    {
                        String linhaDestino = partes[3];
                        int linhaAtual = Integer.parseInt(partes[0]);
                        interpretador.executeJnz(partes[2].charAt(0), linhaDestino, linhaAtual);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: linha inválida para o comando jnz.");
                    }
                } else
                {
                    System.out.println("Erro: formato incorreto para o comando jnz.");
                }
                break;
            case "out":
                if (partes.length == 3) {
                    interpretador.executeOut(partes[2].charAt(0));
                } else {
                    System.out.println("Erro: formato incorreto para o comando out.");
                }
                break;

            default:
                System.out.println("Erro: comando inválido.");
                break;
        }
    }
}
