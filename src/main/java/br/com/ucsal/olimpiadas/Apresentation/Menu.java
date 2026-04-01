package br.com.ucsal.olimpiadas.Apresentation;

import br.com.ucsal.olimpiadas.Domain.*;
import java.util.List;
import java.util.Scanner;

public class Menu
{
    private static int opcaoEscolhida;

    public static String Execute(List<Opcao> opcoes, Scanner in) {
    while (true) {

        System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
        for (Opcao opcao : opcoes) {
            System.out.println(opcao.getId() + ") " + opcao.getTexto());
        }
        System.out.println("0) Sair");
        System.out.print("> ");

        try {
            opcaoEscolhida = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!");
            continue;
        }

        if (opcaoEscolhida == 0) {
            return "Saindo...";
        }

        boolean encontrada = false;

        for (Opcao opcao : opcoes) {
            if (opcao.getId() == opcaoEscolhida) {
                opcao.getUsecase().Execute(in);
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            System.out.println("Opção inválida!");
        }
    }
    }
}
