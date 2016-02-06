package motionProfilling;

public class Runner {

	public static void main(String[] args) {
		MotionControl mc = new MotionControl("0,0,0:5,5,90",0,0);
		for(double i=0; i<5.6; i+=.05)
		{
			Position pos = mc.getPosition(i);
			if(pos!=null)
			{
				
			System.out.println("total time"+pos.totalTime);
			System.out.println("dis left"+pos.totalDistanceLeft);
			System.out.println("dis right"+pos.totalDistanceRight);
			System.out.println("v left"+pos.getVelocityLeft()+" v right"+pos.getVelocityRight());
			}
			else
				break;
		}
	}

}
