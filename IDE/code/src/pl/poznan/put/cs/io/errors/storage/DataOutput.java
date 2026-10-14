package pl.poznan.put.cs.io.errors.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Controls saving data to the given output.
 */
/**
 * Zapisuje listę indeksów węzłów do pliku lub standardowego wyjścia
 */
public class DataOutput {

    private String outputName = null;

    /**
     * Tworzy zapis wyników
     *
     * @param outputName nazwa pliku wyjściowego lub {@code console} dla
     *                   standardowego wyjścia
     */
    public DataOutput(String outputName) {
        this.outputName = outputName;
    }

    /**
     * Zapisuje wynik, umieszczając jeden indeks węzła w każdym wierszu
     *
     * @param result lista indeksów węzłów
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void save(List<Integer> result) throws Exception {
        BufferedWriter output = getBufferedWriter();
        try {

            output.write("");
            for (Integer i : result) {
                output.append(i.toString());
                output.newLine();
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException ex) {
                throw ex;
            }
        }

    }

    /**
     * Tworzy zapis dla skonfigurowanego miejsca docelowego
     *
     * @return zapis do pliku lub standardowego wyjścia
     * @throws IOException jeśli nie można otworzyć pliku wyjściowego
     */
    private BufferedWriter getBufferedWriter() throws IOException {
        if (outputName.equalsIgnoreCase("console")) {
            return new BufferedWriter(new OutputStreamWriter(System.out));
        }
        return new BufferedWriter(new FileWriter(new File(outputName)));
    }

}
