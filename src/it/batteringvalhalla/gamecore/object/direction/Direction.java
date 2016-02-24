package it.batteringvalhalla.gamecore.object.direction;

public enum Direction {
	nord, sud, est, ovest, stop;

	public static Direction fromInt(int i) {
		switch (i) {
		case 1:
			return Direction.nord;

		case 2:
			return Direction.sud;

		case 3:
			return Direction.est;

		case 4:
			return Direction.ovest;

		default:
			return Direction.stop;

		}
	}

	public static int toInt(Direction dir) {
		switch (dir) {
		case nord:
			return 1;

		case sud:
			return 2;

		case est:
			return 3;

		case ovest:
			return 4;

		default:
			return 0;

		}
	}
}
