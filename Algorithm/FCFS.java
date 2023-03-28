package Algorithm;

import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class FCFS{
    // this class would return the 
    // the names of the processes
    // and the end time usage of each process
    
    // FCFS uses a queue to run each process
    // but the processes must be sorted by arrival times
    private int[] bursts;
    private int[] arrivals;
    private int[] processIDs;
    private int[] endTimes;
    private int[] waitingTimes;

    public FCFS(int[] process_times, int[] arrival_times){
        bursts = process_times;
        arrivals = arrival_times;

        // sort burst array by arrival time
        final int[] sorted_bursts = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i -> bursts[i])
            .toArray();

        final int[] sorted_arrivals = arrivals.clone();
        Arrays.sort(sorted_arrivals);

        final int[] sorted_pids = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(Integer::intValue)
            .toArray();
        // print the process, evaluate turnaround
        // by equation : turnaround = burst - arrival + waiting
        int time = 0;
        ArrayList<Integer> turnarounds = new ArrayList<Integer>(); 
        ArrayList<Integer> waitings = new ArrayList<Integer>();

        for(int i = 0; i < sorted_bursts.length; i++){
            if(time >= sorted_arrivals[i]){
                waitings.add(time-sorted_arrivals[i]);
            }else{
                waitings.add(sorted_arrivals[i]-time);
            }
            if(time < sorted_arrivals[i]){
                turnarounds.add(sorted_bursts[i]+sorted_arrivals[i]);
                time = sorted_bursts[i]+sorted_arrivals[i];
            }
            else{
                turnarounds.add(sorted_bursts[i]+time);
                time += sorted_bursts[i];
            }
        }
        waitingTimes = waitings.stream().mapToInt(Integer::intValue).toArray();
        processIDs = sorted_pids;
        arrivals = sorted_arrivals;
        endTimes = turnarounds.stream().mapToInt(Integer::intValue).toArray();
    }

    // same for average turnaround time
    public int[] getEndTimes(){
        return endTimes;
    }

    public int[] getArrivals() {
        return arrivals;
    }

    public int[] getProcessIDs() {
        return processIDs;
    }
    // same for average waiting time
    public int[] getWaitingTimes() {
        return waitingTimes;
    }

    // Character User Interface for testing in console
    public String toString(){
        final int[] turns = this.getEndTimes();
        final int[] pids = this.getProcessIDs();
        final int[] starts = this.getArrivals();
        final int[] waits = this.getWaitingTimes();
        final String result = IntStream.range(0, turns.length).boxed()
            .map(i -> "Process " + (pids[i]+1) + ": start->" + starts[i] + " ends->" + turns[i] + " waits->" + waits[i] +"\n")
            .reduce("",String::concat);
        return result;
    }

    public static void main(String[] args){

        int[] bursts = {10,20,30,40,50};
        int[] arrivals = {1,12,13,14,15};
        FCFS test_fcfs = new FCFS(bursts, arrivals);

        System.out.println(test_fcfs.toString());
        
        // Example use for IntStream
        //int[] strings = {"string1", "string2", "string3"};
        //int[] boosts = {40, 32, 34};

        //String[] sorted = IntStream.range(0, boosts.length).boxed()
         //   .sorted(Comparator.comparingInt(i -> boosts[i]))
         //   .map(i -> strings[i])
         //   .toArray(String[]::new);

        //for(int i = 0; i < sorted.length; i++){
        //}
    }

}