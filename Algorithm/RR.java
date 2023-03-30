package Algorithm;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class RR {
    private int[] bursts;
    private int[] arrivals;
    private int[] processIDs;
    private int[] startTimes;
    private int[] endTimes;
    private int[] waitingTimes;
    private int[] turnaroundTimes;
    private int quantum;
    private double averageWaitingTime;
    private double averageTurnaroundTime;

    public RR(int[] process_times, int[] arrival_times, int time_slice){
        // save the parameters for retrieval 
        bursts = process_times;
        arrivals = arrival_times;
        quantum = time_slice;

        // sort burst array by arrival time
        final int[] sorted_bursts = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i -> bursts[i])
            .toArray();

        // copy the sorted bursts into the original burst    
        bursts = sorted_bursts.clone();

        final int[] sorted_arrivals = arrivals.clone();
        Arrays.sort(sorted_arrivals);

        ArrayList<Integer> ends = new ArrayList<Integer>();
        ArrayList<Integer> processes = new ArrayList<Integer>();
        ArrayList<Integer> starts = new ArrayList<>(); 
        int[] completions = new int[arrivals.length];

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
        // sort turnaround by arrival
        turnaroundTimes = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i -> turnaroundTimes[i])
            .toArray();
        
        arrivals = sorted_arrivals;
        waitingTimes = IntStream.range(0, arrivals.length).boxed()
            .mapToInt(i->turnaroundTimes[i]-sorted_bursts[i])
            .toArray();
        
        averageWaitingTime = IntStream.range(0,waitingTimes.length).boxed()
            .mapToInt(i -> waitingTimes[i]).average().orElse(0.0);
        averageTurnaroundTime = IntStream.range(0,turnaroundTimes.length).boxed()
            .mapToInt(i -> turnaroundTimes[i]).average().orElse(0.0);

        processIDs = processes.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] getEndTimes() {
        return endTimes;
    }

    public int[] getArrivals() {
        return arrivals;
    }

    public int[] getProcessIDs() {
        return processIDs;
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

    public String toString(){

        int[] ends = this.getEndTimes();
        int[] starts = this.getStartTimes();
        int[] pids = this.getProcessIDs();
        int[] waits = this.getWaitingTimes();
        int[] turns = this.getTurnaroundTimes();
        final String result = IntStream.range(0, ends.length).boxed()
        .map(i -> "Process " + (pids[i]+1) + " S: " + starts[i] + " E: " + ends[i] + "\n")
        .reduce("",String::concat);
        final String result2 = IntStream.range(0, waits.length).boxed()
        .map(i-> "Process" + (i+1) + " W: " + waits[i] + " T: " + turns[i] + "\n")
        .reduce(result, String::concat);
        return result2;
    }

    public static void main(String[] args){

        int[] bursts = {1,2,3};
        int[] arrivals = {1,1,1};
        int time_slice = 1;
        RR test_rr = new RR(bursts, arrivals, time_slice);

        System.out.println(test_rr.toString());
        
    }
}
