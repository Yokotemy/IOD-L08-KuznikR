package pl.poznan.put.cs.io.errors.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls loading data from the given input.
 */
/**
 * Odczytuje macierz sąsiedztwa z pliku lub standardowego wejścia
 */
public class DataInput {

    private String inputName;

    private int size;

    private List<Double> numbers = new ArrayList<Double>();

    int[][] matrix;

    /**
     * Tworzy czytnik danych wejściowych
     *
     * @param inputName nazwa pliku wejściowego lub {@code console} dla
     *                  standardowego wejścia
     */
    public DataInput(String inputName) {
        this.inputName = inputName;

    }

    /** Inicjalizuje macierz wartościami zerowymi */
    private void initializeMatrix() {
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    /**
     * Wczytuje macierz. Pierwszy wiersz zawiera jej rozmiar, a kolejne wiersze
     * zawierają wartości macierzy
     *
     * Każdy znak w wierszu wejściowym trafia do odpowiadającej mu kolumny
     * tego samego wiersza. Zachowanie tej zgodności jest konieczne, ponieważ
     * BFS interpretuje macierz jako relacje między węzłami; zapis do
     * niewłaściwych indeksów zmieniałby graf i prowadził do błędnego wyniku
     *
     * @throws Exception
     */
    public void load() throws Exception {
        numbers.clear();
        BufferedReader input = getBufferedReader();
        try {
            String line = null;
            int y = 0;
            // read operation
            line = input.readLine();
            if (line != null) {
                size = Integer.parseInt(line.trim());
            }
            initializeMatrix();
            while ((line = input.readLine()) != null && line.length() > 0) {
                for (int i = 0; i < line.length(); i++) {
                    matrix[y][i] = Integer.parseInt(String.valueOf(line.charAt(i)));
                }
                y++;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                throw ex;
            }
        }
    }

    /**
     * Tworzy czytnik dla skonfigurowanego źródła danych wejściowych
     *
     * @return czytnik pliku lub standardowego wejścia
     * @throws FileNotFoundException jeśli plik wejściowy nie istnieje
     */
    private BufferedReader getBufferedReader() throws FileNotFoundException {
        if (inputName.equalsIgnoreCase("console")) {
            return new BufferedReader(new InputStreamReader(System.in));
        }
        return new BufferedReader(new FileReader(new File(inputName)));
    }

    /**
     * Zwraca macierz wczytaną przez {@link #load()}
     *
     * @return wczytana macierz sąsiedztwa
     */
    public int[][] getMatrix() {
        return matrix;
    }

}
