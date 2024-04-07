package com.example.forestfire.service;
import com.example.forestfire.model.Cell;
    import java.util.Random;

    public class FireSimulation {
        private Cell[][] grid;
        private double spreadProbability;
        private Random random;

        public FireSimulation(int height, int width, double spreadProbability) {
            this.grid = new Cell[height][width];
            this.spreadProbability = spreadProbability;
            this.random = new Random();
            initializeGrid();
        }

        private void initializeGrid() {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = new Cell(false);  // Изначально все ячейки не горят
                }
            }
            grid[grid.length/2][grid[0].length/2].setBurning(true);  // Начинаем с центра
        }

        public Cell[][] simulateStep() {
            boolean[][] newBurningStates = new boolean[grid.length][grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j].isBurning()) {
                        newBurningStates[i][j] = false;  // Тушим пожар
                        trySpreadFire(newBurningStates, i - 1, j);
                        trySpreadFire(newBurningStates, i + 1, j);
                        trySpreadFire(newBurningStates, i, j - 1);
                        trySpreadFire(newBurningStates, i, j + 1);
                    }
                }
            }

            // Обновляем состояние горения ячеек
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j].setBurning(newBurningStates[i][j]);
                }
            }

            return grid;
        }

        private void trySpreadFire(boolean[][] newBurningStates, int i, int j) {
            if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
                if (!grid[i][j].isBurning() && random.nextDouble() < spreadProbability) {
                    newBurningStates[i][j] = true;
                }
            }
        }

        // Геттер для сетки
        public Cell[][] getGrid() {
            return grid;
        }
    }

