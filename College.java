public class College {
    // Name of the college.
    private String name;
    // Median salary after 10 years.
    private int medianSalary;
    // Average SAT for admitted freshman.
    private int avgSAT;
    // Average GPA for admitted freshman.
    private double avgGPA;
    // Acceptance Rate at the college.
    private double accRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMedianSalary() {
        return medianSalary;
    }

    public void setMedianSalary(int medianSalary) {
        this.medianSalary = medianSalary;
    }

    public int getAvgSAT() {
        return avgSAT;
    }

    public void setAvgSAT(int avgSAT) {
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
}


