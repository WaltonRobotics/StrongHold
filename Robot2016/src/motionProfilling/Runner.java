package motionProfilling;

public class Runner {

	public static void main(String[] args) {
		MotionControl mc = new MotionControl("0,0,0:5,5,90");
		for(double i=0; i<1; i+=.001)
		{
			System.out.println("v left"+mc.getPosition(i).getVelocityLeft()+" v right"+mc.getPosition(i).getVelocityRight());
		}
	}

}
