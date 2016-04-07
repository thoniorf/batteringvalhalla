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
	private static final long serialVersionUID = -9187125124331413775L;
	protected static final int max_velocity = 4;
	protected static final int charge_velocity = 10;
	protected static final long charge_countdown = 5000;
	protected static int curret_max_velocity = max_velocity;

	protected Vector2D velocity;
	protected Direction face_dir;
	protected Direction move_dir;
	protected Boolean can_charge;
	protected Boolean charge;
	protected long charge_time;
	protected AbstractIA strategy;
	// sprite images
	protected Sprite head, body, arm, mount;
	// friction time
	protected long friction_time;

	public static int getMaxVelocity() {
		return curret_max_velocity;
	}

	public AbstractActor(Point origin, int idHead, int idBody, int idMount) {
		super(origin);
		this.width = 112;
		this.height = 50;
		((Rectangle) shape).setBounds(origin.x - width / 2, origin.y - height / 2, width, height);
		this.velocity = new Vector2D(0, 0);
		curret_max_velocity = max_velocity;
		this.move_dir = Direction.stop;
		this.face_dir = Direction.est;
		this.charge_time = 0L;
		this.can_charge = Boolean.TRUE;
		this.charge = Boolean.FALSE;
		this.strategy = null;
		// sprite
		// w=117 h=122
		this.body = new Sprite(ResourcesLoader.actor_body.get(idBody), 117, 122, 3, 1, 0, 0, -45);
		// w=117 h=122
		this.arm = new Sprite(ResourcesLoader.actor_body.get(idBody + 1), 117, 122, 3, 1, 0, 0, -45);
		// w=117 h= 88
		this.mount = new Sprite(ResourcesLoader.actor_mount.get(idMount), 117, 88, 3, 16, 0, 0, -10);
		this.head = new Sprite(ResourcesLoader.actor_head.get(idHead), 117, 122, 3, 1, 0, 0, -45);
		// friction
		friction_time = 0L;
	}

	public Boolean canCharge() {
		return can_charge;
	}

	public void charge() {
		if (can_charge) {
			can_charge = Boolean.FALSE;
			charge = Boolean.TRUE;
			charge_time = System.currentTimeMillis();
			curret_max_velocity = charge_velocity;
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
		// face direction
		face_dir = (!Direction.stop.equals(move_dir)) ? move_dir : face_dir;

		// Charge
		if (charge) {
			if (velocity.getComponents().x == charge_velocity || velocity.getComponents().y == charge_velocity) {
				charge = Boolean.FALSE;
				curret_max_velocity = max_velocity;
			}
			switch (face_dir) {
			case est:
				incVelX(1);
				break;
			case nord:
				incVelY(-1);
				break;
			case ovest:
				incVelX(-1);
				break;
			case sud:
				incVelY(1);
				break;
			default:
				break;

			}
		}
		// Movement
		switch (move_dir) {
		case est:
			incVelX(1);
			break;
		case nord:
			incVelY(-1);
			break;
		case ovest:
			incVelX(-1);
			break;
		case sud:
			incVelY(1);
			break;
		case stop:
			// friction
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
		// sprite updates
		arm.update(move_dir);
		head.update(move_dir);
		body.update(move_dir);
		mount.update(move_dir);
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(mount.getFrame(), shape.getBounds().x, shape.getBounds().y + mount.getOffsetY(), null);
		g.drawImage(body.getFrame(), shape.getBounds().x, shape.getBounds().y + body.getOffsetY(), null);
		g.drawImage(head.getFrame(), shape.getBounds().x, shape.getBounds().y + body.getOffsetY(), null);
		g.drawImage(arm.getFrame(), shape.getBounds().x, shape.getBounds().y + body.getOffsetY(), null);
	}
}
