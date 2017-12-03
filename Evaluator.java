import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/* Evaluate the k-means algorithm using different evaluation methods including: 
 * 	- Precision
 *  - Recall
 *  - F-Score
 *  - MAP-Score
 */

public class Evaluator {
	
	private static Evaluator evaluator = null; 
	
	private static final int b = 1; 

	private ArrayList<String> badColleges; 
	private ArrayList<String> goodColleges; 
	

	private static final ArrayList<String> BAD_COLLEGE_NAMES = new ArrayList<String>(
			  Arrays.asList("Arkansas Baptist College", "Becker College", "Benedict College",
					  "Brooks Institute", "Chatfield Colelge", "Clark Atlanta University",
					  "Columbia College", "Columbia College", "Cornish College of the Arts",
					  "DeVry University", "Jacksonville College", "Johnson & Wales University-Denver",
					  "Kansas City Art Institute", "Miami International University of Art and Design",
					  "MidState College", "Mt. Sierra College", "Rocky Mountain College of Art and Design",
					  "Saint Augustine’s University", "Seattle Pacific University", "Shaw University",
					  "Shimer College", "Sojourner-Douglass College", "Southwestern Indian Polytechnic Institute",
					  "Stratford University", "West Los Angeles")); 

	  private static final ArrayList<String> GOOD_COLLEGE_NAMES = new ArrayList<String>(
			  Arrays.asList("Bowdoin College", "Brown University", "Cal Poly",
					  "Colgate University", "Columbia University", "Cornell University",
					  "Duke University", "Georgetown University", "Hardvard University",
					  "Massachusetts Institute of Technology (MIT)", "Northwestern University",
					  "Princeton University", "Rice University", "Stanford University",
					  "University of California at Berkeley", "University of California at Los Angeles",
					  "University of Chicago", "University of MIchigan at Ann Arbor",
					  "University of Notre Dame", "University of Pennsylvania",
					  "University of Virginia", "Unversity of Southern California", "Vanderbilt",
					  "Washington and Lee University", "Washington University in St. Louis",
					  "Yale University"
			  )); 
	  
	private Evaluator(ArrayList<ArrayList<College>> clusters) {
		this.badColleges = (ArrayList)clusters.get(0).stream().map(College::getName).collect(Collectors.toList());
		this.goodColleges = (ArrayList)clusters.get(1).stream().map(College::getName).collect(Collectors.toList());
	}

	public static Evaluator getInstance(ArrayList<ArrayList<College>> clusters) {
		if (evaluator == null)
			evaluator = new Evaluator(clusters); 
		return evaluator; 
	}
	
	public void printStats() {
		System.out.println("Evaluating Bad Colleges");
		getStats(BAD_COLLEGE_NAMES, badColleges);
		System.out.println("\n");

		System.out.println("Evaluating Good Colleges");
		getStats(GOOD_COLLEGE_NAMES, goodColleges); 
	}
	
	public static void getStats(ArrayList<String> relevant, ArrayList<String> returned) {
		double precision = calculatePrecision(relevant, returned); 
		double recall = calculateRecall(relevant, returned); 

		System.out.println("Precision: " + precision); 
		System.out.println("Recall: " + recall); 
		System.out.println("F-Score: " + fScore(precision, recall)); 

		System.out.println("MAP-Score: " + MAPScore(relevant, returned)); 
	}
	
	public static double calculatePrecision(ArrayList<String> relevant, ArrayList<String> returned) {
		int numerator = returned.stream().filter(college -> relevant.contains(college)).toArray().length;  
		int numReturned = returned.size(); 
		return (double) numerator / numReturned;
	}
	
	public static double calculateRecall(ArrayList<String> relevant, ArrayList<String> returned) {
		int numerator = returned.stream().filter(college -> relevant.contains(college)).toArray().length;  
		int numRelevant = relevant.size(); 
		return (double) numerator / numRelevant;
	}
	
	public static double fScore(double precision, double recall) {
		double numerator = (1+Math.pow(b, 2)) * precision * recall; 
		double denominator = ((Math.pow(b, 2)) * precision) + recall; 
		return numerator / denominator; 
	}
	
	public static double MAPScore(ArrayList<String> relevant, ArrayList<String> returned) {
		ArrayList<String> returnedLookedAt = new ArrayList<String>(); 
		ArrayList<String> relevantLookedAt = new ArrayList<String>(); 
		ArrayList<Double> precisionList = new ArrayList<Double>();
		
		for (String college : returned) {
			returnedLookedAt.add(college); 
			if (relevant.contains(college)) {
				relevantLookedAt.add(college); 
				// calculate precision so far
				double precision = calculatePrecision(relevantLookedAt, returnedLookedAt);  
				precisionList.add(precision); 
			}
		}
		
		// divide by number of relevant colleges
		double mapScore = precisionList.stream().mapToDouble(d->d).sum() / (double)relevant.size(); 
		return mapScore;
	}

}
