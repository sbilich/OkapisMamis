/*
* This program opens a file called "collegeList" and reads in the
* seriallized College object ArrayList and then performs k-means clustering
* to cluster the colleges into two different clusters, pressumably "good"
* and "bad" colleges.
*/

import java.util.*;
import java.io.*;

public class Cluster {

  private static ArrayList<College> colleges;
  

  private static final int NUM_CLUSTERS = 2;

  public static void main(String args[]) {
      /* Load college data */
      getSerializedColleges();


      ArrayList<ArrayList<College>> clusters =
        KMeans.getKMeansClusters(colleges, NUM_CLUSTERS);

      printOutput(clusters);
      printEvaluation(clusters);
  }

  private static void getSerializedColleges() {
    try (ObjectInputStream is = new
      ObjectInputStream(new FileInputStream(new File("./collegeList")))) {
      colleges = (ArrayList<College>) is.readObject();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void printOutput(ArrayList<ArrayList<College>> clusters) {
    System.out.println("Results of K-means Clustering");
    System.out.println("---------------------------------");

    int i = 1;
    for (ArrayList<College> c : clusters) {
      System.out.println("Cluster " + i + " is:");
      System.out.println(c+"\n");
      i++;
    }
  }
  
  private static void printEvaluation(ArrayList<ArrayList<College>> clusters) {
	 System.out.println(""); 
	 System.out.println("Evaluation"); 
	 System.out.println("---------------------------------");
	 
	 Evaluator evaluate = Evaluator.getInstance(clusters); 
	 evaluate.printStats();
  }
}
