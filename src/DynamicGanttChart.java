import java.awt.*;
import javax.swing.*;

public class DynamicGanttChart extends JPanel {
    public  String[] jobNames;
    public int plen;
    public int[] startTimes;
    public int[] endTimes;
    public Color[] jobColors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK, Color.YELLOW, Color.RED, Color.ORANGE,
        Color.LIGHT_GRAY, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.YELLOW, Color.ORANGE, Color.PINK, Color.ORANGE, Color.RED, Color.MAGENTA};
    int currentTime;
    int refreshRate;
    int totalTime;

    DynamicGanttChart(int[] pids, int[]startTimes, int[]end_times){
        int[] intArray = pids;
        plen = pids.length;
        String[] strArray = new String[intArray.length];
 
        for (int i = 0; i < intArray.length; i++) {
            strArray[i] = String.valueOf(intArray[i]);
        }
        this.jobNames = strArray;
        this.endTimes = end_times;
        this.startTimes = startTimes;

        jobNames = strArray;

        refreshRate = 100;
        currentTime = 0;
        totalTime = endTimes[0];
        for(int i=1; i < endTimes.length; i++) {
            if(endTimes[i] > totalTime) {
                totalTime = endTimes[i];
            }
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new java.awt.Font("Poppins SemiBold", 0, 20)); 
        g2d.setColor(Color.WHITE);
                
        int width = 800;
        int height = 200;

        g2d.drawString("Current Time: " + currentTime, width + 30, 150);
        g2d.drawLine(50, height / 2, width + 50, height / 2);

        if(ALGO.select.algo == "rr") {
            g2d.drawString("Time Quantum: " + ALGO.input.quantum, 30, 150);
        }

        g2d.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); 
        for (int i = 0; i < jobNames.length; i++) {
            if (startTimes[i] <= currentTime) {
                int x = 50 + startTimes[i] * (width - 75) / totalTime;
                int w = (endTimes[i] - startTimes[i]) * (width - 75) / totalTime;
                g2d.setColor(jobColors[i % plen]);
                g2d.fillRect(x, 50, w, 50);

                g2d.drawString("P"+ jobNames[i], x + w / 2-20, 25);
                g2d.drawString(Integer.toString(startTimes[i]), x, height / 2 + 15);
                if(endTimes[i] < 10) {
                    g2d.drawString(Integer.toString(endTimes[i]), x + w - 10, height / 2 - 55);
                } else {
                    g2d.drawString(Integer.toString(endTimes[i]), x + w - 15, height / 2 - 55);
                }
            }
        }
    }

    public void startSimulation() {
        Timer timer = new Timer(refreshRate, e -> {
            if (currentTime <= totalTime) {
                currentTime++;
                
                repaint();
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
    public javax.swing.JLabel label;
}