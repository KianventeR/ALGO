package Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NPPS {
    private static class Process {
        int processID;
        int priority;
        int burstTime;
        int arrivalTime;
        int completionTime;
        int waitingTime;
        
        public Process(int processID, int priority, int burstTime, int arrivalTime) {
            this.processID = processID;
            this.priority = priority;
            this.burstTime = burstTime;
            this.arrivalTime = arrivalTime;
        }
    }
    
    public static void main(String[] args) {
        // Create a list of processes
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 2, 10, 0));
        processes.add(new Process(2, 1, 5, 1));
        processes.add(new Process(3, 3, 8, 2));
        processes.add(new Process(4, 4, 3, 3));
        
        // Sort the list of processes by priority
        processes.sort(Comparator.comparingInt(p -> p.priority));
        
        // Create a priority queue for processes
        PriorityQueue<Process> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.arrivalTime));
        
        // Add processes to the priority queue
        for (Process process : processes) {
            priorityQueue.add(process);
        }
        
        // Process the processes in the priority queue
        int currentTime = 0;
        int totalWaitingTime = 0;
        for (Process process : priorityQueue) {
            process.waitingTime = currentTime - process.arrivalTime;
            currentTime += process.burstTime;
            process.completionTime = currentTime;
            totalWaitingTime += process.waitingTime;
            System.out.println("Processing process ID " + process.processID + " with priority " + process.priority 
                                + ", burst time " + process.burstTime + ", arrival time " + process.arrivalTime 
                                + ", completion time " + process.completionTime + ", waiting time " + process.waitingTime + ".");
        }
        double averageWaitingTime = (double) totalWaitingTime / processes.size();
        System.out.println("Average waiting time: " + averageWaitingTime);
    }
}
