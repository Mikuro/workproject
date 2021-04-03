package com.company;

import java.util.*;

public class Astar {
    public static Vector<Table.Cell> search(Table.Cell beginCell, Table.Cell endCell, Table table) {
        if (beginCell.isWall() || endCell.isWall()) {
            return new Vector<Table.Cell>();
        }
        Vector<Table.Cell> path = new Vector<Table.Cell>();
        Map<Table.Cell, Boolean> closedSet = new HashMap<Table.Cell, Boolean>();
        Comparator<Table.Cell> comparator = new CellComparator();
        PriorityQueue<Table.Cell> openSet = new PriorityQueue<Table.Cell>(10, comparator);

        beginCell.g = 0;
        beginCell.h = heuristicCost(beginCell, endCell);
        beginCell.f = beginCell.g + beginCell.h;

        openSet.add(beginCell);
        while(!openSet.isEmpty()){
            Table.Cell temp = openSet.remove();

            if(temp == endCell){
                return reconstructPath(beginCell, endCell);
            }

            closedSet.put(temp, true);
            for(Table.Cell i : temp.getNeighbours()){
                if(!i.isWall()){
                    if(closedSet.containsKey(i)){
                        continue;
                    }
                    int gScore = temp.g + 1;//correct diagonal cells
                    boolean gBetter = false;
                    if(!openSet.contains(i)){
                        openSet.add(i);
                        gBetter = true;
                    }else{
                        if(gScore < i.g){
                            gBetter = true;
                        }
                    }
                    if(gBetter){
                        i.cameFrom = temp;
                        i.wasSeen = true;
                        i.g = gScore;
                        i.h = heuristicCost(i, endCell);
                        i.f = i.g + i.h;
                    }

                }
            }
        }
        return new Vector<Table.Cell>();
    }

    private static Vector<Table.Cell> reconstructPath(Table.Cell beginCell, Table.Cell endCell) {
        Vector<Table.Cell> path = new Vector<Table.Cell>();
        Table.Cell currentCell = endCell;
        while(currentCell != null){
            path.add(currentCell);
            currentCell.setPath(true);
            currentCell = currentCell.cameFrom;
        }
        return path;
    }


    private static int heuristicCost(Table.Cell a, Table.Cell b) {
        return Math.abs((b.getX() * b.getX() + b.getY() * b.getY()) - (a.getX() * a.getX() + a.getY() * a.getY()));
    }



    public static class CellComparator implements Comparator<Table.Cell> {
        @Override
        public int compare(Table.Cell o1, Table.Cell o2) {
            return Integer.compare(o2.f, o1.f);
        }
    }

}
