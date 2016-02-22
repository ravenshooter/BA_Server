
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


import com.rapidminer.Process;
import com.rapidminer.RapidMiner;
import com.rapidminer.RapidMiner.ExecutionMode;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.IOContainer;
import com.rapidminer.operator.IOObject;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.operator.nio.file.FileObject;
import com.rapidminer.operator.nio.file.SimpleFileObject;
import com.rapidminer.tools.XMLException;


public class RapidMinerTest2 {


	
	public RapidMinerTest2() {
		
		RapidMiner.setExecutionMode(ExecutionMode.COMMAND_LINE);
		RapidMiner.init();
		
	}
	
	
	public String classify(String csvFile) throws OperatorException{
		Process myProcess;
		try {
			myProcess = new Process(new File("C:\\operationProcess.rmp"));
		


			FileObject file = new SimpleFileObject(new File(csvFile));
			IOContainer ioInput = new IOContainer(file);
			// just use myProcess.run() if you don't use the input ports for your process
			IOContainer ioResult = myProcess.run(ioInput);
	
			// use the result(s) as needed, for example if your process just returns one ExampleSet, use this:
			if (ioResult.getElementAt(0) instanceof ExampleSet) {
			    ExampleSet resultSet = (ExampleSet)ioResult.getElementAt(0);
			    for(int i = 0 ; i < resultSet.size(); i++){
			    	double predResult = resultSet.getExampleTable().getDataRow(i).get(resultSet.getAttributes().getPredictedLabel());
			    	System.out.println("Row: " + resultSet.getAttributes().getPredictedLabel().getAsString(predResult, 0, true));
			    	return resultSet.getAttributes().getPredictedLabel().getAsString(predResult, 0, true);
			    }
			}	
			
			
			
			
		
		} catch (IOException | XMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		
		return "";
	}
	
	
	
	public static void calcZwischenwert(){
		List<SensorMesswert> values = new LinkedList<SensorMesswert>();
		values.add(new SensorMesswert(0, 0, 0, "lkab", 0));
		values.add(new SensorMesswert(1, 0, 0, "lkab", 0));
		
		int nValues = 3;
		
		double nStuetzpunkte= values.size();
		double nZielwerte = nValues;
		double[] zielInStuetze = new double[nValues];
		
		double fac = nStuetzpunkte/nZielwerte;
		
		for(int i = 0; i < nZielwerte;i++){
			zielInStuetze[i] = ((double)i+1)*nStuetzpunkte/nZielwerte;
			System.out.println("zielinst�tze:"+(i+1)+" "+zielInStuetze[i]);
		}
		
		for(int i = 0; i < nZielwerte-1;i++){
			double x1 = (int)zielInStuetze[i];
			double x2 = x1+1;
			double y1 = values.get((int)x1).x;
			double y2 = values.get((int)x2).x;
			double m = (y2-y1)/(x2-x1);
			double yTarget = m*(zielInStuetze[i]-x1)+y1;
			System.out.println("yTarget"+yTarget);
		}
		
		
	}
	
	
	public static void main(String[] args){
		calcZwischenwert();
		
		//new RapidMinerTest().classify("C:\\Users\\Steve\\Documents\\Eclipse Projects\\testapplication\\data\\sample.csv");
	}
}