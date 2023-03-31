import java.util.stream.IntStream;

public class sjf {
    private int[] processIds;
    private int[] arrivalTime;
    private int[] burstTime;
    private int numProcesses;
    private int[] turnaroundTimes;
    private int[] waitingTimes;
    private int[] remainingTimes;
    private int[] completionTimes;
    private int[] startTimes;
    private double averageTurnaroundTime;
    private double averageWaitingTime;

    
    public sjf(int[] arrivalTime, int[] burstTime, int numProcesses) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.numProcesses = numProcesses;
        this.turnaroundTimes = new int[numProcesses];
        this.waitingTimes = new int[numProcesses];
        this.remainingTimes = new int[numProcesses];
        this.completionTimes = new int[numProcesses];
        this.startTimes = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            this.remainingTimes[i] = burstTime[i];
        }
        this.calculate();
    }
    
    public int[] getArrivalTime() {
        return arrivalTime;
    }
    
    public int[] getBurstTime() {
        return burstTime;
    }
    
    public int[] getTurnaroundTimes() {
        return turnaroundTimes;
    }
    
    public int[] getWaitingTimes() {
        return waitingTimes;
    }

    public int[] getCompletionTimes() {
        return completionTimes;
    }

    public int[] getStartTimes() {
        return startTimes;
    }

    public int[] getProcessIds() {
        return processIds;
    }

    public double getAverageTurnaroundTime() {
        return averageTurnaroundTime;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }
    
    public String getGanttChart() {
        StringBuilder sb = new StringBuilder();
        int currentTime = 0;
        int[] completed = new int[numProcesses];
        int completedCount = 0;
        while (completedCount < numProcesses) {
            int minRemainingTime = Integer.MAX_VALUE;
            int nextProcess = -1;
            for (int i = 0; i < numProcesses; i++) {
                if (arrivalTime[i] <= currentTime && completed[i] == 0 && remainingTimes[i] < minRemainingTime) {
                    minRemainingTime = remainingTimes[i];
                    nextProcess = i;
                }
            }
            if (nextProcess == -1) {
                sb.append("-");
                currentTime++;
            } else {
                sb.append("P" + nextProcess);
                currentTime += remainingTimes[nextProcess];
                remainingTimes[nextProcess] = 0;
                completionTimes[nextProcess] = currentTime;
                completed[nextProcess] = 1;
                completedCount++;
            }
        }
        return sb.toString();
    }
    
    public void calculate() {
        int[] turnaroundTime = new int[numProcesses];
        int[] waitingTime = new int[numProcesses];
        int[] startTime = new int[numProcesses];
        int[] pids = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            pids[i] = i;
            turnaroundTime[i] = completionTimes[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            startTime[i] = waitingTime[i] + arrivalTime[i];
        }
        this.turnaroundTimes = turnaroundTime;
        this.waitingTimes = waitingTime;
        this.startTimes = startTime;
        this.processIds = pids;
        averageWaitingTime = IntStream.range(0,waitingTimes.length).boxed()
            .mapToInt(i -> waitingTimes[i]).average().orElse(0.0);
        averageTurnaroundTime = IntStream.range(0,turnaroundTimes.length).boxed()
            .mapToInt(i -> turnaroundTimes[i]).average().orElse(0.0); 
    }

    /*public static void main(String[] args){
        int[] bursts = {1,4,2};
        int[] arrivals = {1,3,3};
        SJF test_sjf = new SJF(bursts, arrivals, bursts.length);
        System.out.println(test_sjf.getGanttChart());
    }*/
}
