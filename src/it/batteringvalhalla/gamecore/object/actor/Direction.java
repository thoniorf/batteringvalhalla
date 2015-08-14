package it.batteringvalhalla.gamecore.object.actor;

public enum Direction {
	nord, sud, est, ovest, stop, sudOvest, sudEst, nordEst, nordOvest;

	public static Direction fromInt(int value) {
		switch (value) {
		case 1:
			return nord;
		case 2:
			return sud;
		case 3:
			return est;
		case 4:
			return ovest;
		default:
			break;
		}
		return stop;
	}

}
