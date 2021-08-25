package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.poi.ss.usermodel.DateUtil;



public class Report{
	

	//static LinkedHashMap <String, TestCaseElement>testcases = new LinkedHashMap <String, TestCaseElement>();
	static LinkedHashMap <Integer, StepElement>testcases_steps = new LinkedHashMap <Integer, StepElement>();
	
	public static int step = 0;
	
	
	public static synchronized void log(String message, Status status) {
		boolean takeScreenShot = false;
		String screenShot = null, reportStatus = null;
		String screenShotPath=null;
		//TestCaseElement tcInfo = getTcInfo();
		step++;

		switch(status){
		case BUSINESSSTEP: takeScreenShot = false; reportStatus = "BUSINESSSTEP";break;
		case Pass:  takeScreenShot = true; reportStatus = "Pass"; break;
		case PASS:  takeScreenShot = false; reportStatus = "Pass"; break;
		case FAIL:  takeScreenShot = true; reportStatus = "Fail"; break;
		case Fail:  takeScreenShot = true; reportStatus = "Fail"; break;
		case DONE: takeScreenShot = false; reportStatus = "Done"; break;
		case WARN:  takeScreenShot = true; reportStatus = "Warn"; break;
		case DEBUG: break;
		}



		StepElement stp = new StepElement();

//		if(tcInfo.rStepCounter==0) {
//			stp.stepid = String.valueOf(tcInfo.bStepCounter);
//		} else {
//			stp.stepid = tcInfo.bStepCounter + "." + tcInfo.rStepCounter;
//		}
		stp.stepId = step;
		stp.description = message;
		
		stp.status = reportStatus;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		stp.time = sdf.format(cal.getTime());

		//getTcSteps().add(stp);
		testcases_steps.put(step,stp);
	}
	
//	private static List<StepElement> getTcSteps() {
//		String key = Thread.currentThread().getName();
//
//		if(!testcases_steps.containsKey(key)) {
//			testcases_steps.put(key, new ArrayList<StepElement>());
//		}
//
//		return testcases_steps.get(key);
//	}
	
	public void generateHTMLReport(String TestcaseName) throws IOException{

		PrintWriter out = new PrintWriter(new FileWriter("Reports//" +TestcaseName+".html"));
		out.println("<html><body><p>First Report" + TestcaseName + " </p>");
		
//		testcases_steps.keySet().forEach((Key)->{
//			out.println("<p>" + testcases_steps.get(Key) + " </p>");
//		});
		
		for(Map.Entry entry:testcases_steps.entrySet()){
			out.println("<p>" +((StepElement)entry.getValue()).stepId + "	"
					 + ((StepElement)entry.getValue()).description + "	"
							 + ((StepElement)entry.getValue()).status + "	"
					 + ((StepElement)entry.getValue()).time + "</p>");
		}
		out.println("</html></body>");
		out.close();
	}
	
	
//	@SuppressWarnings("rawtypes")
//	public static void writeToHTML(Class cls, Object obj, String htmlFile, String xslFile) {
//		try {
//			TransformerFactory tf = TransformerFactory.newInstance();
//			StreamSource xslt = new StreamSource(xslFile);	//xslFile = "report/tc.xsl"
//			Transformer transformer = tf.newTransformer(xslt);
//
//			JAXBContext ctx = JAXBContext.newInstance(cls);
//			JAXBSource source = new JAXBSource(ctx, obj);
//
//			PrintStream p = new PrintStream(new FileOutputStream(htmlFile));
//			StreamResult result = new StreamResult(p);
//
//			transformer.transform(source, result);
//			p.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}