import java.awt.*;
import javax.swing.*;

public class DynamicGanttChart extends JPanel {

    // Define the job details and their respective start and end times
    private final String[] jobNames = {"Job 1", "Job 2", "Job 3", "Job 4", "Job 5"};
    private final int[] startTimes = {0, 3, 5, 8, 10};
    private final int[] endTimes = {2, 6, 7, 9, 12};

    // Define the colors to use for each job
    private final Color[] jobColors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA};

    private final int refreshRate = 1000; // Refresh rate of the timer in milliseconds
    private int currentTime = 0; // Current time of the simulation in seconds
    private final int totalTime = endTimes[endTimes.length - 1]; // Total time of the simulation in seconds

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Draw the current time on top of the Gantt chart
        g2d.drawString("Current Time: " + currentTime, width / 2 - 50, 150);

        // Define the axis lines and labels
        g2d.drawLine(50, height / 2, width - 25, height / 2);
        g2d.drawString("0", 45, height / 2 + 5);
        g2d.drawString("Time", width - 40, height / 2 + 5);

        // Draw the rectangles for each job that has already started
        for (int i = 0; i < jobNames.length; i++) {
            if (startTimes[i] <= currentTime) {
                int x = 50 + startTimes[i] * (width - 75) / (endTimes[endTimes.length - 1]);
                int w = (endTimes[i] - startTimes[i]) * (width - 75) / (endTimes[endTimes.length - 1]);
                int h = height / 2;
                g2d.setColor(jobColors[i]);
                g2d.fillRect(x, 0, w, h);

                // Draw the job name above the rectangle and start and end times below the rectangle
                g2d.setColor(Color.BLACK);
                g2d.drawString(jobNames[i], x + w / 2 - 20, 35);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dynamic Gantt Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);
        frame.setLocationRelativeTo(null);
        DynamicGanttChart chart = new DynamicGanttChart();
        frame.add(chart);
        frame.setVisible(true);
        chart.startSimulation();
    }
}