import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.border.Border;

public class DynamicGanttChart extends JPanel {
    public  String[] jobNames;
    public  int[] startTimes;
    public  int[] endTimes;
    public  Color[] jobColors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK, Color.YELLOW, Color.RED, Color.ORANGE,
        Color.LIGHT_GRAY, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.YELLOW, Color.ORANGE, Color.PINK, Color.ORANGE, Color.RED, Color.MAGENTA};
    int currentTime;
    int refreshRate;
    int totalTime;

    DynamicGanttChart(int[] pids, int[]startTimes, int[]end_times){
        int[] intArray = pids;
 
        String[] strArray = new String[intArray.length];
 
        for (int i = 0; i < intArray.length; i++) {
            strArray[i] = String.valueOf(intArray[i]);
        }
        this.jobNames = strArray;
        this.endTimes = end_times;
        this.startTimes = startTimes;

        jobNames = strArray;
        
    // Define the job details and their respective start and end times
    

    // Define the colors to use for each job
 
    

    refreshRate = 1000; // Refresh rate of the timer in milliseconds
    currentTime = 0; // Current time of the simulation in seconds
    totalTime = endTimes[endTimes.length - 1]; // Total time of the simulation in seconds
        
    }

    
    public void paintComponent(Graphics g) {
        
        Color c1 = new Color(238, 238, 238);
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new java.awt.Font("Poppins Bold", 0, 14)); 
        g2d.setColor(Color.WHITE);
                
        int width = 800;
        int height = 200;

        // Draw the current time on top of the Gantt chart
        g2d.drawString("Current Time: " + currentTime, width + 50, 200);

        // Define the axis lines and labels
        g2d.drawLine(50, height / 2, width + 50, height / 2);
        // g2d.drawString("0", 30, height / 2 + 5);
        // g2d.drawString("Time", 100 - 40, 200 / 2 + 5);
        g2d.setFont(new java.awt.Font("Poppins Bold", 0, 14)); 
        // Draw the rectangles for each job that has already started
        for (int i = 0; i < jobNames.length; i++) {
            if (startTimes[i] <= currentTime) {
                int x = 50 + startTimes[i] * (width - 75) / (endTimes[endTimes.length - 1]);
                int w = (endTimes[i] - startTimes[i]) * (width - 75) / (endTimes[endTimes.length - 1]);
                int h = height / 2;
                g2d.setColor(jobColors[i % 12]);
                g2d.fillRect(x, 50, w, 50);

                // Draw the job name above the rectangle and start and end times below the rectangle
                
                g2d.drawString("P"+ jobNames[i], x + w / 2-20, 35);
                g2d.drawString(Integer.toString(startTimes[i]), x, height / 2 + 15);
                g2d.drawString(Integer.toString(endTimes[i]), x + w - 10, height / 2 + 15);
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

    // public static void main(String[] args) {
    //     JFrame frame = new JFrame("Dynamic Gantt Chart");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setSize(1280, 720);
    //     frame.setLocationRelativeTo(null);
    //     DynamicGanttChart chart = new DynamicGanttChart();
    //     frame.add(chart);
    //     frame.setVisible(true);
    //     chart.startSimulation();
    // }
}