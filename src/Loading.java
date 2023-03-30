public class Loading extends javax.swing.JPanel {
    public Loading() {
        initComponents();
    }
                             
    private void initComponents() {
        load_bg = new javax.swing.JLabel();

        setLayout(null);

        load_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selection/fcfs.png"))); 
        load_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        load_bg.setOpaque(true);
        add(load_bg);
        load_bg.setBounds(0, 0, 1280, 720);
    }                   
               
    public void setBG(String algo) {
        if(algo == "fcfs") {
            load_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selection/fcfs.png")));
        } else if(algo == "sjf-p" || algo == "sjf-np") {
            load_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selection/sjf.png")));
        } else if(algo == "rr") {
            load_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selection/round-robin.png")));
        } else if(algo == "prio-p" || algo == "prio-np") {
            load_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selection/priority.png")));
        }
    }
    private javax.swing.JLabel load_bg;             
}
