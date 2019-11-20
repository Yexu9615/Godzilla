package code;
import java.lang.Math; 

import java.util.*;

class Figure {
	private int x;
	private int y;
	private int type;

	public Figure(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getType() {
		return type;
	}
	public void setType(int t) {
		type=t;
	}

	public void destroyed() {

	}

}

public class Program {
	public static final int G = 'G';
	public static final int N = '.';
	public static final int M = 'M';
	public static final int R = 'R';

	private void testCase(int column, int row, String[] allRows) {
		Figure[][] matrix = deploy(row, column, allRows);

		turnG(matrix);
		turnM(matrix);
	}

	private void turnG(Figure[][] matrix) {
		int rowLength = matrix.length;
		int colLength = matrix[0].length;
		Figure godzilla = null;
		for (int a = 0; a < rowLength; a++) {
			for (int b = 0; b < colLength; b++) {
				if (matrix[a][b].getType() == G) {
					godzilla = matrix[a][b];
					break;
				}

			}
		}
		int x=godzilla.getX();
		int y=godzilla.getY();
		
		if(x-1>=0&&matrix[x-1][y].getType()==R) {//north
			matrix[x][y].setType('.');
			matrix[x-1][y].setType('G');
		}else if(y+1<=colLength-1&&matrix[x][y+1].getType()==R) {//east
			matrix[x][y].setType('.');
			matrix[x][y+1].setType('G');
		}else if(x+1<=rowLength-1&&matrix[x-1][y].getType()==R) {//south
			matrix[x][y].setType('.');
			matrix[x+1][y].setType('G');

		}else if(y-1>=0&&matrix[x][y-1].getType()==R) {//west
			matrix[x][y].setType('.');
			matrix[x][y-1].setType('G');
		}else if(x-1>=0&&matrix[x-1][y].getType()==N) {
			matrix[x][y].setType('.');
			matrix[x-1][y].setType('G');
		}else if(y+1<=colLength-1&&matrix[x][y+1].getType()==N) {//east
			matrix[x][y].setType('.');
			matrix[x][y+1].setType('G');
		}else if(x+1<=rowLength-1&&matrix[x-1][y].getType()==N) {//south
			matrix[x][y].setType('.');
			matrix[x+1][y].setType('G');

		}else if(y-1>=0&&matrix[x][y-1].getType()==N) {//west
			matrix[x][y].setType('.');
			matrix[x][y-1].setType('G');
		}

	}

	private void turnM(Figure[][] matrix) {
		int rowLength = matrix.length;
		int colLength = matrix[0].length;
		Figure godzilla = null;
		for (int a = 0; a < rowLength; a++) {
			for (int b = 0; b < colLength; b++) {
				if (matrix[a][b].getType() == G) {
					godzilla = matrix[a][b];
					break;
				}

			}
		}
		
		ArrayList<Figure> mechs=new ArrayList<Figure>();
		for (int a = 0; a < rowLength; a++) {
			for (int b = 0; b < colLength; b++) {
				if (matrix[a][b].getType() == M) {
					mechs.add(matrix[a][b]);
					
				}

			}
		}
		
		for(int m=godzilla.getX();m>=0;m--) {//up
			if(matrix[m][godzilla.getY()].getType()!=N) {
				System.out.println("protected or died");
				break;
			}
		}
		for(int m=godzilla.getX();m<rowLength;m++) {//down
			if(matrix[m][godzilla.getY()].getType()!=N) {
				System.out.println("protected or died");
				System.out.println("downdowndown");
				break;
			}
		}
		for(int n=godzilla.getY();n<rowLength;n++) {//right
			if(matrix[godzilla.getX()][n].getType()!=N) {
				System.out.println("protected or died");
				break;
			}
		}
		for(int n=godzilla.getY();n>=0;n--) {//left
			if(matrix[godzilla.getX()][n].getType()!=N) {
				System.out.println("protected or died");
				break;
			}
		}
		for(Figure f:mechs) {
			int verticalDistance1=Math.abs(f.getX()+1-godzilla.getX());//down
			int verticalDistance2=Math.abs(f.getX()-1-godzilla.getX());//up

			int horizontalDistance3=Math.abs(f.getY()+1-godzilla.getY());//right
			int horizontalDistance4=Math.abs(f.getY()-1-godzilla.getY());//left
			
			//to be continued
		}
		
	}

	private Figure[][] deploy(int row, int column, String[] allRows) {
		Figure[][] matrix = new Figure[row][column];
		for (int a = 0; a < row; a++) {
			for (int b = 0; b < column; b++) {

				matrix[a][b] = new Figure(a, b, allRows[a].charAt(b));
			}
		}
		return matrix;
	}

	private void run() {
		Scanner scanner = new Scanner(System.in);

		String casesString = scanner.nextLine();
		int cases = Integer.parseInt(casesString);

		for (int a = 0; a < cases; a++) {
			String inputDim = scanner.nextLine();
			String[] inputSplit = inputDim.split(" ");
			int column = Integer.parseInt(inputSplit[0]);
			int row = Integer.parseInt(inputSplit[1]);
			String[] allRows = new String[row];
			for (int b = 0; b < row; b++) {
				String eachRow = scanner.nextLine();
				allRows[b] = eachRow;
			}
			testCase(column, row, allRows);

		}

	}

	public static void main(String[] args) {

		new Program().run();
	}
}
