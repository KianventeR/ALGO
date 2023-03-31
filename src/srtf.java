package src;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class srtf {
    private int[] arrivalTime;
    private int[] burstTime;
    private int[] processIDs;
    private int[] processIDUniques;
    private int numProcesses;
    private int[] turnaroundTimes;
    private int[] waitingTimes;
    private int[] remainingTime;
    private int[] completionTimes;
    private int[] startTimes;
    private double averageTurnaroundTime;
    private double averageWaitingTime;
    
    public srtf(int[] arrivalTime, int[] burstTime, int numProcesses) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.numProcesses = numProcesses;
        this.processIDUniques = new int[numProcesses];
        this.turnaroundTimes = new int[numProcesses];
        this.waitingTimes = new int[numProcesses];
        this.remainingTime = new int[numProcesses];
        this.completionTimes = new int[numProcesses];
        this.startTimes = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            this.remainingTime[i] = burstTime[i];
        }
        String res = getGanttChart();
        calculate();
    }
    
    public int[] getArrivalTime() {
        return arrivalTime;
    }
    
    public int[] getBurstTime() {
        return burstTime;
    }
    
    public int[] getTurnaroundTimes() {
        return turnaroundTimes;
    }
    
    public int[] getWaitingTimes() {
        return waitingTimes;
    }

    public int[] getCompletionTimes() {
        return completionTimes;
    }

    public int[] getStartTimes() {
        return startTimes;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }
    public int[] getProcessIDUniques() {
        return processIDUniques;
    }
    public int[] getProcessIDs() {
        return processIDs;
    }
    public String getGanttChart() {
        ArrayList<Integer> pids = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int currentTime = 0;
        int[] completed = new int[numProcesses];
        int completedCount = 0;
        while (completedCount < numProcesses) {
            int minRemainingTime = Integer.MAX_VALUE;
            int nextProcess = -1;
            for (int i = 0; i < numProcesses; i++) {
                if (arrivalTime[i] <= currentTime && completed[i] == 0 && remainingTime[i] < minRemainingTime) {
                    minRemainingTime = remainingTime[i];
                    nextProcess = i;
                }
            }
            if (nextProcess == -1) {
                sb.append("-");
                currentTime++;
            } else {
                sb.append("P" + nextProcess);
                pids.add(nextProcess);
                remainingTime[nextProcess]--; // Decrement remaining time of current process
                if (remainingTime[nextProcess] == 0) { // If current process has completed
                    currentTime++;
                    completionTimes[nextProcess] = currentTime;
                    completed[nextProcess] = 1;
                    completedCount++;
                }
            }
        }
        processIDs = pids.stream().mapToInt(Integer::intValue).toArray();
        return sb.toString();
    }
    
    public void calculate() {
        int[] turnaroundTime = new int[numProcesses];
        int[] waitingTime = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            turnaroundTime[i] = completionTimes[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            startTimes[i] = waitingTime[i] + arrivalTime[i];
            processIDUniques[i] = i;
        }
        this.turnaroundTimes = turnaroundTime;
        this.waitingTimes = waitingTime;
        averageWaitingTime = IntStream.range(0,waitingTimes.length).boxed()
            .mapToInt(i -> waitingTimes[i]).average().orElse(0.0);
        averageTurnaroundTime = IntStream.range(0,turnaroundTimes.length).boxed()
            .mapToInt(i -> turnaroundTimes[i]).average().orElse(0.0); 
    }

    /*public static void main(String[] args){
        int[] bursts = {1,4,2};
        int[] arrivals = {1,3,3};
        SRTF test_srtf = new SRTF(bursts, arrivals, bursts.length);

        test_srtf.calculate();
        System.out.println(test_srtf.getGanttChart());
    }*/
}

