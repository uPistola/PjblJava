package Menu;

public class Conversion {
	public static int cellToCoord(int cell) {
		return cell * 32;
	}

	public static int coordToCell(int cord) {
		return cord / 32;
	}
}
