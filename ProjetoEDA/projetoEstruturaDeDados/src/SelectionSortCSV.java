import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SelectionSortCSV {
    public static void main(String[] args) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        selectionSortCSV("desordenado_200000.csv", "ORDEM_desordenado.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para desordenado.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        selectionSortCSV("ordenado_crescente_200000.csv", "ORDEM_ordenado_crescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_crescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        selectionSortCSV("ordenado_decrescente_200000.csv", "ORDEM_ordenado_decrescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_decrescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        selectionSortCSV("constante_200000.csv", "ORDEM_constante.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para constante.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        selectionSortCSV("parcialmente_ordenado_desordenado_inicio_200000.csv", "ORDEM_parcialmente_ordenado_desordenado_inicio.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para parcialmente_ordenado_desordenado_inicio.csv: " + duration + " ms");
        
        startTime = System.currentTimeMillis();
        selectionSortCSV("parcialmente_ordenado_desordenado_final_200000.csv", "ORDEM_parcialmente_ordenado_desordenado_final.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para parcialmente_ordenado_desordenado_final.csv: " + duration + " ms");
        
    }

    private static void selectionSortCSV(String inputPath, String outputPath) {
        List<Integer> values = readCSV(inputPath);
        selectionSort(values);
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

    private static void selectionSort(List<Integer> values) {
        int n = values.size();
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (values.get(j) < values.get(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = values.get(i);
                values.set(i, values.get(minIndex));
                values.set(minIndex, temp);
            }
        }
    }
}
