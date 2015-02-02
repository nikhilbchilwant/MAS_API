package mas.customer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import mas.job.job;
import mas.job.jobAttribute;
import mas.job.jobDimension;
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JobGenerator extends JobGeneratorIFace{
		
	private String jobFilePath;
	private ArrayList<String> jobIdList;
	private ArrayList<Double> jobProcessingTimes, jobCPNs, jobDueDates;
	private ArrayList<Integer> jobQuantity;
	private ArrayList<ArrayList<jobDimension> > jobDimensions;
	private ArrayList<ArrayList<jobAttribute> > jobAttributes;
	int countJob = 0;
	
	public JobGenerator() {
		this.jobIdList = new ArrayList<String>();
		this.jobProcessingTimes = new ArrayList<Double>();
		this.jobQuantity = new ArrayList<Integer>();
		this.jobDimensions = new ArrayList<ArrayList<jobDimension> >();
		this.jobCPNs = new ArrayList<Double>();
		this.jobDueDates = new ArrayList<Double>();
		this.jobAttributes = new ArrayList<ArrayList<jobAttribute> >();
		
//		URL location = JobGenerator.class.getProtectionDomain().getCodeSource().getLocation();
//		this.jobFilePath = location.toExternalForm();
		this.jobFilePath = System.getProperty("user.dir");
//      System.out.println(this.jobFilePath);
	}
	
	@Override
	public void readFile() {
			XSSFWorkbook wb;
			XSSFSheet sheet = null;
			try{
				FileInputStream file=new FileInputStream(this.jobFilePath +
														"\\data.xlsx");	
				wb = new XSSFWorkbook(file);
				sheet = wb.getSheetAt(0);
			}catch(IOException e){
				e.printStackTrace();
			}
	            
	        Iterator<Row> rows = sheet.rowIterator();
	        XSSFRow row = (XSSFRow) rows.next();
			int row_count = 0;
	        while( rows.hasNext() ) {
	        		
		            row = (XSSFRow) rows.next();
		            Iterator<Cell> cells = row.cellIterator();
		            
		            int count = 0; 
		            while(cells.hasNext()){
		            	  XSSFCell cell = (XSSFCell) cells.next();
		            	  
		            	  switch(count){
			            	  case 0:
			            		  jobIdList.set(row_count,cell.getStringCellValue());
			            		  break;
			            	  case 1:
			            		  jobProcessingTimes.set(row_count,cell.getNumericCellValue());
			            		  break;
			            	  case 2:
			            		  jobQuantity.set(row_count,(int) cell.getNumericCellValue());
//			            		  System.out.println("q="+ quantity[row_count]);
			            		  break;
			            	  case 3:
			            		  String s = cell.getStringCellValue();
			            		  String temp[] = s.split(",");
//			            		  System.out.println("length="+temp.length);
			            		  ArrayList<jobDimension> tempDimList = new ArrayList<jobDimension>();
			            		  jobDimension tempDim = new jobDimension();
			            		  for(int i=0; i < temp.length; i++){
			            			  tempDim.setTargetDimension(Double.parseDouble(temp[i]));
			            			  tempDimList.add(tempDim );
			            		  }
			            		  jobDimensions.add(tempDimList);
			            		  break;
			            	  case 4:
			            		  String Attr=cell.getStringCellValue();
			            		  String tempAttr[]=Attr.split(",");
			            		  
			            		  ArrayList<jobAttribute> tempAttrList = new ArrayList<jobAttribute>();
			            		  jobAttribute tempAttribute = new jobAttribute();
			            		  
			            		  for(int i=0; i < tempAttr.length; i++){
			            			  tempAttribute.setTitle(tempAttr[i]);
			            			  tempAttrList.add(tempAttribute );
			            		  }
			            		  jobAttributes.add(tempAttrList);
			            		  break;
			            		  
			            	  case 5:
			            		  jobCPNs.set(row_count,cell.getNumericCellValue());
//			            		  System.out.println("hello");
			            		  break;
			            	  case 6:
			            		  jobDueDates.set(row_count,cell.getNumericCellValue());
//			            		  System.out.println("Due date " + dDate[row_count]);
			            		  break;
		            	  	}
		            	  count++;
		            }
		            row_count++;
	        	}
			}  //End of ReadFile
		/**
		 * Generate and return the next job to be dispatched
		 */
	@Override
	public Object getNextJob() {
		int index = runif(1,jobQuantity)[0];
		
		double due = jobDueDates.get(index) + System.currentTimeMillis()/1000;
		
		job j = new job.Builder(12)
				.jobCPN(jobCPNs.get(index))
				.jobDueDateTime(new Date())
				.jobGenTime(System.currentTimeMillis())
				.jobProcTime(jobProcessingTimes.get(index))
				.jobDimensions(jobDimensions.get(index))
				.jobAttrbitues(jobAttributes.get(index))
				.build() ;
		
		return j;
	}
	
	/**
	 * Generate random number(between 0 and 1) following discrete distribution of weights 
	 * @param numSamples
	 * @param weights
	 * @return
	 */
	public static int[] runif(int numSamples,ArrayList<Integer> weights) {
		int size = weights.size();
		int[] numsToGenerate = new int[size];
		double sum = 0.0;
		double[] discreteProbabilities = new double[size];
		int i;
		for(i = 0;i < size; i++) {
			discreteProbabilities[i] = weights.get(0);
			sum += weights.get(0);
			numsToGenerate[i] = i;
		}
		for(i = 0;i < size; i++) {
			discreteProbabilities[i] = weights.get(i)/sum;
//			System.out.print("discreteProbabilities["+i+"]="+temp[i]+"/"+sum +":"+weights[i]);
		}

		EnumeratedIntegerDistribution distribution = 
		    new EnumeratedIntegerDistribution(numsToGenerate, discreteProbabilities);
		
		return distribution.sample(numSamples);
	}
}

