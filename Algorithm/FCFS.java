package Algorithm;
import java.util.ArrayList;

public class FCFS {

    private ArrayList<Process> processes;
    private int[] arrivalTime;
    private int[] burstTime;
    private int[] waitingTimes;
    private int[] turnaroundTimes;
    private int[] startTimes;
    private int[] endTimes;
    private int[] processIDs;

    public FCFS(int[] arrivalTime, int[] burstTime) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.processes = new ArrayList<>();
        for (int i = 0; i < arrivalTime.length; i++) {
            processes.add(new Process(i + 1, arrivalTime[i], burstTime[i]));
        }
    }

    public String simulate() {
        ArrayList<Integer> waitingTime = new ArrayList<>();
        ArrayList<Integer> turnaroundTime = new ArrayList<>();
        ArrayList<Integer> starts = new ArrayList<>();
        ArrayList<Integer> ends = new ArrayList<>();
        ArrayList<Integer> pids = new ArrayList<>();

        int currentTime = 0;
        String ganttChart = "";
        for (Process p : processes) {
            if (p.getArrivalTime() > currentTime) {
                currentTime = p.getArrivalTime();
            }
            p.setStartTime(currentTime);
            ganttChart += "P" + p.getId() + " ";
            currentTime += p.getBurstTime();
            p.setEndTime(currentTime);

            pids.add(p.getId());
            starts.add(p.getStartTime());
            ends.add(p.getEndTime());
            waitingTime.add(p.getStartTime() - p.getArrivalTime());
            turnaroundTime.add(p.getEndTime() - p.getArrivalTime());
        }
        double averageWaitingTime = waitingTime.stream().mapToInt(i -> i).average().orElse(0.0);
        double averageTurnaroundTime = turnaroundTime.stream().mapToInt(i -> i).average().orElse(0.0);

        waitingTimes = waitingTime.stream().mapToInt(Integer::intValue).toArray();
        turnaroundTimes = turnaroundTime.stream().mapToInt(Integer::intValue).toArray();
        startTimes = starts.stream().mapToInt(Integer::intValue).toArray();
        endTimes = ends.stream().mapToInt(Integer::intValue).toArray();
        processIDs = pids.stream().mapToInt(Integer::intValue).toArray();
        String result = "Average Waiting Time: " + averageWaitingTime + "\nAverage Turnaround Time: " + averageTurnaroundTime + "\nGantt Chart: " + ganttChart;
        return result;
    }

    public int[] getArrivalTime() {
        return arrivalTime;
    }

    public int[] getBurstTime() {
        return burstTime;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public int[] getWaitingTimes() {
        return waitingTimes;
    }

    public int[] getTurnaroundTimes() {
        return turnaroundTimes;
    }

    public int[] getStartTimes() {
        return startTimes;
    }
    
    public int[] getEndTimes() {
        return endTimes;
    }

    public int[] getProcessIDs() {
        return processIDs;
    }
    public static void main(String[] args) {
        int[] arrivalTime = {1,3,2};
        int[] burstTime = {1,2,3};
        FCFS fifo = new FCFS(arrivalTime, burstTime);
        String result = fifo.simulate();
        System.out.println("Waiting Time");
        for(int num : fifo.getWaitingTimes()){
            System.out.print(num + " ");
        }
        System.out.println("\nTurnaround Time");
        for(int num : fifo.getTurnaroundTimes()){
            System.out.print(num+ " ");
        }
        System.out.println("\nStarting Time");
        for(int num : fifo.getStartTimes()){
            System.out.print(num + " ");
        }
        System.out.println("\nEnding Time");
        for(int num : fifo.getEndTimes()){
            System.out.print(num+ " ");
        }
        System.out.println();
        System.out.println(result);
    }
}

class Process {
    private int id;
    private int arrivalTime;
    private int burstTime;
    private int startTime;
    private int endTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
