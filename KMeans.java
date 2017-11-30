/*
* This program performs the K-means clustering algorithm on College objects.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KMeans {

  /*
  * Calculates and returns |num| clusters using the k-means clustering
  * algorithm on the data |colleges|.
  */
  public static ArrayList<ArrayList<College>> getKMeansClusters(
      ArrayList<College> colleges, int num) {

    ArrayList<College> centroids =  getInitialCentroids(colleges, num);

    HashMap<College, ArrayList<College>> clusters =
        assignDataToCentroids(colleges, centroids);

    ArrayList<College> new_centroids = computeCentroids(clusters);

    while (compareCentroids(centroids, new_centroids) != 0) {
        centroids = new_centroids;

        clusters = assignDataToCentroids(colleges, centroids);

        new_centroids = computeCentroids(clusters);
    }

    return getClusters(clusters);
  }

  private static void printAssignments(HashMap<College, ArrayList<College>> clusters) {
    System.out.println("Found centroid assignments\n");
    for (Map.Entry<College, ArrayList<College>> e : clusters.entrySet()) {
      System.out.println("Cluster for centroid " + e.getKey() + " = " + e.getValue() + "\n");
    }
    System.out.print("\n");
  }

  private static ArrayList<ArrayList<College>> getClusters(HashMap<College,
      ArrayList<College>> map) {
    ArrayList<ArrayList<College>> clusters = new ArrayList<ArrayList<College>>();

    for (ArrayList<College> lis : map.values()) {
      clusters.add(lis);
    }

    return clusters;
  }

  private static int compareCentroids(ArrayList<College> oldLis, ArrayList<College> newLis) {
    int res = 0;
    int differ = 0;

    for (int i=0; i<oldLis.size(); i++) {
      if (newLis.get(i).compareTo(oldLis.get(i)) != 0) {
        differ++;
      }
    }

    if (differ > 1)
      res = -1;

    return res;
  }

  /*
  * Randomly selects |num| centroids from |data| to be the first clusters.
  */
  private static ArrayList<College> getInitialCentroids(
      ArrayList<College> data, int num) {

    ArrayList<College> centroids = new ArrayList<College>();

    for (int i=0; i<num; i++) {
      boolean picked = false;
      /* select random index */
      int idx = getRandomArrayIdx(data.size());

      while (!picked) {
        if (!centroids.contains(data.get(idx))) {
          centroids.add(data.get(idx));
          picked = true;
        } else {
          /* pick a new index and try again */
          idx = getRandomArrayIdx(data.size());
        }
      }
    }

    return centroids;
  }

  private static int getRandomArrayIdx(int max) {
    return (int)(Math.random() * max);
  }

  private static ArrayList<College> computeCentroids(
      HashMap<College, ArrayList<College>> clusters) {

    ArrayList<College> new_centroids = new ArrayList<College>();

    for (ArrayList<College> c : clusters.values()) {
      College new_c = College.getMeanCollege(c);
      new_centroids.add(new_c);
    }

    return new_centroids;
  }

  private static HashMap<College, ArrayList<College>> assignDataToCentroids(
      ArrayList<College> data, ArrayList<College> centroids) {
        HashMap<College, ArrayList<College>> map =
            new HashMap<College, ArrayList<College>>();

        /* initialize empty map of centroids and assigned data points */
        for (College c : centroids) {
          map.put(c, new ArrayList<College>());
        }

        /* for each data point assign to a centroid */
        for (College point : data) {
          double dist = point.getDistance(centroids.get(0));
          int closest = 0; /* index of closest centroid in |centroids| */

          /* find closest centroid to the point */
          for (int i=1; i<centroids.size(); i++) {
             double tmp_dist = point.getDistance(centroids.get(i));
             if (tmp_dist < dist) {
               dist = tmp_dist;
               closest = i;
             }
          }

          College chosen = centroids.get(closest);
          (map.get(chosen)).add(point);
        }

        return map;
  }
}
