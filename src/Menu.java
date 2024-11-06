public class Menu {
    public void exibeMenu(){
        System.out.println("Opções disponíveis:");
        System.out.println("  LOAD <ARQUIVO.ED1>   Carrega o arquivo.");
        System.out.println("  LIST                 Exibe o código fonte.");
        System.out.println("  RUN                  Executa o código fonte.");
        System.out.println("  INS <LINHA> <INSTRUÇÃO>  Insere uma nova linha.");
        System.out.println("  DEL<LINHA>            Deleta uma determinada linha.");
        System.out.println("  DEL <LINHA_I> <LINHA_F> Deleta um intervalo de linhas.");
        System.out.println("  SAVE                  Salva o código fonte.");
        System.out.println("  SAVE <ARQUIVO.ED1>    Salva o código fonte em determinado arquivo.");
        System.out.println("  EXIT                  Encerra o programa.");
    }
}
