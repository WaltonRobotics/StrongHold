package org.usfirst.frc.team2974.dataLogs;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.usfirst.frc.team2974.robot.Robot;

public class DataLog {
	 public static void printFields() throws Exception {
	        String path = new File(DataLog.class.getName()).getAbsolutePath();
	        path = path.substring(0,path.lastIndexOf(DataLog.class.getPackage().getName()));

	        System.out.println(path);

	        File dir = new File(path);

	        for(File d: dir.listFiles())
	            for (File dt: d.listFiles())
	                System.out.println(dt.getName());
	    }
    
    private static ArrayList<Method> methodFinder(CharSequence keyword, Object... objects)
    {
        ArrayList<Method> methods = new ArrayList<>();
        
        for(Object obj: objects)
            for(Method method: obj.getClass().getDeclaredMethods())
                if(method.getName().contains(keyword))
                    methods.add(method);
        
        return methods;
    }
    
    public static void main(String args[])
    {
    	try {
			printFields();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}