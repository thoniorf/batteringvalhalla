package it.batteringvalhalla.gamecore.object.actor;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import it.batteringvalhalla.gamecore.GameWorld;
import it.batteringvalhalla.gamecore.animation.Sprite;
import it.batteringvalhalla.gamecore.ia.AbstractIA;
import it.batteringvalhalla.gamecore.loader.ResourcesLoader;
import it.batteringvalhalla.gamecore.object.AbstractEntity;
import it.batteringvalhalla.gamecore.object.direction.Direction;
import it.batteringvalhalla.gamecore.vector2d.Vector2D;

public abstract class AbstractActor extends AbstractEntity implements Actor, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -859980732544455845L;
	protected static final int max_velocity = 4;
	protected static final int charge_velocity = 10;
	protected static final long charge_countdown = 5000;
	protected int curret_max_velocity = max_velocity;

	protected Vector2D velocity;
	protected Direction face_dir;
	protected Direction move_dir;
	protected Boolean can_charge;
	protected Boolean charge;
	protected long charge_time;
	protected transient AbstractIA strategy;
	// sprite images
	protected Sprite head, body, arm, mount;
	// friction time
	protected long friction_time;

	public int getMaxVelocity() {
		return curret_max_velocity;
	}

	public AbstractActor(Point origin, int idHead, int idBody, int idMount) {
		super(origin);
		this.width = 110;
		this.height = 50;
		((Rectangle) this.shape).setBounds(origin.x - (this.width / 2), origin.y - (this.height / 2), this.width,
				this.height);
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
		this.body = new Sprite(ResourcesLoader.actor_body.get(idBody), 117, 122, 3, 1, -45);
		// w=117 h=122
		this.arm = new Sprite(ResourcesLoader.actor_body.get(idBody + 1), 117, 122, 3, 1, -45);
		// w=117 h= 88
		this.mount = new Sprite(ResourcesLoader.actor_mount.get(idMount), 117, 88, 3, 16, -10);
		this.head = new Sprite(ResourcesLoader.actor_head.get(idHead), 117, 122, 3, 1, -45);
		// friction
		this.friction_time = 0L;
	}

	public Boolean canCharge() {
		return can_charge;
	}

	public void charge() {
		if (this.can_charge) {
			this.can_charge = Boolean.FALSE;
			this.charge = Boolean.TRUE;
			this.charge_time = System.currentTimeMillis();
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

	public int getCurretMaxVelocity() {
		return curret_max_velocity;
	}

	public void setCurretMaxVelocity(int curret_max_velocity) {
		this.curret_max_velocity = curret_max_velocity;
	}

	@Override
	public Vector2D getVelocity() {
		return velocity;
	}

	private void incVelX(Integer v) {
		if (Math.abs(this.velocity.getComponents().x + v) <= curret_max_velocity) {
			this.velocity.setX(this.velocity.getComponents().x + v);
		} else {
			if ((this.velocity.getComponents().x + v) < 0) {
				this.velocity.setX(curret_max_velocity * -1);
			} else {
				this.velocity.setX(curret_max_velocity);
			}
		}
	}

	private void incVelY(Integer v) {
		if (Math.abs(this.velocity.getComponents().y + v) <= curret_max_velocity) {
			this.velocity.setY(this.velocity.getComponents().y + v);
		} else {
			if ((this.velocity.getComponents().y + v) < 0) {
				this.velocity.setY(curret_max_velocity * -1);
			} else {
				this.velocity.setY(curret_max_velocity);
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
		if (Math.abs(velocity.getX()) <= curret_max_velocity) {
			this.velocity.setX(velocity.getX());
		} else {
			if (velocity.getX() < 0) {
				this.velocity.setX(curret_max_velocity * -1);
			} else {
				this.velocity.setX(curret_max_velocity);
			}

		}

		if (Math.abs(velocity.getY()) <= curret_max_velocity) {
			this.velocity.setY(velocity.getY());
		} else {
			if (velocity.getY() < 0) {
				this.velocity.setY(curret_max_velocity * -1);
			} else {
				this.velocity.setY(curret_max_velocity);
			}

		}
	}

	public long getCharge_time() {
		return charge_time;
	}

	public void setCharge_time(long charge_time) {
		this.charge_time = charge_time;
	}

	public Boolean getCharge() {
		return charge;
	}

	public void setCharge(Boolean charge) {
		this.charge = charge;
		this.can_charge = !charge;
	}

	@Override
	public void update() {
		super.update();
		if (this.strategy != null) {
			this.strategy.update();
		}
		// charge time
		if ((System.currentTimeMillis() - this.charge_time) >= charge_countdown) {
			this.can_charge = Boolean.TRUE;
		}
		// face direction
		this.face_dir = (!Direction.stop.equals(this.move_dir)) ? this.move_dir : this.face_dir;

		// Charge
		if (this.charge) {

			switch (this.face_dir) {
			case est:
				this.incVelX(1);
				break;
			case nord:
				this.incVelY(-1);
				break;
			case ovest:
				this.incVelX(-1);
				break;
			case sud:
				this.incVelY(1);
				break;
			default:
				break;

			}
			if ((Math.abs(velocity.getComponents().x) == charge_velocity)
					|| (Math.abs(velocity.getComponents().y) == charge_velocity)) {
				charge = Boolean.FALSE;
				curret_max_velocity = max_velocity;
			}
		}
		// Movement
		switch (this.move_dir) {
		case est:
			this.incVelX(1);
			break;
		case nord:
			this.incVelY(-1);
			break;
		case ovest:
			this.incVelX(-1);
			break;
		case sud:
			this.incVelY(1);
			break;
		case stop:
			// friction
			long now = System.currentTimeMillis();
			if ((now - this.friction_time) >= GameWorld.getFreq_friction()) {
				this.friction_time = now;
				this.velocity.getComponents().x *= 0.99;
				this.velocity.getComponents().y *= 0.99;
			}
			break;
		default:
			break;

		}

		// move
		this.origin.x += this.velocity.getComponents().x;
		this.origin.y += this.velocity.getComponents().y;
		// set shape bounds
		((Rectangle) this.shape).setBounds(this.origin.x - (this.width / 2), this.origin.y - (this.height / 2),
				this.width, this.height);
		// sprite updates
		this.arm.update(this.move_dir);
		this.head.update(this.move_dir);
		this.body.update(this.move_dir);
		this.mount.update(this.move_dir);

		this.move_dir = Direction.stop;
	}

	@Override
	public void paint(Graphics2D g) {
		g.drawImage(this.mount.getFrame(), this.shape.getBounds().x, this.shape.getBounds().y + this.mount.getOffsetY(),
				null);
		g.drawImage(this.body.getFrame(), this.shape.getBounds().x, this.shape.getBounds().y + this.body.getOffsetY(),
				null);
		g.drawImage(this.head.getFrame(), this.shape.getBounds().x, this.shape.getBounds().y + this.body.getOffsetY(),
				null);
		g.drawImage(this.arm.getFrame(), this.shape.getBounds().x, this.shape.getBounds().y + this.body.getOffsetY(),
				null);
	}

	@Override
	public String toString() {
		return super.toString() + "[velocity=" + velocity + ", face_dir=" + face_dir + ", move_dir=" + move_dir
				+ ", can_charge=" + can_charge + ", charge=" + charge + "]";
	}
}
