package pl.poznan.put.cs.io.errors;

import java.util.List;

import pl.poznan.put.cs.io.errors.processors.BFSProcessor;
import pl.poznan.put.cs.io.errors.storage.DataInput;
import pl.poznan.put.cs.io.errors.storage.DataOutput;

/**
 * This is the controller class, which reads the input, runs the computing and writes the output.
 */
/**
 * Koordynuje odczytywanie grafu, uruchamianie procesora BFS oraz zapisywanie
 * wyniku
 */
public class Controller {

    private DataInput input;
    private DataOutput output;
    BFSProcessor bFSProcessor = new BFSProcessor();

    /**
     * Tworzy kontroler korzystający z wybranych źródeł wejścia i wyjścia
     *
     * @param inputName nazwa pliku wejściowego dla standardowego wejścia
     *
     * @param outputName nazwa pliku wyjściowego dla standardowego wyjścia
     */
    public Controller(String inputName, String outputName) {
        input = new DataInput(inputName);
        output = new DataOutput(outputName);
    }

    /**
     * Wczytuje graf, przetwarza go i zapisuje wynik algorytmu BFS
     *
     * Wynik zawiera indeksy węzłów, po jednym w każdym wierszu, w kolejności
     * ich odwiedzenia przez BFS. Dzięki temu kolejność w pliku wyjściowym
     * odpowiada kolejności z listy zwróconej przez procesor
     *
     * Rozdzielenie odczytu, przetwarzania i zapisu zapobiega błędom
     * spójności, w których wynik był tworzony z innej macierzy niż ta
     * przekazana do BFS albo zapisywany w innym formacie niż oczekiwany
     */
    public void run() {
        // loads input data
        try {
            input.load();
        } catch (Exception e) {
            System.err.println("Wrong input (see trace)!");
            e.printStackTrace();
            return;
        }
        // process
        List<Integer> result = bFSProcessor.process(input.getMatrix());

        // write results
        try {
            output.save(result);
        } catch (Exception e) {
            System.err.println("Wrong output (see trace)!");
            e.printStackTrace();
        }
    }


    /**
     * Uruchamia aplikację
     *
     * @param args źródło danych wejściowych oraz opcjonalnie miejsce zapisu wyniku
     */
    public static void main(String[] args) {
        String inputName = null;
        String outputName = "console";
        if (args.length == 0) {
            System.err
                    .println("Run with parameters: input <output>"
                            + " where input/output are names of files or console for standard in/out"
                            + "\nOperation - first line of the input (+,-,*,/,sort,mean,primals)");
            System.exit(-1);
        } else {
            inputName = args[0];
            // second parameter is optional
            if (args.length > 1) {
                outputName = args[1];
            }
        }
        final Controller controler = new Controller(inputName,
                outputName);
        controler.run();
    }

}
