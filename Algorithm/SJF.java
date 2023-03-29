package Algorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Comparator;
import java.util.Arrays;

public class SJF {
    private int[] bursts;
    private int[] arrivals;
    private int[] processIDs;
    private int[] endTimes;
    private int[] turnarounds;
    private int[] waitingTimes;
    private double avgWaitingTime;
    private double avgTurnaroundTime;

    public SJF(int[] burst_times, int[] arrival_times){
        int n = arrival_times.length;

        // create a list of processes
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            processes.add(new Process(i+1, arrival_times[i], burst_times[i]));
        }
        // sort by arrival time first
        processes.sort(Comparator.comparing(Process::getArrivalTime).
            thenComparing(Comparator.comparing(Process::getBurstTime)));

        // run the processes and calculate waiting time and turnaround time
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] arriveTime = new int[n];
        int[] pids = new int[n];
        int[] burstTimes = new int[n];
        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            Process p = processes.get(i);
            pids[i] = p.getId();
            arriveTime[i] = p.getArrivalTime();
            burstTimes[i] = p.getBurstTime();
            waitingTime[p.getId()-1] = currentTime - p.getArrivalTime();
            turnaroundTime[p.getId()-1] = waitingTime[p.getId()-1] + p.getBurstTime();
            currentTime += p.getBurstTime();
        }

        waitingTimes = waitingTime;
        arrivals = arriveTime; 
        processIDs = pids;
        bursts = burstTimes;
        turnarounds = turnaroundTime;
        endTimes = IntStream.range(0,bursts.length).boxed()
                .mapToInt(i-> turnarounds[i] + arrivals[i])
                .toArray();

        // calculate and print the average waiting time and turnaround time
        avgWaitingTime = Arrays.stream(waitingTime).average().getAsDouble();
        avgTurnaroundTime = Arrays.stream(turnaroundTime).average().getAsDouble();
        
    }

    public int[] getArrivals() {
        return arrivals;
    }

    public int[] getBursts() {
        return bursts;
    }

    public int[] getEndTimes() {
        return endTimes;
    }

    public int[] getWaitingTimes() {
        return waitingTimes;
    }

    public String toString(){
        final String header = "Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time\n";
        final String footer1 = String.format("Average Waiting Time: %.2f\n", avgWaitingTime);
        final String footer2 = String.format("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
        final String result = header + IntStream.range(0,processIDs.length).boxed()
            .map( i -> String.format("%d\t%d\t\t%d\t\t%d\t\t%d\n",processIDs[i],arrivals[i],bursts[i],waitingTimes[i],turnarounds[i]))
            .reduce(String::concat) + footer1 + footer2;
        return result;
    }
    
    public static void main(String[] args) {
        int[] arrivalTime = {0, 2, 4, 5, 7};
        int[] burstTime = {5, 3, 1, 2, 6};
        SJF sjf = new SJF(burstTime, arrivalTime);
        // print results
        System.out.println(sjf.toString());
    }

    static class Process {
        private final int id;
        private final int arrivalTime;
        private final int burstTime;

        public Process(int id, int arrivalTime, int burstTime) {
            this.id = id;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
        }

        public int getId() {
            return id;
        }

        public int getArrivalTime() {
            return arrivalTime;
        }

        public int getBurstTime() {
            return burstTime;
        }
    }
}