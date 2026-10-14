package pl.poznan.put.cs.io.errors.processors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Wykonuje przejście wszerz grafu reprezentowanego przez macierz sąsiedztwa
 */
public class BFSProcessor {

    List<Integer> list = new ArrayList<Integer>();
    Set<Integer> visitedNodes = new HashSet<Integer>();
    Queue<Integer> queue = new LinkedList<Integer>();
    List<Integer> result = new ArrayList<Integer>();
    int[][] matrix;

    /**
     * Uruchamia algorytm BFS dla całego grafu
     *
     * @param matrix macierz sąsiedztwa reprezentująca graf
     * @return wszystkie węzły w kolejności przejścia BFS; po zakończeniu
     *         pierwszej składowej dodawane są węzły z pozostałych składowych
     *
     * Przed rozpoczęciem czyszczony jest stan poprzedniego uruchomienia.
     * Zapewnia to spójność wyniku przy wielokrotnym użyciu tego samego
     * procesora
     */
    public List<Integer> process(int[][] matrix) {
        visitedNodes.clear();
        queue.clear();
        result.clear();
        this.matrix = matrix;
        int beginningNode = getBeginningNode();
        if (beginningNode != -1) {
            search(beginningNode);
        }
        for (int i = 0; i < matrix.length; i++) {
            if (!visitedNodes.contains(i)) {
                search(i);
            }
        }
        return result;
    }

    /**
     * Wykonuje algorytm BFS dla podanego węzła
     *
     * Każdy węzeł jest oznaczany jako odwiedzony dopiero po pobraniu
     * z kolejki, a sąsiedzi są dodawani zgodnie z macierzą. Eliminuje to
     * niespójności polegające na pomijaniu węzłów, wielokrotnym dopisywaniu
     * węzła startowego lub przetwarzaniu już odwiedzonych sąsiadów
     *
     * @param nodeNo indeks węzła, od którego kontynuowane jest przejście
     */
    private void search(int nodeNo) {
        queue.add(nodeNo);
        while (!queue.isEmpty()) {
            Integer currentNode = queue.poll();
            if (visitedNodes.contains(currentNode)) {
                continue;
            }
            visitedNodes.add(currentNode);
            result.add(currentNode);
            queue.addAll(getAllValidNeighbors(currentNode));
        }
    }

    /**
     * Wyszukuje węzeł o najmniejszym indeksie, który ma co najmniej jednego
     * sąsiada
     *
     * @return najmniejszy indeks węzła mającego sąsiada lub {@code -1}, jeśli
     *         graf nie ma krawędzi
     */
    private int getBeginningNode() {
        for (int i = 0; i < matrix.length; i++) {
            if (getAllValidNeighbors(i).size() != 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Zwraca nieodwiedzonych jeszcze sąsiadów węzła
     *
     * @param nodeNo indeks węzła
     * @return indeksy nieodwiedzonych sąsiadów
     */
    private List<Integer> getAllValidNeighbors(int nodeNo) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[nodeNo][i] == 1
                    && !visitedNodes.contains(i)) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    /**
     * Wyszukuje wszystkie węzły, które nie zostały odwiedzone
     *
     * @return indeksy węzłów nieodwiedzonych z początkowej składowej
     *
     * Metoda jest zachowana dla zgodności z wcześniejszą wersją procesora;
     * główny algorytm BFS obsługuje obecnie również węzły izolowane
     * i rozłączne składowe bezpośrednio w {@link #process(int[][])}
     */
    private List<Integer> findNodesWthoutNeighbors() {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < matrix.length; i++) {
            if (!visitedNodes.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }
}
