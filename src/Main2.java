import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import static javafx.scene.input.KeyCode.Y;

//   Домашнее задание #4. Виталий Тымкив
public class Main2 {
    public static int k = 0;
    public static int n = 0;
    public static int m = 0;
    public static int z = 0;
    public static int [][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println(" Imput digital min :");
//        int x1 = scanner.nextInt();
//        System.out.println(" Imput  digital max:");
//        int y1 = scanner.nextInt();
//        z = x1;
//        homework43(x1, y1);
//        System.out.println("Колво решений рекурсивно " + homework43r(y1));
//        System.out.println(" Imput строку массива :");
//        String s1 = scanner.next();
//        System.out.println(" Imput  строку для поска последовательности :");
//        String s2 = scanner.next();
//        homework41(s1, s2);
        homework42(0,0);


    }
    //    Калькулятор через массив
    public static void homework43(int x1, int y1) {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> arrvar = new ArrayList<>();
        for (int i = 0; i < y1-x1+1; i++) {
            arr.add(i + x1);
            arrvar.add(0);
        }
        int m = 2;
        arrvar.set(0, 1);
        if (arr.get(0)% m == 0) {
            arrvar.set(0, arrvar.get(0)+ 1);
        }
        for (int k = 1; k < arr.size(); k++) {
            arrvar.set(k, arrvar.get(k)+ arrvar.get(k-1));
            if (arr.get(k)% m == 0 && k >= m) {
                int in = arr.get(k)/ m;
                int index = arr.indexOf(in);
                arrvar.set(k, arrvar.get(k)+ arrvar.get(index));
            }else  if(arr.get(k)% m == 0 && k < m )arrvar.set(k, arrvar.get(k)+ arrvar.get(k - 1));
        }
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println("");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arrvar.get(i) + " ");
        }
        System.out.println("");
        System.out.println("Колво решений " + arrvar.get(arrvar.size()-1));
    }
    //    Калькулятор через рекурсию
    public static int homework43r(int y) {
        int f = 0;
        if (y  <  z) f = 1;
        else if (y % 2 == 0) f = homework43r(y-1)+homework43r(y/2);
        else  f =homework43r(y-1);
        return f;
    }

    // Заполнение области массива с рекурсией
    public static  int homework42( int x , int y) {
        int[][] map = new int[7][7];
        int intx = map.length;
        for (int i = 0; i < map.length ; i++) {
            for (int j = 0; j < map.length ; j++) {
                System.out.print(map[i][j]+ " ");
            }
            System.out.println("");
        }
        System.out.println("Колво рекурсов -------   " + n);
        n++;
//        if (x >= 0 && x <=intx && y >=0 && y <= intx) return 0;
        if ( map [ x ][ y ] == 0 && (x >= 0 && x < intx && y >=0 && y <  intx)){
            k ++;
            map [ x ][ y ] = 8;
            homework42 ( x + 2 , y + 1 );
            homework42 ( x + 1 , y + 2);
            homework42 ( x - 1, y + 2 );
            homework42 ( x - 2 , y + 1);
            homework42 ( x + 2, y - 1 );
            homework42 ( x - 2 , y - 1);
            homework42 ( x -1 , y - 2 );
            homework42 ( x + 1 , y -2);
        }else if (k >= 100) return 0;

        return 0;
    }
    //  Наибольшая общая подпоследовательность
    public static  void homework41( String x , String y) {
        char[] arrmap = x.toCharArray();
        char[] arrstr = y.toCharArray();
        int intmap = arrmap.length + 1;
        int intstr = arrstr.length + 1;
        int[][] arr = new int[intstr][intmap];
        for (int i = 1; i <  intstr ; i++) {
            for (int j = 1; j < intmap ; j++) {
                arr[i][j] = arr[i - 1][j];
                if (arrstr[i-1] == arrmap[j-1]) {
                    arr[i][j] = arr[i][j - 1] + 1;
                }else arr[i][j] = Math.max(arr[i][j - 1],arr[i - 1][j] );
            }
        }
        for (int i = 0; i <  intstr ; i++) {
            for (int j = 0; j < intmap ; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static boolean isFree(int x, int y) {
        if (map[x][y] != 0) return false;
        return true;
    }

    public Boolean getPath(int x, int y, ArrayList<Point> path,
                           Hashtable<Point, Boolean> cache){
        Point p = new Point(x, y);
        if (cache.containsKey(p)) { // Мы уже посещали эту ячейку
            return cache.get(p);
        }
        path.add(p);
        if (x == 0 && y == 0) {
            return true;  // Найден путь
        }
        boolean success = false;
        if (x >= 1 && isFree(x - 1, y)) { //Пытаемся идти вправо
            map[x - 1][y] = 8;
            success = getPath(x - 1, y, path, cache); // Свободно! Можно идти вправо
        }
        if (!success && y >= 1 && isFree(x, y - 1)) { // Пытаемся идти вниз
            map[x - 1][y] = 8;
            success = getPath(x, y - 1, path, cache); // Свободно! Можно идти вниз
        }
        if (!success) {
            path.remove(p); //Неверный путь! Прекратить движение этим маршрутом
        }
        cache.put(p, success); // Вычисляем результат
        return success;
    }

//    public Boolean getPath(int x, int y, ArrayList<Point> path,
//                           Hashtable<Point, Boolean> cache){
//        Point p = new Point(x, y);
//        if (cache.containsKey(p)) { // Мы уже посещали эту ячейку
//            return cache.get(p);
//        }
//        path.add(p);
//        if (x == 0 && y == 0) {
//            return true;  // Найден путь
//        }
//        boolean success = false;
//        if (x >= 1 && isFree(x - 1, y)) { //Пытаемся идти вправо
//            map[x - 1][y] = 8;
//            success = getPath(x - 1, y, path, cache); // Свободно! Можно идти вправо
//        }
//        if (!success && y >= 1 && isFree(x, y - 1)) { // Пытаемся идти вниз
//            map[x - 1][y] = 8;
//            success = getPath(x, y - 1, path, cache); // Свободно! Можно идти вниз
//        }
//        if (!success) {
//            path.remove(p); //Неверный путь! Прекратить движение этим маршрутом
//        }
//        cache.put(p, success); // Вычисляем результат
//        return success;
//    }

//    int find_path( int cur_x, int cur_y, int move_num )
//    {
//        desk_state[cur_x][cur_y] = move_num ; // Запомнитьход.
//        if( move_num > max_moves ) return 1; // Проверитьзавершение обхода.
//                // Проверитькаждыйвозможныйход из  текущейклетки.
//        for( int i = 0 ; i < 8 ; i++ )
//        {
//            int next_x = cur_x + possible_moves[i][0] ; // Определитьследующееполе.
//            int next_y = cur_y + possible_moves[i][1] ;
//            if(    move_possible( next_x, next_y )
//                    && find_path( next_x, next_y, move_num+1 )) return 1;
//        }
//        //  Возврат.
//                desk_state[cur_x][cur_y] = 0 ;
//        back_ret++ ;
//        return 0 ;
    public class KnightTour {

        int[][] solution;
        int path = 0;

        public KnightTour(int N) {
            solution = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    solution[i][j] = 0;
                }
            }
        }

        public void solve() {
            if (findPath(0, 0, 0, solution.length)) {
                print();
            } else {
                System.out.println("NO PATH FOUND");
            }
        }

        public boolean findPath(int row, int column, int index, int N) {
            // check if current is not used already
            if (solution[row][column] != 0) {
                return false;
            }
            // mark the current cell is as used
            solution[row][column] = path++;
            // if (index == 50) {
            if (index == N * N - 1) {
                // if we are here means we have solved the problem
                return true;
            }
            // try to solve the rest of the problem recursively

            // go down and right
            if (canMove(row + 2, column + 1, N)
                    && findPath(row + 2, column + 1, index + 1, N)) {
                return true;
            }
            // go right and down
            if (canMove(row + 1, column + 2, N)
                    && findPath(row + 1, column + 2, index + 1, N)) {
                return true;
            }
            // go right and up
            if (canMove(row - 1, column + 2, N)
                    && findPath(row - 1, column + 2, index + 1, N)) {
                return true;
            }
            // go up and right
            if (canMove(row - 2, column + 1, N)
                    && findPath(row - 2, column + 1, index + 1, N)) {
                return true;
            }
            // go up and left
            if (canMove(row - 2, column - 1, N)
                    && findPath(row - 2, column - 1, index + 1, N)) {
                return true;
            }
            // go left and up
            if (canMove(row - 1, column - 2, N)
                    && findPath(row - 1, column - 2, index + 1, N)) {
                return true;
            }
            // go left and down
            if (canMove(row + 1, column - 2, N)
                    && findPath(row + 1, column - 2, index + 1, N)) {
                return true;
            }
            // go down and left
            if (canMove(row + 2, column - 1, N)
                    && findPath(row + 2, column - 1, index + 1, N)) {
                return true;
            }
            // if we are here means nothing has worked , backtrack
            solution[row][column] = 0;
            path--;
            return false;

        }

        public boolean canMove(int row, int col, int N) {
            if (row >= 0 && col >= 0 && row < N && col < N) {
                return true;
            }
            return false;
        }

        public void print() {
            DecimalFormat twodigits = new DecimalFormat("00");
            for (int i = 0; i < solution.length; i++) {
                for (int j = 0; j < solution.length; j++) {
                    System.out.print("   " + twodigits.format(solution[i][j]));
                }
                System.out.println();
            }
        }

//        public static void main(String[] args) {
//            int N = 8;
//            KnightTour i = new KnightTour(N);
//            i.solve();
//        }

    }
}
