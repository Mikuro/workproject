package com.company;

import java.util.Vector;

public class Table {
    private final int size;
    private final Vector<Vector<Cell>> canvas;

    public Table(int size) {
        this.canvas = new Vector<Vector<Cell>>();
        this.size = size;
        for (int y = 0; y < this.size; y++) {
            Vector<Cell> temp = new Vector<Cell>();
            for (int x = 0; x < size; x++) {
                temp.add(new Cell(x, y));
            }
            this.canvas.add(temp);
        }
    }

    public void printCoord() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.println("X is " + canvas.get(i).get(j).getX() + " Y is " + canvas.get(i).get(j).getY());
            }
        }
    }

    public class Cell {
        private final int x;
        private final int y;
        private boolean isWall;
        private boolean isPath;
        public boolean wasSeen;
        public Cell cameFrom;

        public int g;
        public int h;
        public int f;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
            this.isWall = false;
            this.isPath = false;
            this.wasSeen = false;
            cameFrom = null;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void setWall(boolean flag){
            this.isWall = flag;
        }

        public boolean isWall(){
            return this.isWall;
        }

        public boolean isPath(){
            return this.isPath;
        }

        public void setPath(boolean flag){
            this.isPath = flag;
        }

        public Vector<Cell> getNeighbours(){
            Vector<Cell> neighbours = new Vector<Cell>();
            for(int y = this.y - 1; y <= this.y + 1; y++){
                for(int x = this.x - 1; x <= this.x + 1; x++){
                    if(y >= 0 && y < size && x >= 0 && x < size){
                        neighbours.add(canvas.get(y).get(x));
                    }
                }
            }
            return neighbours;
        }

    }
}
