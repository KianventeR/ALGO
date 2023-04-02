import java.util.ArrayList;
import java.util.Comparator;
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
            int minArrival = Integer.MAX_VALUE;
            // choose shortest remaining time
            for (int i = 0; i < numProcesses; i++) {
                if (arrivalTime[i] <= currentTime && completed[i] == 0 && remainingTime[i] <= minRemainingTime) {
                    // choose the shortest arrival time
                    if(arrivalTime[i] < minArrival){
                        minArrival = arrivalTime[i];
                        minRemainingTime = remainingTime[i];
                        nextProcess = i;
                    }
                }
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
        processIDs = pids.stream().mapToInt(Integer::intValue).toArray();
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
            startTimes[i] = waitingTime[i] + arrivalTime[i];
            processIDUniques[i] = i;
        }
        this.turnaroundTimes = turnaroundTime;
        this.waitingTimes = waitingTime;
        this.processIDUniques = IntStream.range(0,completionTimes.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> processIDUniques[i])
        .toArray();
        this.startTimes = IntStream.range(0,arrivalTime.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> startTimes[i])
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

    public static void main(String[] args){
        int[] bursts = {1,4,2,3};
        int[] arrivals = {1,3,2,4};
        srtf test_srtf = new srtf(arrivals, bursts, bursts.length);

        //System.out.println(test_srtf.getGanttChart());
        int[] arrs = test_srtf.getProcessIDs();
        System.out.println("\nGantt Process IDs");
        for(int i = 0; i < arrs.length; i++){
            System.out.print(arrs[i] + " ");
        }
        System.out.println("\nProcesses");
        arrs = test_srtf.getProcessIDUniques();
        for(int i = 0; i < arrs.length; i++){
            System.out.print(arrs[i] + " ");
        }
        System.out.println("\nArrivals");
        arrs = test_srtf.getArrivalTimes();
        for(int i = 0; i < arrs.length; i++){
            System.out.print(arrs[i] + " ");
        }
        System.out.println("\nBurst Times");
        arrs = test_srtf.getBurstTimes();
        for(int i = 0; i < arrs.length; i++){
            System.out.print(arrs[i] + " ");
        }
        System.out.println("\nStart Times");
        arrs = test_srtf.getStartTimes();
        for(int i = 0; i < arrs.length; i++){
            System.out.print(arrs[i] + " ");
        }
        System.out.println("\nCompletion Times");
        arrs = test_srtf.getCompletionTimes();
        for(int i = 0; i < arrs.length; i++){
            System.out.print(arrs[i] + " ");
        }
        System.out.println("\nWaiting Times");
        arrs = test_srtf.getWaitingTimes();
        for(int i = 0; i < arrs.length; i++){
            System.out.print(arrs[i] + " ");
        }
        System.out.println("\nTurnaround Times");
        arrs = test_srtf.getTurnaroundTimes();
        for(int i = 0; i < arrs.length; i++){
            System.out.print(arrs[i] + " ");
        }
        System.out.println("\nAverage Waiting Time: "+test_srtf.getAverageWaitingTime());
        System.out.println("Average Turnaround Time: "+test_srtf.getAverageTurnaroundTime());
    }
}
