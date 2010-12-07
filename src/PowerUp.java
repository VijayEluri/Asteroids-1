/**
 * Author: Chris Lundquist
 * Description: Framework for "PowerUp" like functionality. I.E. You run into the
 *              PowerUp and you get a special ability
 *              
 *              By inheriting from PowerUp and implementing the abstract methods
 *              I.E. The ones that handle collision with different actor types
 *              it is easy to create lots of functionality
 */
abstract public class PowerUp extends Actor {
	private static final long serialVersionUID = -6702121556971776417L;
	private static final float POWERUP_SIZE = 0.1f;

	PowerUp(Vector pos){
		position = pos;
		init();
	}
	
	PowerUp(float x, float y){
		this(new Vector(x, y));
	}
	
	private void init() {
		size = PowerUp.POWERUP_SIZE;
		theta = 0;
		omega = 0;
		velocity = new Vector();
		sprite = Sprite.powerUp();
		id = generateId();
	}
	// What Happens when we hit with the Player
	abstract void applyTo(PlayerShip player);
	// What Happens when we hit with an Bandit
	abstract void applyTo(Bandit bandit);
	// What Happens when we hit with an Asteroid
	abstract void applyTo(Asteroid asteroid);
	// What Happens when we hit a Bullet
	abstract void applyTo(Bullet bullet);
	// Fall back
	abstract void applyTo(Actor actor);

	
	// This method figures out which method to call in the
	// derived classes.
	@Override
	public void handleCollision(Actor other) {
		if (other instanceof PlayerShip) {
			applyTo((PlayerShip) other);
		} else if (other instanceof Bandit) {
			applyTo((Bandit) other);
		} else if (other instanceof Asteroid) {
			applyTo((Asteroid) other);
		} else if (other instanceof Bullet) {
			applyTo((Bullet) other);
		} else /* Actor */{
			System.err.println("DEBUG:Poorly Handled powerup applied to Actor class");
			applyTo(other);
		}
		if(SoundEffect.isEnabled())
			SoundEffect.forPowerUp().play();
	}

	public static void spawn() {
		switch(gen.nextInt(4)) {
		case(0):
			Actor.actors.add(new LifePowerUp(randomPosition()));
			break;
		default:
			Actor.actors.add(new TripleShotPowerUp(randomPosition()));
			
		}	
	}
}

