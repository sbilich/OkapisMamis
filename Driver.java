import java.io.*;
import java.util.*;

public class Driver {

	private static final int COLLEGE_NAME = 0;
	private static final int MEDIAN_SALARY = 1;
	private static final int AVG_SAT = 2;
	private static final int AVG_GPA = 3;
	private static final int ACC_RATE = 4;

	private static ArrayList<College> colleges = new ArrayList<College>();

	public static void main(String []args) throws IOException {
		readInFile("Colleges.txt");
		serializeObj();
	}

	// Reads in the file storing each college in the colleges ArrayList.
	private static void readInFile(String file) throws IOException {
		try {
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			while ((strLine = br.readLine()) != null)   {
				String collegeInfo[] = strLine.toString().split(",");

				College college = new College();
				college.setName(collegeInfo[COLLEGE_NAME]);
				college.setMedianSalary(Integer.parseInt(collegeInfo[MEDIAN_SALARY]));
				college.setAvgSAT(Integer.parseInt(collegeInfo[AVG_SAT]));
				college.setAvgGPA(Double.parseDouble(collegeInfo[AVG_GPA]));

				// Removes percent from number.
				String accRate[] = collegeInfo[ACC_RATE].split("%");
				college.setAccRate(Double.parseDouble(accRate[0]));

				colleges.add(college);
			}
			br.close();
		} catch (IOException e) {
			throw e;
		}
	}

	// Only needed for debugging - quick and easy way to print all colleges.
	private static void printColleges() {
		for (College college : colleges) {
			System.out.println("Name: " + college.getName());
			System.out.print("Median Salary: " + college.getMedianSalary());
			System.out.println(" Average SAT: " + college.getAvgSAT());
			System.out.print("Average GPA: " + college.getAvgGPA());
			System.out.println("  Acceptance Rate: " + college.getAccRate());
			System.out.println();
		}
	}

	public static void serializeObj() {
		try (ObjectOutputStream os = new ObjectOutputStream(new
					FileOutputStream(new File("./collegeList")))) {
				os.writeObject(colleges);
		} catch (Exception e) {
				System.out.println(e);
		}
	}
}
