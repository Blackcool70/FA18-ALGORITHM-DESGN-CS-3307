package com.jecsanb;

import java.io.File;
import java.io.FileNotFoundException;
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
            scanner = new Scanner(new File("Scheduling/test1.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Unable to open file.");
            System.exit(1);
        }
        // read the first line and store it in numberOfJobs
        int numberOfJobs = scanner.nextInt();
        System.out.printf("Number of jobs: %d",numberOfJobs);

        //for each line after, store each weight and length into
        // job and store the job into jobs.


        //sort jobs by least to greatest based on weight - length
        //create a variable sumOfTheWeightedCompletionTimes and set it to 0
        //for each job add the jobLength to sumOfTheWeightedCompletionTimes


    }
}
