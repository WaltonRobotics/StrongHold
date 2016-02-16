package motionProfilling;

import java.util.ArrayList;

public class InputText {
	
	/**
	 * Converts string input into states for motion control	 * 
	 * @param s input string
	 * 			format of input: "x,y,theta:x,y:x,y...x,y,theta" 
	 * @return the states as outlined in the input string
	 */
	public static ArrayList<State> parseInput(String s)
	{
		String[] strings = s.split(":");
		ArrayList<State> states = new ArrayList<State>();
		
		for(String str:strings) // for each coordinate given by the input string
		{
			String[] digits = str.split(",");
			if(digits.length <= 2)
			states.add(new State(Double.parseDouble(digits[0]),Double.parseDouble(digits[1])));
			else
				states.add(new State(Double.parseDouble(digits[0]),Double.parseDouble(digits[1]),Double.parseDouble(digits[2])));
		}
		return states;
	}
	
}
