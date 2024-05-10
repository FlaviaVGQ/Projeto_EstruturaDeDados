import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeradorCSV {
    private static final int[] NUM_ROWS = { 200_000, 400_000, 600_000, 800_000, 1_000_000 }; // Número de linhas no arquivo CSV
    private static final int PARTIALLY_SORTED_PERCENTAGE = 90; // Porcentagem de valores ordenados no arquivo parcialmente ordenado

    public static void main(String[] args) {
        generateUnsortedCSV();
        generateSortedCSV(true); // Ordem crescente
        generateSortedCSV(false); // Ordem decrescente
        generateConstantCSV();
        generatePartiallySortedCSVDesordInit();
        generatePartiallySortedCSVDesordFinal();
    }

    private static void generateUnsortedCSV() {
        for (int numRows : NUM_ROWS) {
            String fileName = "desordenado_" + numRows + ".csv";
            try (FileWriter writer = new FileWriter(fileName)) {
                Random random = new Random();
                for (int i = 0; i < numRows; i++) {
                    int value = random.nextInt(numRows);
                    writer.append(Integer.toString(value));
                    writer.append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateSortedCSV(boolean ascendingOrder) {
        for (int numRows : NUM_ROWS) {
            String fileName = (ascendingOrder ? "ordenado_crescente_" : "ordenado_decrescente_") + numRows + ".csv";
            try (FileWriter writer = new FileWriter(fileName)) {
                for (int i = 0; i < numRows; i++) {
                    int value = ascendingOrder ? i : numRows - i;
                    writer.append(Integer.toString(value));
                    writer.append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateConstantCSV() {
        for (int numRows : NUM_ROWS) {
            String fileName = "constante_" + numRows + ".csv";
            try (FileWriter writer = new FileWriter(fileName)) {
                int constantValue = 42; // Valor constante para todas as linhas
                for (int i = 0; i < numRows; i++) {
                    writer.append(Integer.toString(constantValue));
                    writer.append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generatePartiallySortedCSVDesordInit() {
        for (int numRows : NUM_ROWS) {
            String fileName = "parcialmente_ordenado_desordenado_inicio_" + numRows + ".csv";
            try (FileWriter writer = new FileWriter(fileName)) {
                Random random = new Random();
                int partiallySortedSize = (int) (numRows * (PARTIALLY_SORTED_PERCENTAGE / 100.0));
                int unorderedSize = numRows - partiallySortedSize;

                // Gera os valores desordenados no início
                for (int i = 0; i < unorderedSize; i++) {
                    int value = random.nextInt(numRows);
                    writer.append(Integer.toString(value));
                    writer.append('\n');
                }

                // Gera os valores ordenados no restante
                for (int i = unorderedSize; i < numRows; i++) {
                    writer.append(Integer.toString(i - unorderedSize));
                    writer.append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void generatePartiallySortedCSVDesordFinal() {
        for (int numRows : NUM_ROWS) {
            String fileName = "parcialmente_ordenado_desordenado_final_" + numRows + ".csv";
            try (FileWriter writer = new FileWriter(fileName)) {
                Random random = new Random();
                int partiallySortedSize2 = (int) (numRows * (PARTIALLY_SORTED_PERCENTAGE / 100.0));
                int unorderedSize = numRows - partiallySortedSize2;

                // Gera os valores ordenados no início
                for (int i = 0; i < partiallySortedSize2; i++) {
                    writer.append(Integer.toString(i));
                    writer.append('\n');
                }

                // Gera os valores desordenados no final
                for (int i = partiallySortedSize2; i < numRows; i++) {
                    int value = random.nextInt(numRows);
                    writer.append(Integer.toString(value));
                    writer.append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
