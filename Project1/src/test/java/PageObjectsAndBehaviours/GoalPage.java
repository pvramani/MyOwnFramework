package PageObjectsAndBehaviours;

import Utilities.TestCase;
import Utilities.Status;
import Utilities.Report;

public class GoalPage extends TestCase {
	
	
	public void Login() {
		Report.log("Step 1: logginng In", Status.BUSINESSSTEP);
		Report.log("logginng In", Status.PASS);
		Report.log("Step 3: logginng In", Status.BUSINESSSTEP);
		Report.log("Step 4: logginng In", Status.BUSINESSSTEP);
		System.out.println("In Login Method"+super.TestData.get("UserName"));
		
	}
	public void Navigate() {
		System.out.println("In Navigate Method");
	}

}
