/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

Referências:

    - LinkedList em java: https://www.w3schools.com/java/java_linkedlist.asp

    - Injeção de dependência: https://medium.com/@annarafadev/inje%C3%A7%C3%A3o-de-depend%C3%AAncia-o-que-%C3%A9-e-como-usar-7ec564b11b60

    - Manipulação de strings: https://www.academicotech.com/post/manipulando-strings-com-java-10-funcoes-e-metodos-uteis

    - Diferença entre int e integer: https://www.alura.com.br/artigos/diferenca-entre-int-e-integer-em-java

    - Leitura de arquivos: https://www.dio.me/articles/lendo-conteudo-de-arquivo-txt-com-java

    - Documentação sobre buffered reader: https://www.baeldung.com/java-buffered-reader

 */
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // Instância das classes necessárias
        Scanner s = new Scanner(System.in);
        Menu menu = new Menu();
        VerificaEntrada verificaEntrada = new VerificaEntrada();

        // Mensagem de boas vindas e exibe o menu de ajuda
        System.out.println("Seja bem-vindo ao nosso Intepretador de Assembly!!!\n");
        menu.exibeMenu();

        // Loop para continuar solicitando input do usuário
        while(true)
        {
            System.out.print("\n> ");
            String expressao = s.nextLine().trim().toUpperCase();

            verificaEntrada.isValid(expressao); // verfica se expressão é válida
        }
    }
}