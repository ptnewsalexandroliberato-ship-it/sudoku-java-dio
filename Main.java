import java.util.*;

public class Main {
    // Record para representar cada célula do tabuleiro
    record Cell(int value, boolean isFixed) {}

    private static Cell[][] board = new Cell[9][9];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Se você passar os argumentos via VS Code, ele inicializa aqui
        if (args.length > 0) {
            parseInput(String.join(" ", args));
        }

        int option = -1;
        while (option != 7) {
            showMenu();
            try {
                option = Integer.parseInt(scanner.nextLine());
                handleOption(option);
            } catch (Exception e) {
                System.out.println("⚠️ Entrada inválida! Digite um número de 1 a 7.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== SUDOKU TERMINAL ELITE ===");
        System.out.println("1. Iniciar/Ver Tabuleiro");
        System.out.println("2. Colocar número");
        System.out.println("3. Remover número");
        System.out.println("4. Verificar status (Erros/Conclusão)");
        System.out.println("5. Limpar jogadas (Mantém fixos)");
        System.out.println("6. Dica do Expert (IA Mode)");
        System.out.println("7. Finalizar e Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void handleOption(int option) {
        switch (option) {
            case 1 -> displayBoard();
            case 2 -> placeNumber();
            case 3 -> removeNumber();
            case 4 -> checkStatus();
            case 5 -> resetBoard();
            case 6 -> System.out.println("💡 Dica: Verifique a linha e coluna antes de jogar!");
            case 7 -> System.out.println("Encerrando... Até a próxima!");
            default -> System.out.println("Opção inexistente.");
        }
    }

    // --- LÓGICA DE RENDERIZAÇÃO ---
    private static void displayBoard() {
        System.out.println("\n    0   1   2   3   4   5   6   7   8");
        System.out.println("  ╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗");
        for (int r = 0; r < 9; r++) {
            System.out.print(r + " ║");
            for (int c = 0; c < 9; c++) {
                Cell cell = board[r][c];
                String val = (cell == null || cell.value == 0) ? " " : String.valueOf(cell.value);
                System.out.print(" " + val + " ");
                if (c % 3 == 2 && c < 8) System.out.print("║");
                else if (c < 8) System.out.print("│");
            }
            System.out.println("║");
            if (r % 3 == 2 && r < 8) System.out.println("  ╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣");
            else if (r < 8) System.out.println("  ╟───┼───┼───╫───┼───┼───╫───┼───┼───╢");
        }
        System.out.println("  ╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝");
    }

    // --- LÓGICA DE PARSER (Lê os argumentos da DIO) ---
    private static void parseInput(String input) {
        String[] entries = input.split(" ");
        for (String entry : entries) {
            try {
                String[] parts = entry.split(";");
                String[] coords = parts[0].split(",");
                String[] data = parts[1].split(",");
                int r = Integer.parseInt(coords[0]);
                int c = Integer.parseInt(coords[1]);
                int val = Integer.parseInt(data[0]);
                boolean fixed = Boolean.parseBoolean(data[1]);
                board[r][c] = new Cell(val, fixed);
            } catch (Exception ignored) {}
        }
    }

    // Implemente aqui as regras de negócio para as opções 2, 3, 4 e 5!
    private static void placeNumber() { /* Lógica para inserir número */ }
    private static void removeNumber() { /* Lógica para remover (se não for fixed) */ }
    private static void checkStatus() { /* Lógica de validação */ }
    private static void resetBoard() { /* Mantém apenas os fixos */ }
}
