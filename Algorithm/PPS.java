package Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PPS {
    private static class Process {
        int processID;
        int priority;
        int burstTime;
        int arrivalTime;
        int completionTime;
        int waitingTime;
        int remainingTime;
        
        public Process(int processID, int priority, int burstTime, int arrivalTime) {
            this.processID = processID;
            this.priority = priority;
            this.burstTime = burstTime;
            this.arrivalTime = arrivalTime;
            this.remainingTime = burstTime;
        }
    }
    
    public static void main(String[] args) {
        // Create a list of processes
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 2, 10, 0));
        processes.add(new Process(2, 1, 5, 1));
        processes.add(new Process(3, 3, 8, 2));
        processes.add(new Process(4, 4, 3, 3));
        
        // Create a priority queue for processes
        PriorityQueue<Process> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.priority));
        
        // Process the processes in the priority queue
        int currentTime = 0;
        int totalWaitingTime = 0;
        while (!priorityQueue.isEmpty() || !processes.isEmpty()) {
            // Add arriving processes to the priority queue
            for (int i = 0; i < processes.size(); i++) {
                Process process = processes.get(i);
                if (process.arrivalTime <= currentTime) {
                    priorityQueue.add(process);
                    processes.remove(i);
                    i--;
                }
            }
            
            // If the priority queue is not empty, process the highest priority process
            if (!priorityQueue.isEmpty()) {
                Process process = priorityQueue.poll();
                
                // Update waiting time for all processes in the priority queue
                for (Process p : priorityQueue) {
                    p.waitingTime += process.remainingTime;
                }
                
                // Process the current process
                int timeSlice = Math.min(1, process.remainingTime);
                currentTime += timeSlice;
                process.remainingTime -= timeSlice;
                
                // If the current process is not completed, add it back to the priority queue
                if (process.remainingTime > 0) {
                    priorityQueue.add(process);
                } else {
                    process.completionTime = currentTime;
                    totalWaitingTime += process.waitingTime;
                    System.out.println("Processing process ID " + process.processID + " with priority " + process.priority 
                                        + ", burst time " + process.burstTime + ", arrival time " + process.arrivalTime 
                                        + ", completion time " + process.completionTime + ", waiting time " + process.waitingTime + ".");
                }
            } else {
                currentTime++;
            }
        }
        double averageWaitingTime = (double) totalWaitingTime / (processes.size() + priorityQueue.size());
        System.out.println("Average waiting time: " + averageWaitingTime);
    }
}
