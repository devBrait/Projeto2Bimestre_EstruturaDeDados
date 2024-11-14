/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
public class Menu {
    // Exibe um menu de comandos em tela
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
