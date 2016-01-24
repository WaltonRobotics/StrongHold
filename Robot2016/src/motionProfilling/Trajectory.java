package motionProfilling;

public class Trajectory extends java.util.ArrayList<Position>{
	private double time = -1;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double getDeltaTime()
	{
		
	if(time == -1)
	{
		time+=1;
		for(int i=0; i<size(); i++)
		{
			time+=get(i).getDeltaTime();
		}
	}

	
	return time;
	}
	
}
