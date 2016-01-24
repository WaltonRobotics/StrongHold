package motionProfilling;

import java.util.ArrayList;

public class InputText {
	
	/**
	 * 
	 * format of input:
	 * @param s
	 */
	public static ArrayList<State> parseInput(String s)
	{
		String[] strings = s.split(":");
		ArrayList<State> states = new ArrayList<State>();
		for(String str:strings)
		{
			String[] digits = str.split(",");
			if(digits.length>2)
			states.add(new State(Double.parseDouble(digits[0]),Double.parseDouble(digits[1])));
			else
				states.add(new State(Double.parseDouble(digits[0]),Double.parseDouble(digits[1]),Double.parseDouble(digits[2])));
		}
		return states;
	}
	
}
