package Algorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;

public class SJF {
    
    public static void main(String[] args) {
        int[] arrivalTime = {0, 2, 4, 5, 7};
        int[] burstTime = {5, 3, 1, 2, 6};
        int n = arrivalTime.length;

        // create a list of processes
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            processes.add(new Process(i+1, arrivalTime[i], burstTime[i]));
        }

        // sort the list by burst time
        processes.sort(Comparator.comparing(Process::getBurstTime));

        // run the processes and calculate waiting time and turnaround time
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            Process p = processes.get(i);
            waitingTime[p.getId()-1] = currentTime - p.getArrivalTime();
            turnaroundTime[p.getId()-1] = waitingTime[p.getId()-1] + p.getBurstTime();
            currentTime += p.getBurstTime();
        }

        // print the results
        System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            Process p = processes.get(i);
            System.out.printf("%d\t%d\t\t%d\t\t%d\t\t%d\n", p.getId(), p.getArrivalTime(), p.getBurstTime(),
                    waitingTime[p.getId()-1], turnaroundTime[p.getId()-1]);
        }

        // calculate and print the average waiting time and turnaround time
        double avgWaitingTime = Arrays.stream(waitingTime).average().getAsDouble();
        double avgTurnaroundTime = Arrays.stream(turnaroundTime).average().getAsDouble();
        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
    }

    static class Process {
        private final int id;
        private final int arrivalTime;
        private final int burstTime;

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
    }
}