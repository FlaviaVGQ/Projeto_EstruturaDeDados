import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MergeSortCSV {
    public static void main(String[] args) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        mergeSortCSV("desordenado_200000.csv", "ORDEM_desordenado.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para desordenado.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        mergeSortCSV("ordenado_crescente_200000.csv", "ORDEM_ordenado_crescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_crescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        mergeSortCSV("ordenado_decrescente_200000.csv", "ORDEM_ordenado_decrescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_decrescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        mergeSortCSV("constante_200000.csv", "ORDEM_constante.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para constante.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        mergeSortCSV("parcialmente_ordenado_desordenado_inicio_200000.csv", "ORDEM_parcialmente_ordenado_desordenado_inicio.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para parcialmente_ordenado_desordenado_inicio.csv: " + duration + " ms");
        
        startTime = System.currentTimeMillis();
        mergeSortCSV("parcialmente_ordenado_desordenado_final_200000.csv", "ORDEM_parcialmente_ordenado_desordenado_final.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para parcialmente_ordenado_desordenado_final.csv: " + duration + " ms");
        
    }

    private static void mergeSortCSV(String inputPath, String outputPath) {
        List<Integer> values = readCSV(inputPath);
        mergeSort(values);
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

    private static void mergeSort(List<Integer> values) {
        if (values.size() <= 1) {
            return;
        }

        int mid = values.size() / 2;
        List<Integer> left = new ArrayList<>(values.subList(0, mid));
        List<Integer> right = new ArrayList<>(values.subList(mid, values.size()));

        mergeSort(left);
        mergeSort(right);

        merge(values, left, right);
    }

    private static void merge(List<Integer> values, List<Integer> left, List<Integer> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                values.set(k, left.get(i));
                i++;
            } else {
                values.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < left.size()) {
            values.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < right.size()) {
            values.set(k, right.get(j));
            j++;
            k++;
        }
    }
}
