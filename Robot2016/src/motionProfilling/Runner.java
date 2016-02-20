package motionProfilling;

public class Runner {

	public static void main(String[] args) {
		MotionControl mc = new MotionControl("0,0,0:1,1,90",0,0);
		for(double i=0; i<mc.getMaxTime(); i+=.05)
		{				
			System.out.println("total time"+i);
			System.out.println("vl"+mc.velocityLeft(i));
			System.out.println("vr"+mc.velocityRight(i));
			System.out.println("dl"+mc.distanceleft(i));
			System.out.println("dr"+mc.distanceRight(i));
		}
	}

}
