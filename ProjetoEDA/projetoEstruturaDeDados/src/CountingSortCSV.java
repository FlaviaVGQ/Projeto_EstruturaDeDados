import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountingSortCSV {
    public static void main(String[] args) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        countingSortCSV("desordenado_200000.csv", "ORDEM_desordenado.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para desordenado.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        countingSortCSV("ordenado_crescente_200000.csv", "ORDEM_ordenado_crescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_crescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        countingSortCSV("ordenado_decrescente_200000.csv", "ORDEM_ordenado_decrescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_decrescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        countingSortCSV("constante_200000.csv", "ORDEM_constante.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para constante.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        countingSortCSV("parcialmente_ordenado_desordenado_inicio_200000.csv", "ORDEM_parcialmente_ordenado_desordenado_inicio.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para parcialmente_ordenado_desordenado_inicio.csv: " + duration + " ms");
        
        startTime = System.currentTimeMillis();
        countingSortCSV("parcialmente_ordenado_desordenado_final_200000.csv", "ORDEM_parcialmente_ordenado_desordenado_final.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para parcialmente_ordenado_desordenado_final.csv: " + duration + " ms");
        
    }

    private static void countingSortCSV(String inputPath, String outputPath) {
        List<Integer> values = readCSV(inputPath);
        countingSort(values);
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

    private static void countingSort(List<Integer> values) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int value : values) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        int range = max - min + 1;
        int[] count = new int[range];

        for (int value : values) {
            count[value - min]++;
        }

        int k = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                values.set(k, i + min);
                count[i]--;
                k++;
            }
        }
    }
}
