/*

Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        Scanner s = new Scanner(System.in);
        Menu menu = new Menu();
        VerificaEntrada verificaEntrada = new VerificaEntrada();

        System.out.println("Seja bem-vindo ao nosso Intepretador de Assembly!!!\n");
        menu.exibeMenu();

        while(true)
        {
            System.out.print("\n> ");
            String expressao = s.nextLine().trim().toUpperCase();

            verificaEntrada.isValid(expressao);
        }
    }
}