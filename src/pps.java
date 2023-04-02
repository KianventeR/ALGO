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
    private static int[] completionTimes;
    private int[] waitingTimes;
    private int[] turnaroundTimes;
    private float averageWaitingTime;
    private float averageTurnaroundTime;
    private List<String> ganttChart;

    public pps(int[] pids, int[] arrivalTimes, int[] burstTimes, int[] priorities) {
        processIDs = pids;
        this.arrivalTimes = arrivalTimes;
        this.burstTimes = burstTimes;
        this.priorities = priorities;
        startTimes = new int[pids.length];
        completionTimes = new int[pids.length];
        this.waitingTimes = new int[pids.length];
        this.turnaroundTimes = new int[pids.length];
        this.averageWaitingTime = 0;
        this.averageTurnaroundTime = 0;
        this.ganttChart = new ArrayList<>();
        schedule();
    }

    public void schedule() {
        int[] remainingBurstTimes = Arrays.copyOf(burstTimes, burstTimes.length);
        ArrayList<Integer> highPrioProcesses = new ArrayList<>();
        int currentTime = 0;
        int completed = 0;
        boolean[] isCompleted = new boolean[processIDs.length];
        while (completed != processIDs.length) {
            int highestPriorityProcess = -1;
            int highestPriority = Integer.MAX_VALUE;
            for (int i = 0; i < processIDs.length; i++) {
                if (arrivalTimes[i] <= currentTime && !isCompleted[i] && priorities[i] < highestPriority && remainingBurstTimes[i] > 0) {
                    highestPriorityProcess = i;
                    highestPriority = priorities[i];
                }
            }
            
            if (highestPriorityProcess == -1) {
                currentTime++;
            } else {
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
                currentTime++;
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
        completionTimes = highPrioProcesses.stream().mapToInt(i-> completionTimes[i]).toArray();
        startTimes = highPrioProcesses.stream().mapToInt(i-> startTimes[i]).toArray();

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
}
    