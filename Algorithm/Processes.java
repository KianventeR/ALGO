package Algorithm;
import java.util.ArrayList;

public class Processes {

    private ArrayList<Integer> pids;
    private ArrayList<Integer> bursts;
    private ArrayList<Integer> arrivals;
    private ArrayList<Integer> priorities;
    private ArrayList<Integer> waits;
    private ArrayList<Integer> turnarounds;
    private ArrayList<Double> ave_waits;
    private ArrayList<Double> ave_turnarounds;

    Processes(){
        pids = new ArrayList<Integer>();
        bursts = new ArrayList<Integer>();
        arrivals = new ArrayList<Integer>();
        priorities = new ArrayList<Integer>();
        waits = new ArrayList<Integer>();
        turnarounds = new ArrayList<Integer>();
        ave_waits = new ArrayList<Double>();
        ave_turnarounds = new ArrayList<Double>();
    }

    public void createProcess(int pid, int burst, int arr, int priority){
        pids.add(pid);
        bursts.add(burst);
        arrivals.add(arr);
        priorities.add(priority);
    }

    public String getProcessName(int index){
        return "P"+pids.get(index);
    }

    public void deleteProcess(int pid){
        for(int i = 0; i < pids.size(); i++){
            int val = pids.get(i);
            if(val == pid){
                pids.remove(i);
                bursts.remove(i);
                arrivals.remove(i);
                priorities.remove(i);
                break;
            }
        }
    }

}
