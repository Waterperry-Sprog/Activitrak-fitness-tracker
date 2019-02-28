package backend;

import java.util.Vector;
import java.io.*;

public class DataHandler {
	
	static Vector<Integer> time = new Vector<Integer>();
	static Vector<Integer> heartRate = new Vector<Integer>();
	
	public DataHandler() {
	}
	
	private void printTables() {
		for (int i = 0; i < time.size(); i++) {
			System.out.println("Time:\t" + time.get(i).toString() +"\tHR:\t"+ heartRate.get(i).toString());
		}
	}
	
	/**
	 * this method parses HR/time data from a Suunto CSV file.
	 * @param file the file to parse
	 */
	public void importDataFromFile(String fileName) {
		
		System.out.println("[DEBUG] importing file "+fileName);
		File data = new File(fileName);
		
		System.out.println("[DEBUG] File exists >"+data.exists());
		System.out.println("[DEBUG] File readable >"+data.canRead());
		
		BufferedReader reader;
		String line = "";
		try {
			reader = new BufferedReader(new FileReader(data));
			//first line is useless
			reader.readLine();
			//read second line
			line = reader.readLine();
			
			int indexOfHrData = 0;
			int timeDataPair = 0;
			//work out which column the HR data is stored in (suunto records HR data 1 time per second so time can
			//be calculated by delta line number.
			String[] splitLine = line.split(",");
			for (int i = 0; i < splitLine.length; i++) {
				if(splitLine[i].equals("HeartRate")) {
					indexOfHrData = i;
				}
				if(splitLine[i].equals("Time")) {
					indexOfHrData = i;
				}
			}
			
			while( (line = reader.readLine()) != null ) {
				splitLine = line.split(",");
				int hr = Operations.toInt( splitLine[indexOfHrData] );
				if(hr!=0) {
					heartRate.add( (Integer) hr );
					time.add( (Integer) timeDataPair++ );
				}
			}
			
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DataHandler suunto = new DataHandler();
		String prefix = System.getProperty("user.dir");
		String filePath = prefix+"\\src\\backend\\"+""+"sample_data_suunto.csv";
		suunto.importDataFromFile(filePath);
		suunto.printTables();
	}
}
