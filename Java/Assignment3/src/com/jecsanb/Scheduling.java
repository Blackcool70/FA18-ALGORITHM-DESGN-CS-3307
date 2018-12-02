/**
 * Jecsan Blanco
 * 2018FA ALGORITHM DESGN/ANAL (CS-3307-01)
 * Programming Assignment 3
 * This is ORIGINAL code, the file/classes were designed by myself
 * and my understanding of java objects and the tools. The only
 * resources used were java class and method documentation and the
 * professors provided examples.
 */
package com.jecsanb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Scheduling {


    public static void main(String[] args) {

        //part A
        //Your task in this problem is to run a greedy algorithm that schedules the
        // jobs (optimally) in decreasing order of the difference (weight - length).
        // If two jobs have equal difference (weight - length), then you should schedule
        // the job with higher weight first.

        //open file
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("Scheduling/jobs.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Unable to open file.");
            System.exit(1);
        }
        // read the first line and store it in numberOfJobs
        int numberOfJobs = scanner.nextInt();
        //System.out.printf("Number of jobs: %d\n", numberOfJobs);

        //create jobsA to hold the jobs that use difference
        ArrayList<Job> jobsA = new ArrayList<>(numberOfJobs);
        //create jobsB to hold the jobs that use division
        ArrayList<JobB> jobsB = new ArrayList<>(numberOfJobs);

        //for each line after, store each weight and length into
        // job and store the job into jobs.
        int weight, length;
        for (int i = 0; i < numberOfJobs; ++i) {
            weight = scanner.nextInt();
            length = scanner.nextInt();
            Job job = new Job(weight, length);
            JobB jobB = new JobB(weight, length);
            jobsA.add(job);
            jobsB.add(jobB);
//            System.out.printf("%s\n",job.toString());
        }
        //sorts using JobsA definition of "compareTo"
        Collections.sort(jobsA);
        //sorts using JobsB definition of "compareTo"
        Collections.sort(jobsB);

        //reverse as
        Collections.reverse(jobsA);
        Collections.reverse(jobsB);

        long sumOfWeightCostA = 0;
        long lengthsA = 0;

        long sumOfWeightCostB = 0;
        long lengthsB = 0;

        for (int i = 0; i < numberOfJobs; ++i) {

            lengthsA += jobsA.get(i).getLength();
            sumOfWeightCostA += jobsA.get(i).getWeight() * (lengthsA);

            lengthsB += jobsB.get(i).getLength();
            sumOfWeightCostB += jobsB.get(i).getWeight() * (lengthsB);
        }
        System.out.printf("WeightCost: %,d\n", sumOfWeightCostA);
        System.out.printf("WeightCost: %,d\n", sumOfWeightCostB);

    }

    //Class repenting a jobs given int he specifications
    //The class implements the Comparable interface for easy sorting
    private static class Job implements Comparable<Job> {
        private final int WEIGHT;
        private final int LENGTH;

        Job(int weight, int length) {
            this.WEIGHT = weight;
            this.LENGTH = length;
        }

        int getWeight() {
            return WEIGHT;
        }

        int getLength() {
            return LENGTH;
        }

        @Override
        public String toString() {
            return ("(" + getWeight() + "," + getLength() + ")");
        }

        @Override
        public boolean equals(Object obj) {
            if (this.getClass() != obj.getClass()) return false;
            if (this == obj) return true;
            Job other = (Job) obj;
            return this.getWeight() == other.getWeight() &&
                    this.getLength() == other.getLength();
        }

        /**
         * Compares based onto specifications.
         *
         * @param other the other job to compare to
         * @return which one is "bigger" based on the definition in specifications
         * "Your task in this problem is to run a greedy algorithm that schedules
         * the jobs (optimally) in decreasing order of the difference (weight - length).
         * If two jobs have equal difference (weight - length),
         * then you should schedule the job with higher weight first."
         */
        @Override
        public int compareTo(Job other) {
            int thisVal, otherVal;
            thisVal = this.getWeight() - this.getLength();
            otherVal = other.getWeight() - other.getLength();
            int compare = Integer.compare(thisVal, otherVal);
            if (compare == 0) {
                return Integer.compare(this.getWeight(), other.getWeight());
            } else {
                return compare;
            }

        }
    }

    private static class JobB extends Job {

        JobB(int weight, int length) {
            super(weight, length);
        }
        /**
         * Compares based onto specifications.
         *
         * @param other the other job to compare to
         * @return which one is "bigger" based on the definition in specifications
         * "Your task in this problem is to run a greedy algorithm that schedules
         * the jobs (optimally) in decreasing order of the ratio (weight / length).
         * If two jobs have equal ratio (weight/length),
         * then you should schedule the job with higher weight first."
         */
        @Override
        public int compareTo(Job other) {
            float thisVal, otherVal;
            thisVal = (float) this.getWeight() / this.getLength();
            otherVal = (float) other.getWeight() / other.getLength();
            int compare = Float.compare(thisVal, otherVal);
            if (compare == 0) {
                return Integer.compare(this.getWeight(), other.getWeight());
            } else {
                return compare;
            }

        }
    }
}
