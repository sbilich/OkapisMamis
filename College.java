/*
* Represents one data point containing info pertaining to a
* college.
*/

import java.io.Serializable;
import java.lang.Math.*;
import java.util.ArrayList;

public class College implements Serializable, Comparable {
    // Name of the college.
    private String name;
    // Median salary after 10 years.
    private double medianSalary;
    // Average SAT for admitted freshman.
    private double avgSAT;
    // Average GPA for admitted freshman.
    private double avgGPA;
    // Acceptance Rate at the college.
    private double accRate;

    public College() {
      this.name = null;
      this.medianSalary = 0;
      this.avgSAT = 0;
      this.avgGPA = 0;
      this.accRate = 0;
    }

    public College(String name, double medianSalary, double avgSAT,
        double avgGPA, double accRate) {
      this.name = name;
      this.medianSalary = medianSalary;
      this.avgSAT = avgSAT;
      this.avgGPA = avgGPA;
      this.accRate = accRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMedianSalary() {
        return medianSalary;
    }

    public void setMedianSalary(double medianSalary) {
        this.medianSalary = medianSalary;
    }

    public double getAvgSAT() {
        return avgSAT;
    }

    public void setAvgSAT(double avgSAT) {
        this.avgSAT = avgSAT;
    }

    public double getAvgGPA() {
        return avgGPA;
    }

    public void setAvgGPA(double avgGPA) {
        this.avgGPA = avgGPA;
    }

    public double getAccRate() {
        return accRate;
    }

    public void setAccRate(double accRate) {
        this.accRate = accRate;
    }

    public String toString() {
      return this.name;
    }

    public String toVerboseString() {
      String str = "";

      str += this.name + " ";
      str += "[";
      str += this.medianSalary + ", ";
      str += this.avgSAT + ", ";
      str += this.avgGPA + ", ";
      str += this.accRate + "]";

      return str;
    }

    /* gets the distance between two Colleges, |this| and |c| using the
    * Euclidean distance formula.
    */
    public double getDistance(College c) {
      double salary_diff = Math.pow(this.medianSalary - c.getMedianSalary(), 2.0);
      double sat_diff = Math.pow(this.avgSAT - c.getAvgSAT(), 2.0);
      double gpa_diff = Math.pow(this.avgGPA - c.getAvgGPA(), 2.0);
      double accRate_diff = Math.pow(this.accRate - c.getAccRate(), 2.0);

      return Math.sqrt(salary_diff + sat_diff + gpa_diff + accRate_diff);
    }


    public static College getMeanCollege(ArrayList<College> lis) {
      double sum_salary = 0;
      double sum_sat = 0;
      double sum_gpa = 0;
      double sum_accRate = 0;

      for (College c : lis) {
        sum_salary += c.getMedianSalary();
        sum_sat += c.getAvgSAT();
        sum_gpa += c.getAvgGPA();
        sum_accRate += c.getAccRate();
      }

      sum_salary *= (1.0 / lis.size());
      sum_sat *= (1.0 / lis.size());
      sum_gpa *= (1.0 / lis.size());
      sum_accRate *= (1.0 / lis.size());

      return new College(null, sum_salary, sum_sat, sum_gpa, sum_accRate);
    }

    @Override public int compareTo(Object c) {
      int EQUAL = 0;
      int NOT_EQUAL = -1;

      if (c == this) return EQUAL;

      if (!(c instanceof College)) return NOT_EQUAL;
      College oth = (College)c;

      if (oth.getMedianSalary() != this.medianSalary) return NOT_EQUAL;
      if (oth.getAvgSAT() != this.avgSAT) return NOT_EQUAL;
      if (oth.getAvgGPA() != this.avgGPA) return NOT_EQUAL;
      if (oth.getAccRate() != this.accRate) return NOT_EQUAL;

      return EQUAL;
    }
}
