import java.awt.*;
import javax.swing.*;

public class DynamicGanttChart extends JPanel {
    public String[] jobNames;
    public int[] startTimes;
    public int[] endTimes;
    public int[] intArray;
    public Color[] jobColors = { new Color(0, 0, 0),
        new Color(79, 155, 252), new Color(246, 187, 57), new Color(53, 213, 166), new Color(201, 53, 235), new Color(60, 78, 97),
        new Color(52, 81, 151), new Color(253, 125, 61), new Color(77, 167, 75), new Color(155, 54, 243), new Color(249, 19, 19),
        new Color(53, 150, 211), new Color(234, 239, 55), new Color(58, 250, 122), new Color(188, 51, 193), new Color(201, 0, 47), 
        // Duplicated for extra set of colors
        new Color(79, 155, 252), new Color(246, 187, 57), new Color(53, 213, 166), new Color(201, 53, 235), new Color(60, 78, 97),
        new Color(52, 81, 151), new Color(253, 125, 61), new Color(77, 167, 75), new Color(155, 54, 243), new Color(249, 19, 19),
        new Color(53, 150, 211), new Color(234, 239, 55), new Color(58, 250, 122), new Color(188, 51, 193), new Color(201, 0, 47)
    };
    int currentTime;
    int refreshRate;
    int totalTime;

    DynamicGanttChart(int[] pids, int[]startTimes, int[]end_times){
        intArray = pids;
       
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
                if(Integer.parseInt(jobNames[i]) == intArray[i]) {
                    g2d.setColor(jobColors[intArray[i]]);
                }
                // g2d.setColor(jobColors[i]);
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