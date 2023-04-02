import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class pps {
    private static int[] processIDs;
    private int[] processIDUniques;
    private int[] arrivalTimes;
    private int[] burstTimes;
    private int[] priorities;
    private static int[] startTimes;
    private int[] endTimes;
    private static int[] completionTimes;
    private int[] waitingTimes;
    private int[] turnaroundTimes;
    private float averageWaitingTime;
    private float averageTurnaroundTime;
    private List<String> ganttChart;

    public pps(int[] pids, int[] arrivalTimes, int[] burstTimes, int[] priorities) {
        this.processIDs = pids;
        this.arrivalTimes = arrivalTimes;
        this.burstTimes = burstTimes;
        this.priorities = priorities;
        this.startTimes = new int[pids.length];
        this.completionTimes = new int[pids.length];
        this.waitingTimes = new int[pids.length];
        this.turnaroundTimes = new int[pids.length];
        this.averageWaitingTime = 0;
        this.averageTurnaroundTime = 0;
        this.ganttChart = new ArrayList<>();
        schedule();
    }

    public void schedule() {
        int[] remainingBurstTimes = Arrays.copyOf(burstTimes, burstTimes.length);
        int currentTime = 0;
        int completed = 0;
        boolean[] isCompleted = new boolean[processIDs.length];
        
        ArrayList<Integer> highPrioProcesses = new ArrayList<>();
        ArrayList<Integer> firstExecs = new ArrayList<>();
        ArrayList<Integer> lastExecs = new ArrayList<>();
        
        while (completed != processIDs.length) {
            int highestPriorityProcess = -1;
            int prevProcess = -1;
            int highestPriority = Integer.MAX_VALUE;
            for (int i = 0; i < processIDs.length; i++) {
                if (arrivalTimes[i] <= currentTime && !isCompleted[i] && priorities[i] < highestPriority && remainingBurstTimes[i] > 0) {
                    highestPriorityProcess = i;
                    highestPriority = priorities[i];
                }
            }
            // save the first changes of a process
            if(prevProcess != highestPriorityProcess){
                firstExecs.add(currentTime);
                // if some time passed already, only then add
                if(currentTime > 0){
                    lastExecs.add(currentTime+1);
                }
                prevProcess = highestPriorityProcess;
            }
            // let time pass
            currentTime++;

            if (highestPriorityProcess > -1) {
                remainingBurstTimes[highestPriorityProcess]--;
                if (remainingBurstTimes[highestPriorityProcess] == 0) {
                    completed++;
                    isCompleted[highestPriorityProcess] = true;
                    completionTimes[highestPriorityProcess] = currentTime + 1;
                    turnaroundTimes[highestPriorityProcess] = completionTimes[highestPriorityProcess] - arrivalTimes[highestPriorityProcess];
                    waitingTimes[highestPriorityProcess] = turnaroundTimes[highestPriorityProcess] - burstTimes[highestPriorityProcess];
                    startTimes[highestPriorityProcess] = waitingTimes[highestPriorityProcess] + arrivalTimes[highestPriorityProcess];
                    averageWaitingTime += waitingTimes[highestPriorityProcess];
                    averageTurnaroundTime += turnaroundTimes[highestPriorityProcess];
                }
                ganttChart.add(String.format("| P%d ", processIDs[highestPriorityProcess]));
                // add the new high prio process
                highPrioProcesses.add(highestPriorityProcess);
            }
        }
        // process table variables
        averageWaitingTime /= processIDs.length;
        averageTurnaroundTime /= processIDs.length;
        processIDUniques = IntStream.range(0, processIDs.length).boxed()
        .sorted(Comparator.comparingInt(i -> arrivalTimes[i]))
        .mapToInt(i-> processIDs[i]).toArray();
        waitingTimes = IntStream.range(0, processIDs.length).boxed()
        .sorted(Comparator.comparingInt(i -> arrivalTimes[i]))
        .mapToInt(i-> waitingTimes[i]).toArray();
        turnaroundTimes = IntStream.range(0, processIDs.length).boxed()
        .sorted(Comparator.comparingInt(i -> arrivalTimes[i]))
        .mapToInt(i-> turnaroundTimes[i]).toArray();
        priorities = IntStream.range(0, processIDs.length).boxed()
        .sorted(Comparator.comparingInt(i -> arrivalTimes[i]))
        .mapToInt(i-> priorities[i]).toArray();
        burstTimes = IntStream.range(0, processIDs.length).boxed()
        .sorted(Comparator.comparingInt(i -> arrivalTimes[i]))
        .mapToInt(i-> burstTimes[i]).toArray();
        arrivalTimes = IntStream.range(0, processIDs.length).boxed()
        .sorted(Comparator.comparingInt(i -> arrivalTimes[i]))
        .mapToInt(i-> arrivalTimes[i]).toArray();

        // gantt chart variables
        processIDs = highPrioProcesses.stream().mapToInt(Integer::intValue).toArray();
        startTimes = firstExecs.stream().mapToInt(Integer::intValue).toArray();
        endTimes = lastExecs.stream().mapToInt(Integer::intValue).toArray();

        ArrayList<Integer> pids = new ArrayList<>();
        firstExecs = new ArrayList<>();
        lastExecs = new ArrayList<>();

        // compaction of continuous process ids, start times, and end times
        int prevProcess = -1;
        int nextProcess = -1;
        for(int i = 0; i < startTimes.length; i++){
            nextProcess = processIDs[i];
            int index = pids.size() - 1;
            if(prevProcess == nextProcess && index > -1){
                // dont add the recent process and start time
                // remove the previous end time
                lastExecs.remove(index);
                // add the recent end time
                lastExecs.add(endTimes[i]);
            }else{
                // add three of them 
                pids.add(processIDs[i]);
                firstExecs.add(startTimes[i]);
                lastExecs.add(endTimes[i]);
            }
            prevProcess = nextProcess;
        }

        // gantt chart variables
        processIDs = pids.stream().mapToInt(Integer::intValue).toArray();
        startTimes = firstExecs.stream().mapToInt(Integer::intValue).toArray();
        endTimes = lastExecs.stream().mapToInt(Integer::intValue).toArray();

        completionTimes = highPrioProcesses.stream().mapToInt(i-> completionTimes[i]).toArray();
        

    }

    public static int[] getProcessIDs() {
        return processIDs;
    }

    public int[] getProcessIDUniques(){
        return processIDUniques;
    }

    public int[] getArrivalTimes() {
        return arrivalTimes;
    }

    public int[] getBurstTimes() {
        return burstTimes;
    }

    public int[] getPriorities() {
        return priorities;
    }

    public static int[] getCompletionTimes() {
        return completionTimes;
    }

    public static int[] getStartTimes() {
        return startTimes;
    }

    public int[] getEndTimes() {
        return endTimes;
    }

    public int[] getWaitingTimes() {
        return waitingTimes;
    }

    public int[] getTurnaroundTimes() {
        return turnaroundTimes;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }

    public String getGanttChart() {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int i = 0; i < ganttChart.size(); i++) {
            sb.append("-");
        }
        sb.append("+\n");
        sb.append("|");
        for (int i = 0; i < ganttChart.size(); i++) {
            sb.append(ganttChart.get(i));
                if(i < ganttChart.size() - 1){
                    if(ganttChart.get(i).equals(ganttChart.get(i+1))){
                        sb.append(" ");
                    }else{
                        sb.append("|");
                    }
                }
        }
        sb.append("|\n");
        sb.append("+");
        for (int i = 0; i < ganttChart.size(); i++) {
            sb.append("-");
        }
        sb.append("+\n");
        sb.append("0");
        for (int i = 0; i < ganttChart.size(); i++) {
            if (ganttChart.get(i).contains("|")) {
                sb.append(String.format("%3d", i+1));
            } else {
                sb.append("   ");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    // public static void main(String[] args) {
    //     int[] arrivals = {2,3,4};
    //     int[] bursts = {2,2,2};
    //     int[] priorities = {3,2,1};
    //     int[] pids = IntStream.range(0,arrivals.length).toArray();
    //     pps scheduler = new pps(pids,arrivals,bursts,priorities);

    //     String ganttChart = scheduler.getGanttChart();
    //     System.out.println(ganttChart);
    //     for(int num : scheduler.getProcessIds()){
    //         System.out.print(num + " ");
    //     }
    //     System.out.println("\nStart Times");
    //     for(int num : scheduler.getStartTimes()){
    //         System.out.print(num + " ");
    //     }
    //     System.out.println("\nEnd Times");
    //     for(int num : scheduler.getEndTimes()){
    //         System.out.print(num + " ");
    //     }
    //     System.out.println("\n--------");

    //     for(int num : scheduler.getWaitingTimes()){
    //         System.out.print(num + " ");
    //     }
    //     System.out.println();
    //     for(int num : scheduler.getTurnaroundTimes()){
    //         System.out.print(num+ " ");
    //     }
    //     System.out.println();
    //     System.out.println(scheduler.getAverageWaitingTime());
    //     System.out.println(scheduler.getAverageTurnaroundTime());
    // }
}
    