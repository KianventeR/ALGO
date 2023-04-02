import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class fcfs {

    private ArrayList<Process> processes;
    private ArrayList<Integer> arrivalTime;
    private ArrayList<Integer> burstTime;
    private int[] waitingTimes;
    private int[] turnaroundTimes;
    private int[] startTimes;
    private int[] endTimes;
    private int[] processIDs;

    public double averageWaitingTime;
    public double averageTurnaroundTime;

    public fcfs(ArrayList<Integer> arrivalArray, ArrayList<Integer> burstArray) {
        arrivalTime = arrivalArray;
        burstTime = burstArray;
        processes = new ArrayList<>();
        for (int i = 0; i < burstArray.size(); i++) {
            processes.add(new Process(i + 1, burstArray.get(i), arrivalArray.get(i)));
        }
        // sort by arrival time
        processes = IntStream.range(0, processes.size()).boxed()
        .sorted(Comparator.comparingInt(i -> processes.get(i).getArrivalTime()))
        .map(i-> processes.get(i))
        .collect(Collectors.toCollection(ArrayList::new));

        simulate();
        // sorted bursts
        burstTime = IntStream.range(0, arrivalTime.size()).boxed()
            .map(i -> processes.get(i).getBurstTime())
            .collect(Collectors.toCollection(ArrayList::new));
        // sorted arrival
        arrivalTime = IntStream.range(0, arrivalTime.size()).boxed()
            .map(i -> processes.get(i).getArrivalTime())
            .collect(Collectors.toCollection(ArrayList::new));
        // sorted process ids
        processIDs = IntStream.range(0, arrivalTime.size()).boxed()
            .mapToInt(i -> processes.get(i).getId())
            .toArray();
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
        averageWaitingTime = waitingTime.stream().mapToInt(i -> i).average().orElse(0.0);
        averageTurnaroundTime = turnaroundTime.stream().mapToInt(i -> i).average().orElse(0.0);

        waitingTimes = waitingTime.stream().mapToInt(Integer::intValue).toArray();
        turnaroundTimes = turnaroundTime.stream().mapToInt(Integer::intValue).toArray();
        startTimes = starts.stream().mapToInt(Integer::intValue).toArray();
        endTimes = ends.stream().mapToInt(Integer::intValue).toArray();
        processIDs = pids.stream().mapToInt(Integer::intValue).toArray();
        String result = "Average Waiting Time: " + averageWaitingTime + "\nAverage Turnaround Time: " + averageTurnaroundTime + "\nGantt Chart: " + ganttChart;
        return result;
    }

    public ArrayList<Integer> getArrivalTime() {
        return arrivalTime;
    }

    public ArrayList<Integer> getBurstTime() {
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