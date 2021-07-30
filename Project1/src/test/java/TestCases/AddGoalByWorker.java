package TestCases;

import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

import PageObjectsAndBehaviours.GoalPage;
public class AddGoalByWorker extends GoalPage {
	
	@Test
	public void Test1() {
		Login();
		assertFalse(false, "Hello Assert");
	}
////	
//	@Test
//	public void Test2() {
//		Navigate();
//	}
	
	
}
