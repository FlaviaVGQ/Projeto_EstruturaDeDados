import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeapSortCSV {
    public static void main(String[] args) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        heapSortCSV("desordenado_200000.csv", "ORDEM_desordenado.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para desordenado.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        heapSortCSV("ordenado_crescente_200000.csv", "ORDEM_ordenado_crescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_crescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        heapSortCSV("ordenado_decrescente_200000.csv", "ORDEM_ordenado_decrescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_decrescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        heapSortCSV("constante_200000.csv", "ORDEM_constante.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para constante.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        heapSortCSV("parcialmente_ordenado_desordenado_inicio_200000.csv", "ORDEM_parcialmente_ordenado_desordenado_inicio.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para parcialmente_ordenado_desordenado_inicio.csv: " + duration + " ms");
        
        startTime = System.currentTimeMillis();
        heapSortCSV("parcialmente_ordenado_desordenado_final_200000.csv", "ORDEM_parcialmente_ordenado_desordenado_final.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para parcialmente_ordenado_desordenado_final.csv: " + duration + " ms");
    }

    private static void heapSortCSV(String inputPath, String outputPath) {
        List<Integer> values = readCSV(inputPath);
        heapSort(values);
        writeCSV(outputPath, values);
    }

    private static List<Integer> readCSV(String filePath) {
        List<Integer> values = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                values.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }

    private static void writeCSV(String filePath, List<Integer> values) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Integer value : values) {
                writer.write(value.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void heapSort(List<Integer> values) {
        int n = values.size();

        // Construir o heap máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(values, n, i);
        }

        // Extrair elementos do heap um por um
        for (int i = n - 1; i >= 0; i--) {
            // Move a raiz (maior elemento) para o final
            int temp = values.get(0);
            values.set(0, values.get(i));
            values.set(i, temp);

            // Chama a função heapify na subárvore reduzida
            heapify(values, i, 0);
        }
    }

    private static void heapify(List<Integer> values, int n, int i) {
        int largest = i; // Inicializa o maior como raiz
        int left = 2 * i + 1; // Filho esquerdo do nó i
        int right = 2 * i + 2; // Filho direito do nó i

        // Se o filho esquerdo é maior que a raiz
        if (left < n && values.get(left) > values.get(largest)) {
            largest = left;
        }

        // Se o filho direito é maior que a raiz
        if (right < n && values.get(right) > values.get(largest)) {
            largest = right;
        }

        // Se o maior não é a raiz
        if (largest != i) {
            int swap = values.get(i);
            values.set(i, values.get(largest));
            values.set(largest, swap);

            
            heapify(values, n, largest);
        }
    }
}
