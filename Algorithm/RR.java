package Algorithm;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class RR {
    private int[] bursts;
    private int[] arrivals;
    private int quantum;

    public RR(int[] process_times, int[] arrival_times, int time_slice){
        bursts = process_times;
        arrivals = arrival_times;
        quantum = time_slice;
    }

    public Object[] getTurnarounds(){
        // sort burst array by arrival time
        final int[] sorted_bursts = IntStream.range(0, arrivals.length).boxed()
            .sorted(Comparator.comparingInt(i -> arrivals[i]))
            .mapToInt(i -> bursts[i])
            .toArray();
        final int[] sorted_arrivals = arrivals.clone();

        Arrays.sort(sorted_arrivals);
        // print the process, evaluate turnaround
        // take note of the remaining bursts
        int[] remaining_bursts = bursts.clone();

        ArrayList<Integer> turnarounds = new ArrayList<Integer>();
        ArrayList<Integer> processes = new ArrayList<Integer>();

        int burstsleft = Arrays.stream(remaining_bursts).sum();
        int index = 0; int time = 0;
        while(burstsleft > 0){
            // if process arrived already and has still burst left, only then execute 
            if(sorted_arrivals[index] <= time && remaining_bursts[index] > 0){
                processes.add(index);
                if(remaining_bursts[index] > quantum){
                    time += quantum;
                    remaining_bursts[index] -= quantum;
                }else{
                    time += remaining_bursts[index];
                    remaining_bursts[index] = 0;
                }
                turnarounds.add(time);
            }
            else{
                // let time pass 
                time++;
            }
            // alternate through all processes
            index = (index + 1) % sorted_bursts.length;
            // check if there are any processes to run
            burstsleft = Arrays.stream(remaining_bursts).sum();
            System.out.println("Processing... "+burstsleft);
        }

        return new Object[]{processes.toArray(), turnarounds.toArray()};
    }

    public String toString(){
        final int PROCESS = 0;
        final int TURNAROUND = 1;
        Object[] turns = this.getTurnarounds();
        int[] processes = (int[]) turns[PROCESS];
        int[] turnarounds = (int[]) turns[TURNAROUND];
        final String result = IntStream.range(0, turns.length).boxed()
        .map(i -> "Process " + processes[i] + ": " + turnarounds[i] +"\n")
        .reduce("",String::concat);
        return result;
    }

    public static void main(String[] args){

        int[] bursts = {10,20,30,40,50};
        int[] arrivals = {1,12,13,14,15};
        int time_slice = 10;
        RR test_rr = new RR(bursts, arrivals, time_slice);

        System.out.println(test_rr.toString());
        
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
