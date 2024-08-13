import java.io.*;

class Main {
    static char[][] paper;
    static int numOfWhite = 0;
    static int numOfBlue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        paper = new char[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                paper[i][j] = input[j].charAt(0);
            }
        }
        makeColorPaper(0, 0, N);
        StringBuilder sb = new StringBuilder();
        sb.append(numOfWhite).append("\n").append(numOfBlue);
        System.out.println(sb.toString());
    }

    private static void makeColorPaper(int startRow, int startCol, int width) {
        for (int i = startRow; i - startRow < width; i++) {
            for (int j = startCol; j - startCol < width; j++) {
                if (paper[i][j] != paper[startRow][startCol]) {
                    makeColorPaper(startRow, startCol, width >> 1);
                    makeColorPaper(startRow, startCol + (width >> 1), width >> 1);
                    makeColorPaper(startRow + (width >> 1), startCol, width >> 1);
                    makeColorPaper(startRow + (width >> 1), startCol + (width >> 1), width >> 1);
                    return;
                }
            }
        }

        if (paper[startRow][startCol] == '0')   numOfWhite++;
        else    numOfBlue++;
    }
}