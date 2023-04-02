import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Job {
    private String id;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int startTime;
    private int finishTime;

    public Job(String id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public String getName(){
        return id;
    }
    public int getId() {
        return Integer.parseInt(this.id.replace("J", ""));
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getWaitingTime() {
        return startTime - arrivalTime;
    }

    public int getTurnaroundTime() {
        return finishTime - arrivalTime;
    }
}

public class npps {
    private static ArrayList<Job> jobs;

    public npps(int[] burst, int[] arrival, int[] priority){
        this.jobs = new ArrayList<>();
        addJobs(arrival, burst, priority);
        
        String res = generateGanttChart();
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public Boolean isEmpty(){
        return jobs.isEmpty();
    }

    public void addJobs(int[] arrival, int[] burst, int[] priority){
        for(int i = 0; i < arrival.length; i++){
            addJob(new Job("J"+(i+1),arrival[i],burst[i],priority[i]));
        }
    }

    public static int[] getProcessIDs(){
        return IntStream.range(0, jobs.size()).boxed()
            .mapToInt(i -> jobs.get(i).getId()).toArray();
    }

    public int[] getBursts(){
        return IntStream.range(0, jobs.size()).boxed()
            .mapToInt(i -> jobs.get(i).getBurstTime()).toArray();
    }

    public int[] getArrivals(){
        return IntStream.range(0, jobs.size()).boxed()
            .mapToInt(i -> jobs.get(i).getArrivalTime()).toArray();
    }

    public static int[] getStartTimes(){
        int[] startTimes = new int[jobs.size()];

        for (int i = 0; i < jobs.size(); i++) {
            startTimes[i] = jobs.get(i).getStartTime();
        }
        return startTimes;
    }

    public static int[] getEndTimes(){
        int[] endTimes = new int[jobs.size()];

        for (int i = 0; i < jobs.size(); i++) {
            endTimes[i] = jobs.get(i).getFinishTime();
        }
        return endTimes;
    }

    public int[] getWaitingTimes() {
        int[] waitingTimes = new int[jobs.size()];

        for (int i = 0; i < jobs.size(); i++) {
            waitingTimes[i] = jobs.get(i).getWaitingTime();
        }
        return waitingTimes;
    }

    public int[] getTurnaroundTimes() {
        int[] turnaroundTimes = new int[jobs.size()];

        for (int i = 0; i < jobs.size(); i++) {
            turnaroundTimes[i] = jobs.get(i).getTurnaroundTime();
        }
        return turnaroundTimes;
    }

    public int[] getPriorities(){
        int[] priorities = new int[jobs.size()];

        for (int i = 0; i < jobs.size(); i++) {
            priorities[i] = jobs.get(i).getPriority();
        }
        return priorities;
    }


    public double getAverageWaitingTime(){
        double sum = 0;
        int[] nums = getWaitingTimes();
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        double result = sum / jobs.size();
        return result;
    }

    public double getAverageTurnaroundTime(){
        double sum = 0;
        int[] nums = getTurnaroundTimes();
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        double result = sum / jobs.size();
        return result;
    }


    public String generateGanttChart() {
        StringBuilder chart = new StringBuilder();
        int currentTime = 0;
        ArrayList<Job> jobsCopy = new ArrayList<>(jobs);
        ArrayList<Job> jobsSaved = new ArrayList<>();

        while (!jobsCopy.isEmpty()) {
            Job selectedJob = null;

            for (Job job : jobsCopy) {
                if (job.getArrivalTime() <= currentTime) {
                    if (selectedJob == null || job.getPriority() < selectedJob.getPriority()) {
                        selectedJob = job;
                    }
                }
            }

            if (selectedJob != null) {
                jobsCopy.remove(selectedJob);
                selectedJob.setStartTime(currentTime);
                currentTime += selectedJob.getBurstTime();
                selectedJob.setFinishTime(currentTime);
                chart.append(selectedJob.getName());
                jobsSaved.add(selectedJob);
            } else {
                chart.append("-");
                currentTime++;
            }
        }
        jobs = jobsSaved;
        jobs = IntStream.range(0, jobs.size()).boxed()
        .sorted(Comparator.comparingInt(i -> jobs.get(i).getArrivalTime()))
        .map(i-> jobs.get(i))
        .collect(Collectors.toCollection(ArrayList::new));
        return chart.toString();
    }

    public static void main(String[] args) {
        int[] arrivals = {1,3,2,4,5};
        int[] bursts = {2,3,1,2,4};
        int[] priorities = {1,2,3,4,5}; 
        npps scheduler = new npps(bursts, arrivals, priorities);
        String ganttChart = scheduler.generateGanttChart();
        System.out.println(ganttChart);
        System.out.println(scheduler.isEmpty());
        for(int num : scheduler.getProcessIDs()){
            System.out.print(num + " ");
        }
        System.out.println();
        for(int num : scheduler.getStartTimes()){
            System.out.print(num + " ");
        }
        System.out.println();
        for(int num : scheduler.getEndTimes()){
            System.out.print(num + " ");
        }
        System.out.println();
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
    }
}