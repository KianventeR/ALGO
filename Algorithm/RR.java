package Algorithm;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class RR {
    private int[] bursts;
    private int[] arrivals;
    private int[] processIDs;
    private int[] endTimes;
    private int quantum;

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
        
        final int[] sorted_arrivals = arrivals.clone();
        Arrays.sort(sorted_arrivals);

        ArrayList<Integer> turnarounds = new ArrayList<Integer>();
        ArrayList<Integer> processes = new ArrayList<Integer>();

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

                    if(sorted_bursts[index] > quantum){
                        time += quantum;
                        sorted_bursts[index] -= quantum;
                    }else{
                        time += sorted_bursts[index];
                        sorted_bursts[index] = 0;
                    }

                    turnarounds.add(time);
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
        endTimes = turnarounds.stream().mapToInt(Integer::intValue).toArray();
        arrivals = sorted_arrivals;
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

    public String toString(){

        int[] turns = this.getEndTimes();
        int[] pids = this.getProcessIDs();
        final String result = IntStream.range(0, turns.length).boxed()
        .map(i -> "Process " + pids[i] + ": " + turns[i] +"\n")
        .reduce("",String::concat);
        return result;
    }

    public static void main(String[] args){

        int[] bursts = {10,20,30,40,50};
        int[] arrivals = {1,2,3,4,5};
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
