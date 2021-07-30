package Utilities;

import org.testng.annotations.Test;

import PageObjectsAndBehaviours.GoalPage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;

public class AutoExecuteMethodsFromExcel extends TestCase{
	
	AutoExecuteMethodsFromExcel(){
		
	}
	
		
@Test

public void AddEditGoalByWorkerTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	
	//AtomicReference<String> methodname = new AtomicReference<>();
	String Methodname = null;
	GoalPage gp = new GoalPage(); 
	Method method;
	for(Entry<String, LinkedHashMap<String, String>> entry: super.TestMethods.entrySet()) {
		
		Methodname = entry.getKey().toString();
		method = gp.getClass().getMethod(Methodname, null);
		method.invoke(gp, null);
	}
	
//	super.TestMethods.keySet().forEach((Key) -> {
//	System.out.println("****************"+Key);
//	});
	
	
}

}
