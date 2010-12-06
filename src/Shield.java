import java.io.Serializable;


public class Shield implements Serializable {
	private static final long serialVersionUID = 3737736996028582652L;
	public static final float SHIELD_SIZE = 0.15f;
	private static final float MAX_CAPACITY = 1.0f;
	private static final float REGEN_RATE = 0.0001f;
	private float strength; // The current strength of the shield
	private transient Sprite sprite;
	private Actor owner;
	
	public Shield(Actor owner){
		strength = MAX_CAPACITY;
		sprite = Sprite.shield();
		this.owner = owner;
	}
	
	public void update(){
		// Don't regenerate if its full
		if(strength <= MAX_CAPACITY)
			strength += REGEN_RATE;
	}
	
	public void handleCollision(Actor other){
		// TODO calculate kinetic energy of our owner against the other
		strength -= 30000.0f * other.getKineticEnergy();
		
		Vector difference = other.getPosition().minus(owner.getPosition());
		difference.scaleBy(0.5f);
		difference.incrementBy(owner.getPosition());
		
		ParticleSystem.addPlasmaParticle(difference);
		//System.err.println("Shield Hit Captain! Down to " + getIntegrity() + "% (" + strength + ")");
		// Don't put a minimum bound on shield
	}
	
	public boolean isDown() {
		return strength < 0.0f;
	}
	
	public boolean isUp(){
		return strength > 0.0f;
	}
	
	public float getSize(){
		return SHIELD_SIZE;
	}
	
	public Sprite getSprite(){
		return sprite;
	}

	public float getIntegrity(){
		if(isUp())
		    return strength / MAX_CAPACITY;
		else
			return 0.0f;
		
	}
}
