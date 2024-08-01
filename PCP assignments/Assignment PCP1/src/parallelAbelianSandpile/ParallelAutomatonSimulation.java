package parallelAbelianSandpile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import parallelAbelianSandpile.ParallelGrid;

public class ParallelAutomatonSimulation {
    static final boolean DEBUG = false;

    static long startTime = 0;
    static long endTime = 0;

    private static void tick() {
        startTime = System.currentTimeMillis();
    }

    private static void tock() {
        endTime = System.currentTimeMillis();
    }

    public static int[][] readArrayFromCSV(String filePath) {
        int[][] array = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                String[] dimensions = line.split(",");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                System.out.printf("Rows: %d, Columns: %d\n", width, height);

                array = new int[height][width];
                int rowIndex = 0;

                while ((line = br.readLine()) != null && rowIndex < height) {
                    String[] values = line.split(",");
                    for (int colIndex = 0; colIndex < width; colIndex++) {
                        array[rowIndex][colIndex] = Integer.parseInt(values[colIndex]);
                    }
                    rowIndex++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        ParallelGrid simulationGrid;

        if (args.length != 2) {
            System.out.println("Incorrect number of command line arguments provided.");
            System.exit(0);
        }
        String inputFileName = args[0];
        String outputFileName = args[1];

        simulationGrid = new ParallelGrid(readArrayFromCSV(inputFileName));

        int counter = 0;
        tick();
        if (DEBUG) {
            System.out.printf("starting config: %d \n", counter);
            simulationGrid.printGrid();
        }
        while (simulationGrid.update()) {
            if (DEBUG) simulationGrid.printGrid();
            counter++;
        }
        tock();

        System.out.println("Simulation complete, writing image...");
        simulationGrid.gridToImage(outputFileName);
        System.out.printf("\t Rows: %d, Columns: %d\n", simulationGrid.getRows(), simulationGrid.getColumns());
        System.out.printf("Number of steps to stable state: %d \n", counter);
        System.out.printf("Time: %d ms\n", endTime - startTime);
    }
}
