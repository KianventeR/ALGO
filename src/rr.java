import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class rr {
    private int[] bursts;
    private int[] arrivals;
    private int[] processIDs;
    private int[] processIDUniques;
    private int[] startTimes;
    private int[] endTimes;
    private int[] waitingTimes;
    private int[] turnaroundTimes;
    private int[] completions;
    private int quantum;
    private double averageWaitingTime;
    private double averageTurnaroundTime;

    public rr(int[] process_times, int[] arrival_times, int time_slice){
        // save the parameters for retrieval 
        bursts = process_times;
        arrivals = arrival_times;
        quantum = time_slice;

        processIDUniques = IntStream.range(0, arrivals.length).boxed()
        .sorted(Comparator.comparingInt(i -> arrival_times[i]))
        .mapToInt(Integer::intValue)
        .toArray();

        // copy the sorted bursts into the original burst    
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
            // check for those who arrived
            int arrived = 0;
            for(int index = 0; index < sorted_bursts.length; index++){
                //System.out.println("Processing... "+time+" | "+sorted_arrivals[index]+" "+sorted_bursts[index]); //for debugging
                // if process arrived already and has still burst left, only then execute process
                if(sorted_arrivals[index] <= time && sorted_bursts[index] > 0){

                    processes.add(index);
                    starts.add(time);
                    if(sorted_bursts[index] == bursts[index]){
                        firstExecs.add(time);
                    }

                    if(sorted_bursts[index] > quantum){
                        // still has remaining time
                        time += quantum;
                        sorted_bursts[index] -= quantum;
                    }else{
                        // completion time
                        time += sorted_bursts[index];
                        sorted_bursts[index] = 0;
                        completions[index] = time;
                    }

                    ends.add(time);
                    arrived += 1;
                }
            }
            
            if(arrived == 0){
                // let time pass 
                time++; 
            }

            // check if there are any processes to run
            burstsleft = Arrays.stream(sorted_bursts).sum();
        }

        // save the end_times, arrivals, and process ids
        startTimes = starts.stream().mapToInt(Integer::intValue).toArray();
        endTimes = ends.stream().mapToInt(Integer::intValue).toArray();

        // generate turnaround values
        turnaroundTimes = IntStream.range(0, arrivals.length).boxed()
            .mapToInt(i -> completions[i]-arrivals[i])
            .toArray();
        // generate waiting times
        waitingTimes = IntStream.range(0, arrivals.length).boxed()
            .mapToInt(i->turnaroundTimes[i]-bursts[i])
            .toArray();

        //sort turnaround by arrival
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

    public int[] getEndTimes() {
        return endTimes;
    }

    public int[] getCompletions() {
        return completions;
    }

    public int[] getArrivals() {
        return arrivals;
    }

    public int[] getProcessIDs() {
        return processIDs;
    }

    public int[] getProcessIDUniques() {
        return processIDUniques;
    }

    public int[] getStartTimes() {
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

        int[] ends = this.getEndTimes();
        int[] starts = this.getStartTimes();
        int[] pids = this.getProcessIDs();
        int[] pidUniques = this.getProcessIDUniques();
        int[] waits = this.getWaitingTimes();
        int[] turns = this.getTurnaroundTimes();
        int[] completes = this.getCompletions();
        final String result = IntStream.range(0, ends.length).boxed()
        .map(i -> "Process " + (pids[i]+1) + " S: " + starts[i] + " E: " + ends[i] + "\n")
        .reduce("",String::concat);
        final String result2 = IntStream.range(0, pidUniques.length).boxed()
        .map(i-> "Process" + (pidUniques[i]+1) + " W: " + waits[i] + " T: " + turns[i] + " C: " + completes[i] + "\n")
        .reduce(result, String::concat);
        return result2;
    }

    public static void main(String[] args){
        int[] bursts = {2,3,1,2,4};
        int[] arrivals = {1,3,2,4,5};
        int time_slice = 1;
        rr test_rr = new rr(bursts, arrivals, time_slice);
        System.out.println(test_rr.toString());
    }
}