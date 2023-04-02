import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class rr {
    private int[] bursts;
    private int[] arrivals;
    private static int[] processIDs;
    private int[] processIDUniques;
    private static int[] startTimes;
    private static int[] endTimes;
    private int[] waitingTimes;
    private int[] turnaroundTimes;
    private int[] completions;
    private int quantum;
    private double averageWaitingTime;
    private double averageTurnaroundTime;

    public rr(int[] process_times, int[] arrival_times, int time_slice){
        bursts = process_times;
        arrivals = arrival_times;
        quantum = time_slice;

        processIDUniques = IntStream.range(0, arrivals.length).boxed()
        .sorted(Comparator.comparingInt(i -> arrival_times[i]))
        .mapToInt(Integer::intValue)
        .toArray();

        int[] sorted_bursts = bursts.clone();
        int[] sorted_arrivals = arrivals.clone();

        ArrayList<Integer> ends = new ArrayList<Integer>();
        ArrayList<Integer> processes = new ArrayList<Integer>();
        ArrayList<Integer> starts = new ArrayList<>(); 
        ArrayList<Integer> firstExecs = new ArrayList<>();

        completions = new int[arrivals.length];

        int burstsleft = Arrays.stream(sorted_bursts).sum();
        int time = 0;

        while(burstsleft > 0){
            int arrived = 0;
            for(int index = 0; index < sorted_bursts.length; index++){
                if(sorted_arrivals[index] <= time && sorted_bursts[index] > 0){

                    processes.add(index);
                    starts.add(time);
                    if(sorted_bursts[index] == bursts[index]){
                        firstExecs.add(time);
                    }

                    if(sorted_bursts[index] > quantum){
                        time += quantum;
                        sorted_bursts[index] -= quantum;
                    }else{
                        time += sorted_bursts[index];
                        sorted_bursts[index] = 0;
                        completions[index] = time;
                    }

                    ends.add(time);
                    arrived += 1;
                }
            }
            
            if(arrived == 0){
                time++; 
            }
            burstsleft = Arrays.stream(sorted_bursts).sum();
        }
        startTimes = starts.stream().mapToInt(Integer::intValue).toArray();
        endTimes = ends.stream().mapToInt(Integer::intValue).toArray();

        turnaroundTimes = IntStream.range(0, arrivals.length).boxed()
            .mapToInt(i -> completions[i]-arrivals[i])
            .toArray();
        waitingTimes = IntStream.range(0, arrivals.length).boxed()
            .mapToInt(i->turnaroundTimes[i]-bursts[i])
            .toArray();
        turnaroundTimes = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i -> turnaroundTimes[i])
            .toArray();
        completions = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i -> completions[i])
            .toArray();
        waitingTimes = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i -> waitingTimes[i])
            .toArray();
        bursts = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i -> bursts[i])
            .toArray();
        arrivals = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i->arrivals[i])
            .toArray();
        averageWaitingTime = IntStream.range(0,waitingTimes.length).boxed()
            .mapToInt(i -> waitingTimes[i]).average().orElse(0.0);
        averageTurnaroundTime = IntStream.range(0,turnaroundTimes.length).boxed()
            .mapToInt(i -> turnaroundTimes[i]).average().orElse(0.0);

        processIDs = processes.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] getBursts() {
        return bursts;
    }

    public static int[] getEndTimes() {
        return endTimes;
    }

    public int[] getCompletions() {
        return completions;
    }

    public int[] getArrivals() {
        return arrivals;
    }

    public static int[] getProcessIDs() {
        return processIDs;
    }

    public int[] getProcessIDUniques() {
        return processIDUniques;
    }

    public static int[] getStartTimes() {
        return startTimes;
    }

    public int[] getTurnaroundTimes() {
        return turnaroundTimes;
    }

    public int[] getWaitingTimes() {
        return waitingTimes;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }

    public String toString(){
        int[] ends = getEndTimes();
        int[] starts = getStartTimes();
        int[] pids = getProcessIDs();
        int[] pidUniques = this.getProcessIDUniques();
        int[] waits = this.getWaitingTimes();
        int[] turns = this.getTurnaroundTimes();
        int[] completes = this.getCompletions();
        final String result = IntStream.range(0, ends.length).boxed()
        .map(i -> "Process " + (pids[i]+1) + " S: " + starts[i] + " E: " + ends[i] + "\n")
        .reduce("",String::concat);
        final String result2 = IntStream.range(0, pidUniques.length).boxed()
        .map(i-> "Process " + (pidUniques[i]+1) + " W: " + waits[i] + " T: " + turns[i] + " C: " + completes[i] + "\n")
        .reduce(result, String::concat);
        return result2;
    }
}