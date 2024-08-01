package serialAbelianSandpile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// This class is for the grid for the Abelian Sandpile cellular automaton
public class Grid {
    private final int rows, columns;
    private final int[][] grid; // Current grid
    public final int[][] updateGrid; // Grid for next time step

    public Grid(int w, int h) {
        rows = w + 2; // For the "sink" border
        columns = h + 2; // For the "sink" border
        grid = new int[this.rows][this.columns];
        updateGrid = new int[this.rows][this.columns];
        initializeGrid();
    }

    public Grid(int[][] newGrid) {
        this(newGrid.length, newGrid[0].length); // Call constructor above
        // Don't copy over sink border
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                this.grid[i][j] = newGrid[i - 1][j - 1];
            }
        }
    }

    public Grid(Grid copyGrid) {
        this(copyGrid.rows, copyGrid.columns); // Call constructor above
        // Grid initialization
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.grid[i][j] = copyGrid.get(i, j);
            }
        }
    }

    public int getRows() {
        return rows - 2; // Less the sink
    }

    public int getColumns() {
        return columns - 2; // Less the sink
    }

    public int get(int i, int j) {
        return this.grid[i][j];
    }

    public void setAll(int value) {
        // Borders are always 0
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                grid[i][j] = value;
            }
        }
    }

    public synchronized void nextTimeStep() {
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                this.grid[i][j] = updateGrid[i][j];
            }
        }
    }

    // Key method to calculate the next update grid
    public boolean update() {
        boolean change = false;
        // Do not update border
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                int current = grid[i][j];
                int updated = (current % 4) +
                              (grid[i - 1][j] / 4) +
                              (grid[i + 1][j] / 4) +
                              (grid[i][j - 1] / 4) +
                              (grid[i][j + 1] / 4);
                if (current != updated) {
                    change = true;
                }
                updateGrid[i][j] = updated;
            }
        }
        nextTimeStep();
        return change;
    }

    // Display the grid in text format
    public void printGrid() {
        int i, j;
        // Not border is not printed
        System.out.printf("Grid:\n");
        System.out.printf("+");
        for (j = 1; j < columns - 1; j++) System.out.printf("  --");
        System.out.printf("+\n");
        for (i = 1; i < rows - 1; i++) {
            System.out.printf("|");
            for (j = 1; j < columns - 1; j++) {
                if (grid[i][j] > 0)
                    System.out.printf("%4d", grid[i][j]);
                else
                    System.out.printf("    ");
            }
            System.out.printf("|\n");
        }
        System.out.printf("+");
        for (j = 1; j < columns - 1; j++) System.out.printf("  --");
        System.out.printf("+\n\n");
    }

    // Write grid out as an image
    public void gridToImage(String fileName) throws IOException {
        BufferedImage dstImage =
                new BufferedImage(rows, columns, BufferedImage.TYPE_INT_ARGB);
        // Integer values from 0 to 255.
        int a = 0;
        int g = 0; // Green
        int b = 0; // Blue
        int r = 0; // Red

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                g = 0; // Green
                b = 0; // Blue
                r = 0; // Red

                switch (grid[i][j]) {
                    case 0:
                        break;
                    case 1:
                        g = 255;
                        break;
                    case 2:
                        b = 255;
                        break;
                    case 3:
                        r = 255;
                        break;
                    default:
                        break;
                }
                // Set destination pixel to mean
                // Re-assemble destination pixel.
                int dpixel = (0xff000000)
                        | (a << 24)
                        | (r << 16)
                        | (g << 8)
                        | b;
                dstImage.setRGB(i, j, dpixel); // Write it out
            }
        }

        File dstFile = new File(fileName);
        ImageIO.write(dstImage, "png", dstFile);
    }

    private void initializeGrid() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                grid[i][j] = 0;
                updateGrid[i][j] = 0;
            }
        }
    }
}
