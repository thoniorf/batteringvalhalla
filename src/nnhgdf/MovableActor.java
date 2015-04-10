package nnhgdf;
public interface MovableActor {
	/**
	 * <pre>
	 *           1..1     1..1
	 * MovableActor ------------------------> 
	 *           &lt;       direction
	 * </pre>
	 */
	public void setDirection(Direction value);

	public Direction getDirection();

}
