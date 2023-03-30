package Algorithm;

public class SRTF {
    private int[] arrivalTime;
    private int[] burstTime;
    private int numProcesses;
    private int[] turnaroundTime;
    private int[] waitingTime;
    private int[] remainingTime;
    private int[] completionTime;
    
    public SRTF(int[] arrivalTime, int[] burstTime, int numProcesses) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.numProcesses = numProcesses;
        this.turnaroundTime = new int[numProcesses];
        this.waitingTime = new int[numProcesses];
        this.remainingTime = new int[numProcesses];
        this.completionTime = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            this.remainingTime[i] = burstTime[i];
        }
    }
    
    public int[] getArrivalTime() {
        return arrivalTime;
    }
    
    public int[] getBurstTime() {
        return burstTime;
    }
    
    public int[] getTurnaroundTime() {
        return turnaroundTime;
    }
    
    public int[] getWaitingTime() {
        return waitingTime;
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
                if (arrivalTime[i] <= currentTime && completed[i] == 0 && remainingTime[i] < minRemainingTime) {
                    minRemainingTime = remainingTime[i];
                    nextProcess = i;
                }
            }
            if (nextProcess == -1) {
                sb.append("-");
                currentTime++;
            } else {
                sb.append("P" + nextProcess);
                remainingTime[nextProcess]--; // Decrement remaining time of current process
                if (remainingTime[nextProcess] == 0) { // If current process has completed
                    currentTime++;
                    completionTime[nextProcess] = currentTime;
                    completed[nextProcess] = 1;
                    completedCount++;
                }
            }
        }
        return sb.toString();
    }
    
    public void calculate() {
        int[] turnaroundTime = new int[numProcesses];
        int[] waitingTime = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            turnaroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
        }
        this.turnaroundTime = turnaroundTime;
        this.waitingTime = waitingTime;
    }

    public static void main(String[] args){
        int[] bursts = {1,4,2};
        int[] arrivals = {1,3,3};
        SRTF test_srtf = new SRTF(bursts, arrivals, bursts.length);

        test_srtf.calculate();
        System.out.println(test_srtf.getGanttChart());
    }
}

