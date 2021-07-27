package PageObjectsAndBehaviours;

import Utilities.TestCase;

public class GoalPage extends TestCase {
	
	
	public void Login() {
		System.out.println("In Login Method"+super.TestData.get("UserName"));
	}
	public void Navigate() {
		System.out.println("In Navigate Method");
	}

}
