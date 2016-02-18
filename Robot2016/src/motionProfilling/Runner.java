package motionProfilling;

public class Runner {

	public static void main(String[] args) {
		MotionControl mc = new MotionControl("0,0,0:1,1,90",0,0);
		for(double i=0; i<mc.getMaxTime(); i+=.05)
		{
			Position pos = mc.getPosition(i);
			if(pos!=null)
			{
				
			System.out.println("total time"+i);
//			System.out.println("dis left"+pos.totalDistanceLeft);
//			System.out.println("dis right"+pos.totalDistanceRight);
			//System.out.println("v left"+pos.getVelocityLeft()+" v right"+pos.getVelocityRight());
			System.out.println("vl"+mc.velocityLeft(i));
			System.out.println("vr"+mc.velocityLeft(i));
			System.out.println("dl"+mc.distanceleft(i));
			System.out.println("dr"+mc.distanceRight(i));
			}
			else
				break;
		}
	}

}
