package algorithms.graphs;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chaitra.kr on 17/11/18.
 */
public class FillColor {
    static int ROW_MAX = 0;
    static int COL_MAX = 0;
    static int MIN = 0;

    public static void main(String[] args) {
        int[][] image = new int[][]{{0, 0, 0}, {0, 0, 0}};
        int sr = 0;
        int sc = 0;
        int newColor = 2;
        ROW_MAX = image.length - 1;
        COL_MAX = image[0].length-1;
        int[][] res = floodFill(image, sr, sc, newColor);
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        Cordinate start = new Cordinate(sr, sc);
        HashSet<Cordinate> neighbours = new HashSet<>();
        int curColor = image[sr][sc];
        Set<Cordinate> visited = new HashSet<>();
        visited.add(start);
        Set<Cordinate> connectedPoints = getConnectedPoints(image, start, curColor, neighbours, visited);
        int[][] newImage = fillColor(connectedPoints, image, newColor);
        return image;
    }

    private static Set<Cordinate> getConnectedPoints(int[][] image, Cordinate start, int curColor, Set<Cordinate> neighbours, Set<Cordinate> visited) {
        if (start.getX() == 0) {
            Cordinate n1 = new Cordinate(start.getX() + 1, start.getY());
            if (!visited.contains(n1) && image[n1.getX()][n1.getY()] == curColor) {
                neighbours.add(n1);
                visited.add(n1);
                neighbours = getConnectedPoints(image, n1, curColor, neighbours, visited);
            }
        } else if (start.getX() > MIN && start.getX() < ROW_MAX) {
            Cordinate n1 = new Cordinate(start.getX() + 1, start.getY());
            Cordinate n2 = new Cordinate(start.getX() - 1, start.getY());
            if (!visited.contains(n1) && image[n1.getX()][n1.getY()] == curColor) {
                neighbours.add(n1);
                visited.add(n1);
                neighbours = getConnectedPoints(image, n1, curColor, neighbours, visited);
            }
            if (!visited.contains(n2) && image[n2.getX()][n2.getY()] == curColor) {
                neighbours.add(n2);
                visited.add(n2);
                neighbours = getConnectedPoints(image, n2, curColor, neighbours, visited);
            }
        } else if (start.getX() == ROW_MAX) {  //= MAX
            Cordinate n1 = new Cordinate(start.getX() - 1, start.getY());
            if (!visited.contains(n1) && image[n1.getX()][n1.getY()] == curColor) {
                neighbours.add(n1);
                visited.add(n1);
                neighbours = getConnectedPoints(image, n1, curColor, neighbours, visited);
            }
        }

        if (start.getY() == 0) {
            Cordinate n3 = new Cordinate(start.getX(), start.getY() + 1);
            if (!visited.contains(n3) && image[n3.getX()][n3.getY()] == curColor) {
                neighbours.add(n3);
                visited.add(n3);
                neighbours = getConnectedPoints(image, n3, curColor, neighbours, visited);
            }
        } else if (start.getY() > MIN && start.getY() < COL_MAX) {
            Cordinate n3 = new Cordinate(start.getX(), start.getY() + 1);
            Cordinate n4 = new Cordinate(start.getX(), start.getY() - 1);
            if (!visited.contains(n3) && image[n3.getX()][n3.getY()] == curColor) {
                neighbours.add(n3);
                visited.add(n3);
                neighbours = getConnectedPoints(image, n3, curColor, neighbours, visited);
            }
            if (!visited.contains(n4) && image[n4.getX()][n4.getY()] == curColor) {
                neighbours.add(n4);
                visited.add(n4);
                neighbours = getConnectedPoints(image, n4, curColor, neighbours, visited);
            }
        } else if (start.getY() == COL_MAX) {
            Cordinate n3 = new Cordinate(start.getX(), start.getY() - 1);
            if (!visited.contains(n3) && image[n3.getX()][n3.getY()] == curColor) {
                neighbours.add(n3);
                visited.add(n3);
                neighbours = getConnectedPoints(image, n3, curColor, neighbours, visited);
            }
        }

        return neighbours;
    }

    public static int[][] fillColor(Set<Cordinate> connectedPoints, int[][] image, int newColor) {
        for (Cordinate cordinate : connectedPoints) {
            image[cordinate.getX()][cordinate.getY()] = newColor;
        }
        return image;
    }


    static class Cordinate {
        int x;
        int y;

        public Cordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cordinate cordinate = (Cordinate) o;

            if (x != cordinate.x) return false;
            if (y != cordinate.y) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
