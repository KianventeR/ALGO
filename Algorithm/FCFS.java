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

    public FCFS(int[] process_times, int[] arrival_times){
        bursts = process_times;
        arrivals = arrival_times;
    }

    public int[] getTurnarounds(){
        // sort burst array by arrival time
        final int[] sorted_bursts = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i -> bursts[i])
            .toArray();
        final int[] sorted_arrivals = arrivals.clone();

        Arrays.sort(sorted_arrivals);
        // print the process, evaluate turnaround
        // by equation : turnaround = burst - arrival + waiting
        int waiting = 0;
        ArrayList<Integer> turnarounds = new ArrayList<Integer>(); 
        for(int i = 0; i < sorted_bursts.length; i++){
            if(waiting < sorted_arrivals[i]){
                turnarounds.add(sorted_bursts[i]+sorted_arrivals[i]);
                waiting = sorted_bursts[i]+sorted_arrivals[i];
            }
            else{
                turnarounds.add(sorted_bursts[i]+waiting);
                waiting += sorted_bursts[i];
            }
        }
        return turnarounds.stream().mapToInt(Integer::intValue).toArray();
    }

    // Character User Interface for testing in console
    public String toString(){
        final int[] turns = this.getTurnarounds();
        final String result = IntStream.range(0, turns.length).boxed()
            .map(i -> "Process " + (i+1) + ": " + turns[i] +"\n")
            .reduce("",String::concat);
        return result;
    }

    public static void main(String[] args){

        int[] bursts = {10,20,30,40,50};
        int[] arrivals = {1,12,13,14,15};
        FCFS test_fcfs = new FCFS(bursts, arrivals);

        System.out.println(test_fcfs.toString());
        
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