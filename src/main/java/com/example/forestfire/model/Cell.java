package com.example.forestfire.model;

    public class Cell {
        private boolean burning;

        public Cell(boolean isBurning) {
            this.burning = isBurning;
        }

        public boolean isBurning() {
            return burning;
        }

        public void setBurning(boolean burning) {
            this.burning = burning;
        }
    }

