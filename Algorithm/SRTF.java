package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class SRTF {
    private int[] arrivalTime; // array of arrival time of each process
    private int[] burstTime; // array of burst time of each process
    private int numProcesses; // number of processes

    public SRTF(int[] arrivalTime, int[] burstTime) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.numProcesses = arrivalTime.length;
    }

    public int[] getArrivalTime() {
        return arrivalTime;
    }

    public int[] getBurstTime() {
        return burstTime;
    }

    public int getNumProcesses() {
        return numProcesses;
    }

    public int[] getTurnaroundTime() {
        int[] turnaroundTime = new int[numProcesses];
        int[] remainingTime = Arrays.copyOf(burstTime, numProcesses);

        // initialize variables
        int currentTime = 0;
        int completed = 0;

        while (completed != numProcesses) {
            int shortestProcess = -1;
            int shortestTime = Integer.MAX_VALUE;

            // find the shortest remaining time process at current time
            for (int i = 0; i < numProcesses; i++) {
                if (arrivalTime[i] <= currentTime && remainingTime[i] < shortestTime && remainingTime[i] > 0) {
                    shortestProcess = i;
                    shortestTime = remainingTime[i];
                }
            }

            // if no process is found, increment the current time
            if (shortestProcess == -1) {
                currentTime++;
            } else {
                // execute the shortest remaining time process for one unit
                remainingTime[shortestProcess]--;
                currentTime++;

                // if process is completed, update turnaround time and completed counter
                if (remainingTime[shortestProcess] == 0) {
                    turnaroundTime[shortestProcess] = currentTime - arrivalTime[shortestProcess];
                    completed++;
                }
            }
        }

        return turnaroundTime;
    }

    public int[] getWaitingTime() {
        int[] waitingTime = new int[numProcesses];
        int[] turnaroundTime = getTurnaroundTime();

        for (int i = 0; i < numProcesses; i++) {
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
        }

        return waitingTime;
    }

    public String getGanttChart() {
        int[] remainingTime = Arrays.copyOf(burstTime, numProcesses);

        // initialize variables
        int currentTime = 0;
        int completed = 0;
        ArrayList<String> ganttChart = new ArrayList<>();
        //StringBuilder builder = new StringBuilder();

        // add initial time to gantt chart
        ganttChart.add("0");

        while (completed != numProcesses && currentTime < 100) {
            System.out.println("Time: "+currentTime+" Finished: "+completed);
            int shortestProcess = -1;
            int shortestTime = Integer.MAX_VALUE;

            // find the shortest remaining time process at current time
            for (int i = 0; i < numProcesses; i++) {
                if (arrivalTime[i] <= currentTime && remainingTime[i] < shortestTime && remainingTime[i] > 0) {
                    shortestProcess = i;
                    shortestTime = remainingTime[i];
                }
            }

            // if no process is found, increment the current time
            if (shortestProcess == -1) {
                currentTime++;
                ganttChart.add("-");
            } else {
                // add process to gantt chart
                ganttChart.add("P" + shortestProcess);

                // execute the shortest remaining time process for one unit
                remainingTime[shortestProcess]--;
                currentTime++;

                // if process is completed, update completed counter
                if (remainingTime[shortestProcess] == 0) {
                    completed++;
                }

            }

        }

        return String.join(" ", ganttChart);
    }

    public static void main(String[] args){
        int[] arrivalTime = {0, 2, 4, 5, 7};
        int[] burstTime = {5, 3, 1, 2, 6};
        SRTF srtf = new SRTF(burstTime, arrivalTime);
        // print results
        System.out.println(srtf.getGanttChart());
    }
}
