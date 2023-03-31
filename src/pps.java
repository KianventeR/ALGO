import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class pps {
    private int[] processIDs;
    private int[] processIDUniques;
    private int[] arrivalTimes;
    private int[] burstTimes;
    private int[] priorities;
    private int[] startTimes;
    private int[] completionTimes;
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
        averageWaitingTime /= processIDs.length;
        averageTurnaroundTime /= processIDs.length;
        processIDs = highPrioProcesses.stream().mapToInt(Integer::intValue).toArray(); 
    }

    public int[] getProcessIds() {
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

    public int[] getCompletionTimes() {
        return completionTimes;
    }

    public int[] getStartTimes() {
        return startTimes;
    }

    public int[] getWaitingTimes() {
        return waitingTimes;
    }

    public int[] getTurnaroundTimes() {
        return turnaroundTimes;
    }

    public float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public float getAverageTurnaroundTime() {
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
    /*public static void main(String[] args) {
        int[] arrivals = {1,2,3,4};
        int[] bursts = {1,2,3,4};
        int[] priorities = {4,3,2,1};
        int[] pids = IntStream.range(0,arrivals.length).toArray();
        PPS scheduler = new PPS(pids,arrivals,bursts,priorities);

        String ganttChart = scheduler.getGanttChart();
        System.out.println(ganttChart);
        for(int num : scheduler.getWaitingTimes()){
            System.out.print(num + " ");
        }
        System.out.println();
        for(int num : scheduler.getTurnaroundTimes()){
            System.out.print(num+ " ");
        }
        System.out.println();
        System.out.println(scheduler.getAverageWaitingTime());
        System.out.println(scheduler.getAverageTurnaroundTime());
    }*/
}
    