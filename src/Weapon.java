/*
 * Author: Chris Lundquist
 */
public abstract class Weapon {
	protected Actor owner;
	protected static int shootDelay;
	protected static int banditShootDelay;

	Weapon(Actor owner) {
		this.owner = owner;
	}
	// Places a bullet
	abstract void shoot();
	abstract void shootOnce();
	// Keeps track of our shoot delay counter
	void update() {
		// decrement our shoot delay
		if (shootDelay > 0)
			shootDelay--;
	}
	void banditUpdate(){
		if (banditShootDelay > 0)
			banditShootDelay--;
	}
	static void setShootDelay(int time){
		shootDelay = time;
	}
	public int getBanditShootDelay(){
		return banditShootDelay;
	}
	public int getShootDelay(){
		return shootDelay;
	}
}
