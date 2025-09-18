import java.util.*;

public class Main {

    // 打印棋盘
    // 棋盘采用一维数组 idx = r * cols + c
    static String repeat(String plus, int cols, int formWidth){
        String result = "";
        for(int i = 0; i < cols; i++){
            result = result + plus;
            for(int j = 0; j < formWidth; j++){
                result = result + '-';
            }
        }
        return result;
    }

    static void printBoard(int[] board, int rows, int cols){
        int max = rows * cols - 1;
        int width = String.valueOf(max).length(); //取max的最大位数，为了保证打印出的格子不歪
        String line = repeat("+", cols, width + 1) + "+";
        for(int r = 0; r < rows; r++){
            System.out.println(line);
            for(int c = 0; c < cols; c++){
                int v = board[r * cols + c];
                String cell = (v == 0 ? " " : String.valueOf(v)); //打印当前格子中的内容
                System.out.printf("|%" + width + "s" , cell);
            }
            System.out.println("|");
        }
        System.out.println(line);
    }

    // 移动规则：输入一个数字，如果他与空格相邻，则与空格交换
    static boolean move(int[] board, int rows, int cols, int num){
        int idx = -1;
        int empty = -1;
        for(int i = 0; i < board.length; i++){
            if(board[i] == num) idx = i;
            if(board[i] == 0) empty = i;
        }
        if(idx == -1) return false;

        int r1 = idx / cols, c1 = idx % cols;
        int r2 = empty / cols, c2 = empty % cols;
        if(Math.abs(r1 - r2) + Math.abs(c1 - c2) == 1){ //说明此时目标格与空格相邻
            board[empty] = board[idx];
            board[idx] = 0;
            return true;
        }
        return false;
    }

    // 胜利判定（是否已解）
    static boolean isSolved(int[] board) {
        for (int i = 0; i < board.length - 1; i++) {
            if (board[i] != i + 1) return false;
        }
        return board[board.length - 1] == 0;
    }


    // 随机打乱，直到生成一个可解的局面
    /*
        如何保证随机生成的棋盘是可解的？
            v(反序数,注意非零)       k(空格自底行号)
            1. 当cols为奇数，inv为偶数 = 可解
            2. 当cols为偶数，(inv + k)为奇数 = 可解
     */
    static boolean isSolvable(int[] board, int rows, int cols){
        int inv = 0;
        for(int i = 0; i < board.length; i++){
            if(board[i] == 0) continue;
            for(int j = i + 1; j < board.length; j++){
                if(board[j] == 0) continue;
                if(board[i] > board[j]) inv++;
            }
        }
        // 计算空格从最底下一行数，在第几行（最底层为1）
        int k = rows - (indexOf(board, 0) / cols);
        if(cols % 2 != 0 ){
            return inv % 2 == 0;
        }else{
            return (inv + k) % 2 == 1;
        }
    }

    static void shuffle(int[] board, int rows, int cols){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            list.add(i);
        }
        do{
            Collections.shuffle(list);
            for(int i = 0; i < board.length; i++){
                board[i] = list.get(i);
            }
        } while (!isSolvable(board, rows, cols) || isSolved(board));
    }

    static int indexOf(int[] arr, int index){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == index) return i;
        }
        return -1;
    }

    // 主函数，运行游戏
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Welcome to Sliding Puzzle Game ===");
        System.out.println("Enter number of rows : ");
        int rows = input.nextInt();
        input.nextLine();
        System.out.println("Enter number of cols: ");
        int cols = input.nextInt();
        input.nextLine();
        int[] board = new int[rows * cols];
        shuffle(board, rows, cols);
        while(true){
            printBoard(board, rows, cols);
            if(isSolved(board)) {
                System.out.println("Congratulations !!!");
                break;
            }
            System.out.println("Please choose to continue or quit (c=continue, q=quit)");
            String choice = input.nextLine().trim();
            if(choice.equals("q")) break;
            if(choice.equals("c")) {
                shuffle(board, rows, cols);
                continue;
            }

            try{
                int num = Integer.parseInt(choice);
                if(!move(board, rows, cols, num)) {
                    System.out.println("Invalid input. Try again");
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid input. Try again");
            }
        }
        System.out.println("Goodbye!");
    }

}