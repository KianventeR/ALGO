

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

public class srtf {
    private int[] arrivalTime;
    private int[] burstTime;
    private static int[] processIDs;
    private int[] processIDUniques;
    private int numProcesses;
    private int[] turnaroundTimes;
    private int[] waitingTimes;
    private int[] remainingTime;
    private int[] completionTimes;
    private static int[] startTimes;
    private static int[] endTimes;
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
        startTimes = new int[numProcesses];
        
        for (int i = 0; i < numProcesses; i++) {
            this.remainingTime[i] = burstTime[i];
        }
        getGanttChart();
        calculate();
    }
    
    public int[] getArrivalTimes() {
        return arrivalTime;
    }
    
    public int[] getBurstTimes() {
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

    public static int[] getStartTimes() {
        return startTimes;
    }
    public static int[] getEndTimes() {
        return endTimes;
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
    public static int[] getProcessIDs() {
        return processIDs;
    }
    public String getGanttChart() {
        ArrayList<Integer> pids = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int currentTime = 0;
        int[] completed = new int[numProcesses];
        int completedCount = 0;
        ArrayList<Integer> firstExecs = new ArrayList<>();
        ArrayList<Integer> lastExecs = new ArrayList<>();

        while (completedCount < numProcesses) {
            int minRemainingTime = Integer.MAX_VALUE;
            int prevProcess = -1;
            int nextProcess = -1;
            int minArrival = Integer.MAX_VALUE;
            
            // choose shortest remaining time
            for (int i = 0; i < numProcesses; i++) {
                if (arrivalTime[i] <= currentTime && completed[i] == 0 && remainingTime[i] <= minRemainingTime) {
                    // get the least arrival time
                    if(minRemainingTime == remainingTime[i]){
                        if(arrivalTime[i] < minArrival){
                            minArrival = arrivalTime[i];
                            minRemainingTime = remainingTime[i];
                            nextProcess = i;
                        }
                    }else{
                        minArrival = arrivalTime[i];
                        minRemainingTime = remainingTime[i];
                        nextProcess = i;
                    }
                }
            }
            // save the first changes of a process
            if(prevProcess != nextProcess){
                firstExecs.add(currentTime);
                // if some time passed already, only then add
                if(currentTime > 0){
                    lastExecs.add(currentTime + 1);
                }
                prevProcess = nextProcess;
            }
            // let time pass
            currentTime++;
            
            // if no process is present, be idle
            if (nextProcess == -1) {
                sb.append("-");
            } else {
                // run shortest remaining time for one time unit
                sb.append("P" + nextProcess);
                pids.add(nextProcess);
                remainingTime[nextProcess]--; // Decrement remaining time of current process
                if (remainingTime[nextProcess] == 0) { // If current process has completed
                    completionTimes[nextProcess] = currentTime;
                    completed[nextProcess] = 1;
                    completedCount++;
                }
            }
        }
        // gantt chart variables
        processIDs = pids.stream().mapToInt(Integer::intValue).toArray();
        startTimes = firstExecs.stream().mapToInt(Integer::intValue).toArray();
        endTimes = lastExecs.stream().mapToInt(Integer::intValue).toArray();

        pids = new ArrayList<>();
        firstExecs = new ArrayList<>();
        lastExecs = new ArrayList<>();

        // compaction of continuous process ids, start times, and end times
        int prevProcess = -1;
        int nextProcess = -1;
        for(int i = 0; i < startTimes.length; i++){
            nextProcess = processIDs[i];
            int index = pids.size() - 1;
            if(prevProcess == nextProcess && index > -1){
                // dont add the recent process and start time
                // remove the previous end time
                lastExecs.remove(index);
                // add the recent end time
                lastExecs.add(endTimes[i]);
            }else{
                // add three of them 
                pids.add(processIDs[i]);
                firstExecs.add(startTimes[i]);
                lastExecs.add(endTimes[i]);
            }
            prevProcess = nextProcess;
        }

        // gantt chart variables
        processIDs = pids.stream().mapToInt(Integer::intValue).toArray();
        startTimes = firstExecs.stream().mapToInt(Integer::intValue).toArray();
        endTimes = lastExecs.stream().mapToInt(Integer::intValue).toArray();

        processIDUniques = IntStream.range(0,arrivalTime.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(Integer::intValue)
        .toArray();
        return sb.toString();
    }
    
    public void calculate() {
        int[] turnaroundTime = new int[numProcesses];
        int[] waitingTime = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            turnaroundTime[i] = completionTimes[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            processIDUniques[i] = i;
        }
        this.turnaroundTimes = turnaroundTime;
        this.waitingTimes = waitingTime;
        this.processIDUniques = IntStream.range(0,completionTimes.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> processIDUniques[i])
        .toArray();
        this.completionTimes = IntStream.range(0,arrivalTime.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> completionTimes[i])
        .toArray();
        this.waitingTimes = IntStream.range(0,arrivalTime.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> waitingTimes[i])
        .toArray();
        this.turnaroundTimes = IntStream.range(0,arrivalTime.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> turnaroundTimes[i])
        .toArray();
        this.burstTime = IntStream.range(0,arrivalTime.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> burstTime[i])
        .toArray();
        this.arrivalTime = IntStream.range(0,arrivalTime.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> arrivalTime[i])
        .toArray();

        averageWaitingTime = IntStream.range(0,waitingTimes.length).boxed()
            .mapToInt(i -> waitingTimes[i]).average().orElse(0.0);
        averageTurnaroundTime = IntStream.range(0,turnaroundTimes.length).boxed()
            .mapToInt(i -> turnaroundTimes[i]).average().orElse(0.0); 
    }

    // public static void main(String[] args){
    //     int[] bursts = {29,18,7};
    //     int[] arrivals = {21,9,13};
    //     srtf test_srtf = new srtf(arrivals, bursts, bursts.length);

    //     //System.out.println(test_srtf.getGanttChart());
    //     int[] arrs = test_srtf.getProcessIDs();
    //     System.out.println("\nGantt Process IDs");
    //     for(int i = 0; i < arrs.length; i++){
    //         System.out.print((arrs[i]+1) + " ");
    //     }
    //     arrs = test_srtf.getStartTimes();
    //     System.out.println("\nGantt Start Times");
    //     for(int i = 0; i < arrs.length; i++){
    //         System.out.print((arrs[i]+1) + " ");
    //     }
    //     arrs = test_srtf.getEndTimes();
    //     System.out.println("\nGantt End Times");
    //     for(int i = 0; i < arrs.length; i++){
    //         System.out.print((arrs[i]+1) + " ");
    //     }
    //     System.out.println("\nProcesses");
    //     arrs = test_srtf.getProcessIDUniques();
    //     for(int i = 0; i < arrs.length; i++){
    //         System.out.print(arrs[i] + " ");
    //     }
    //     System.out.println("\nArrivals");
    //     arrs = test_srtf.getArrivalTimes();
    //     for(int i = 0; i < arrs.length; i++){
    //         System.out.print(arrs[i] + " ");
    //     }
    //     System.out.println("\nBurst Times");
    //     arrs = test_srtf.getBurstTimes();
    //     for(int i = 0; i < arrs.length; i++){
    //         System.out.print(arrs[i] + " ");
    //     }
    //     System.out.println("\nCompletion Times");
    //     arrs = test_srtf.getCompletionTimes();
    //     for(int i = 0; i < arrs.length; i++){
    //         System.out.print(arrs[i] + " ");
    //     }
    //     System.out.println("\nWaiting Times");
    //     arrs = test_srtf.getWaitingTimes();
    //     for(int i = 0; i < arrs.length; i++){
    //         System.out.print(arrs[i] + " ");
    //     }
    //     System.out.println("\nTurnaround Times");
    //     arrs = test_srtf.getTurnaroundTimes();
    //     for(int i = 0; i < arrs.length; i++){
    //         System.out.print(arrs[i] + " ");
    //     }
    //     System.out.println("\nAverage Waiting Time: "+test_srtf.getAverageWaitingTime());
    //     System.out.println("Average Turnaround Time: "+test_srtf.getAverageTurnaroundTime());
    // }
}
