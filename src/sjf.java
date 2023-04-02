
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

public class sjf {
    private static int[] processIds;
    private int[] arrivalTime;
    private int[] burstTime;
    private int numProcesses;
    private int[] turnaroundTimes;
    private int[] waitingTimes;
    private int[] remainingTimes;
    private static int[] completionTimes;
    private static int[] startTimes;
    private double averageTurnaroundTime;
    private double averageWaitingTime;
    ArrayList<Integer> sortedPids = new ArrayList<>();
    ArrayList<Integer> pids = new ArrayList<>();

    
    public sjf(int[] arrivalTime, int[] burstTime, int numProcesses) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.numProcesses = numProcesses;
        this.turnaroundTimes = new int[numProcesses];
        this.waitingTimes = new int[numProcesses];
        this.remainingTimes = new int[numProcesses];
        this.completionTimes = new int[numProcesses];
        this.startTimes = new int[numProcesses];
        
        this.processIds = IntStream.range(0,arrivalTime.length).boxed()
        .sorted(Comparator.comparing(i->this.arrivalTime[i]))
        .mapToInt(Integer::intValue)
        .toArray();

        for(int i : processIds){
            pids.add(i);
        }

        for (int i = 0; i < numProcesses; i++) {
            this.remainingTimes[i] = burstTime[i];
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

    public static int[] getCompletionTimes() {
        return completionTimes;
    }

    public static int[] getStartTimes() {
        return startTimes;
    }

    public static int[] getProcessIDs() {
        return processIds;
    }

    public double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }
    
    public String getGanttChart() {
        StringBuilder sb = new StringBuilder();
        int currentTime = 0;
        int[] completed = new int[numProcesses];
        int completedCount = 0;

        while (completedCount < numProcesses) {
            int minRemainingTime = Integer.MAX_VALUE;
            int nextProcess = -1;
            int minArrival = Integer.MAX_VALUE;
            // find the process with shortest burst time which arrived already and not completed
            for (int i = 0; i < numProcesses; i++) {
                if (arrivalTime[i] <= currentTime && completed[i] == 0 && remainingTimes[i] <= minRemainingTime) {
                        // get the least arrival time
                        if(minRemainingTime == remainingTimes[i]){
                            if(arrivalTime[i] < minArrival){
                                minArrival = arrivalTime[i];
                                minRemainingTime = remainingTimes[i];
                                nextProcess = i;
                            }
                        }else{
                            minArrival = arrivalTime[i];
                            minRemainingTime = remainingTimes[i];
                            nextProcess = i;
                        }
                }
            }
            // if there is none at one time instance, system is idle
            if (nextProcess == -1) {
                sb.append("-");
                // let time pass
                currentTime++;
            } else {
                // run the process with shortest burst time till completion
                sortedPids.add(nextProcess);
                sb.append("P" + nextProcess);
                currentTime += remainingTimes[nextProcess];
                completionTimes[nextProcess] = currentTime;
                remainingTimes[nextProcess] = 0;
                completed[nextProcess] = 1;
                completedCount++;
            }
        }
        return sb.toString();
    }
    
    public void calculate() {
        int[] turnaroundTime = new int[numProcesses];
        int[] waitingTime = new int[numProcesses];
        int[] startTime = new int[numProcesses];

        //arrivalTime = pids.stream()
        //.mapToInt(i -> this.arrivalTime[i])
        //.toArray();

        for (int i = 0; i < numProcesses; i++) {
            turnaroundTime[i] = completionTimes[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            startTime[i] = waitingTime[i] + arrivalTime[i];
        }
        this.turnaroundTimes = turnaroundTime;
        this.waitingTimes = waitingTime;
        this.startTimes = startTime;
        
        startTimes = IntStream.range(0,completionTimes.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> startTimes[i])
        .toArray();
        completionTimes = IntStream.range(0,completionTimes.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> completionTimes[i])
        .toArray();
        waitingTimes = IntStream.range(0,completionTimes.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> waitingTimes[i])
        .toArray();
        turnaroundTimes = IntStream.range(0,completionTimes.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> turnaroundTimes[i])
        .toArray();
        burstTime = IntStream.range(0,completionTimes.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> burstTime[i])
        .toArray();
        arrivalTime = IntStream.range(0,completionTimes.length).boxed()
        .sorted(Comparator.comparing(i->arrivalTime[i]))
        .mapToInt(i -> arrivalTime[i])
        .toArray();

        averageWaitingTime = IntStream.range(0,waitingTimes.length).boxed()
            .mapToInt(i -> waitingTimes[i]).average().orElse(0.0);
        averageTurnaroundTime = IntStream.range(0,turnaroundTimes.length).boxed()
            .mapToInt(i -> turnaroundTimes[i]).average().orElse(0.0); 
    }
}