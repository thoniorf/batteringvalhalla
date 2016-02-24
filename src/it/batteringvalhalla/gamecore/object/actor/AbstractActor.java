package it.batteringvalhalla.gamecore.object.actor;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.animation.Sprite;
import it.batteringvalhalla.gamecore.ia.AbstractIA;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.AbstractEntity;
import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamecore.vector2d.Vector2D;

public abstract class AbstractActor extends AbstractEntity implements Actor {
	protected static final int max_velocity = 4;
	protected static final int charge_velocity = 20;
	protected static final long charge_countdown = 5000;
	protected static int curret_max_velocity = max_velocity;

	public static int getMaxVelocity() {
		return curret_max_velocity;
	}

	protected Vector2D velocity;
	protected Direction face_dir;
	protected Direction move_dir;
	protected Boolean can_charge;
	protected Boolean charge;
	protected long charge_time;
	protected AbstractIA strategy;
	// sprite images
	protected Sprite head, body, mount;
	// friction time
	protected long friction_time;

	public AbstractActor(Point origin, int idHead, int idBody, int idMount) {
		super(origin);
		this.velocity = new Vector2D(0, 0);
		this.move_dir = Direction.stop;
		this.face_dir = Direction.est;
		this.charge_time = 0L;
		this.can_charge = Boolean.TRUE;
		this.charge = Boolean.FALSE;
		this.strategy = null;
		// sprite
		this.body = new Sprite(ResourcesLoader.actor_body, 115, 94, 3, 1, 115 / 2, 115 / 2, 77);
		this.mount = new Sprite(ResourcesLoader.actor_mount.get(idMount), 117, 88, 3, 16, 110 / 2, 110 / 2, 88 / 2);
		this.head = new Sprite(ResourcesLoader.actor_weapon.get(idHead), 103, 76, 1, 16, 36, 66, 48);
		// friction
		friction_time = 0L;
	}

	public Boolean canCharge() {
		return can_charge;
	}

	public void charge() {
		if (can_charge) {
			charge = Boolean.TRUE;
		}
	}

	@Override
	public Direction getFaceDirection() {
		return face_dir;
	}

	@Override
	public Direction getMoveDirection() {
		return move_dir;
	}

	@Override
	public Vector2D getVelocity() {
		return velocity;
	}

	private void incVelX(Integer v) {
		if (Math.abs(velocity.getComponents().x + v) <= curret_max_velocity) {
			velocity.setX(velocity.getComponents().x + v);
		} else {
			if (velocity.getComponents().x + v < 0) {
				velocity.setX(curret_max_velocity * -1);
			} else {
				velocity.setX(curret_max_velocity);
			}
		}
	}

	private void incVelY(Integer v) {
		if (Math.abs(velocity.getComponents().y + v) <= curret_max_velocity) {
			velocity.setY(velocity.getComponents().y + v);
		} else {
			if (velocity.getComponents().y + v < 0) {
				velocity.setY(curret_max_velocity * -1);
			} else {
				velocity.setY(curret_max_velocity);
			}

		}
	}

	@Override
	public void setFaceDirection(Direction face_dir) {
		this.face_dir = face_dir;

	}

	@Override
	public void setMoveDirection(Direction face_dir) {
		this.move_dir = face_dir;

	}

	@Override
	public void setVelocity(Vector2D velocity) {
		if (Math.abs(velocity.getComponents().x) <= curret_max_velocity) {
			this.velocity.setX(velocity.getComponents().x);
		} else {
			if (velocity.getComponents().x < 0) {
				this.velocity.setX(curret_max_velocity * -1);
			} else {
				this.velocity.setX(curret_max_velocity);
			}

		}

		if (Math.abs(velocity.getComponents().y) <= curret_max_velocity) {
			this.velocity.setY(velocity.getComponents().y);
		} else {
			if (velocity.getComponents().y < 0) {
				this.velocity.setY(curret_max_velocity * -1);
			} else {
				this.velocity.setY(curret_max_velocity);
			}

		}
	}

	@Override
	public void update() {
		super.update();
		if (strategy != null) {
			strategy.update();
		}
		// charge time
		if (System.currentTimeMillis() - charge_time >= charge_countdown) {
			can_charge = Boolean.TRUE;
		}
		int addendum = 1;
		// if (charge) {
		// charge = Boolean.FALSE;
		// can_charge = Boolean.FALSE;
		// charge_time = System.currentTimeMillis();
		// curret_max_velocity = charge_velocity;
		// addendum = curret_max_velocity;
		// } else {
		// curret_max_velocity = max_velocity;
		// }
		// Movement
		switch (move_dir) {
		case est:
			incVelX(addendum);
			break;
		case nord:
			incVelY(-1 * addendum);
			break;
		case ovest:
			incVelX(-1 * addendum);
			break;
		case sud:
			incVelY(addendum);
			break;
		case stop:
			long now = System.currentTimeMillis();
			if (now - friction_time >= GameWorld.getFreq_friction()) {
				friction_time = now;
				velocity.getComponents().x *= 0.99;
				velocity.getComponents().y *= 0.99;
			}

			break;
		default:
			break;

		}

		// move
		origin.x += velocity.getComponents().x;
		origin.y += velocity.getComponents().y;
		// set shape bounds
		((Rectangle) shape).setBounds(origin.x - width / 2, origin.y - height / 2, width, height);
		// face direction
		face_dir = move_dir;
		// sprite updates
		head.update(face_dir);
		body.update(face_dir);
		mount.update(face_dir);
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(head.getFrame(), shape.getBounds().x, shape.getBounds().y, null);
		g.drawImage(body.getFrame(), shape.getBounds().x, shape.getBounds().y, null);
		g.drawImage(mount.getFrame(), shape.getBounds().x, shape.getBounds().y, null);

		// debug
		g.draw(shape);
		g.drawString(Boolean.toString(alive), origin.x, origin.y);
		g.drawString(velocity.getComponents().x + " " + velocity.getComponents().y, origin.x, origin.y + 20);
	}
}
