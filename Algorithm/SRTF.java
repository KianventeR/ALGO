package Algorithm;
import java.util.*;

public class SRTF {

    static class Process implements Comparable<Process> {
        int id;
        int arrivalTime;
        int burstTime;
        int remainingTime;

        public Process(int id, int arrivalTime, int burstTime) {
            this.id = id;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
        }

        public int compareTo(Process p) {
            return remainingTime - p.remainingTime;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, t = 0, completed = 0;
        double avgTurnaroundTime = 0, avgWaitingTime = 0;
        PriorityQueue<Process> queue = new PriorityQueue<>();

        System.out.print("Enter the number of processes: ");
        n = sc.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter arrival time and burst time for process %d: ", i + 1);
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        Arrays.sort(processes, (p1, p2) -> p1.arrivalTime - p2.arrivalTime);

        System.out.println("Gantt Chart:");

        while (completed < n) {
            while (!queue.isEmpty() && queue.peek().remainingTime == 0) {
                Process p = queue.poll();
                System.out.printf("P%d(%d-%d) ", p.id, t - p.remainingTime, t);
                avgTurnaroundTime += t - p.arrivalTime;
                avgWaitingTime += t - p.arrivalTime - p.burstTime;
                completed++;
            }

            for (int i = 0; i < n; i++) {
                if (processes[i].arrivalTime <= t && processes[i].remainingTime > 0) {
                    queue.offer(processes[i]);
                }
            }

            if (queue.isEmpty()) {
                System.out.printf("idle(%d-%d) ", t, t + 1);
                t++;
                continue;
            }

            Process p = queue.poll();
            System.out.printf("P%d(%d-%d) ", p.id, t, t + 1);
            p.remainingTime--;
            t++;
            queue.offer(p);
        }

        System.out.println();
        System.out.printf("Average turnaround time: %.2f\n", avgTurnaroundTime / n);
        System.out.printf("Average waiting time: %.2f\n", avgWaitingTime / n);
    }
}
