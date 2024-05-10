import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuickSortCSV {
    public static void main(String[] args) {
        long startTime, endTime, duration;

        startTime = System.currentTimeMillis();
        quickSortCSV("desordenado_200000.csv", "ORDEM_desordenado.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para desordenado.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        quickSortCSV("ordenado_crescente_200000.csv", "ORDEM_ordenado_crescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_crescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        quickSortCSV("ordenado_decrescente_200000.csv", "ORDEM_ordenado_decrescente.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para ordenado_decrescente.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        quickSortCSV("constante_200000.csv", "ORDEM_constante.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Tempo de execução para constante.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        quickSortCSV("parcialmente_ordenado_desordenado_inicio_200000.csv",
                "ORDEM_parcialmente_ordenado_desordenado_inicio.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println(
                "Tempo de execução para parcialmente_ordenado_desordenado_inicio.csv: " + duration + " ms");

        startTime = System.currentTimeMillis();
        quickSortCSV("parcialmente_ordenado_desordenado_final_200000.csv",
                "ORDEM_parcialmente_ordenado_desordenado_final.csv");
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println(
                "Tempo de execução para parcialmente_ordenado_desordenado_final.csv: " + duration + " ms");
    }

    private static void quickSortCSV(String inputPath, String outputPath) {
        List<Integer> values = readCSV(inputPath);
        quickSort(values, 0, values.size() - 1);
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

    private static void quickSort(List<Integer> values, int low, int high) {
        while (low < high) {
            int partitionIndex = partition(values, low, high);

            // Ordenar a partição menor de forma recursiva
            if (partitionIndex - low < high - partitionIndex) {
                quickSort(values, low, partitionIndex - 1);
                low = partitionIndex + 1;
            } else {
                quickSort(values, partitionIndex + 1, high);
                high = partitionIndex - 1;
            }
        }
    }

    private static int partition(List<Integer> values, int low, int high) {
        int pivot = values.get(low);
        int i = low + 1;
        int j = high;

        while (i <= j) {
            if (values.get(i) <= pivot) {
                i++;
            } else if (values.get(j) > pivot) {
                j--;
            } else {
                swap(values, i, j);
                i++;
                j--;
            }
        }

        swap(values, low, j);
        return j;
    }

    private static void swap(List<Integer> values, int i, int j) {
        int temp = values.get(i);
        values.set(i, values.get(j));
        values.set(j, temp);
    }
}
