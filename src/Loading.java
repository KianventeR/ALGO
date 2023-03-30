public class Loading extends javax.swing.JPanel {
    public Loading() {
        initComponents();
    }
                             
    private void initComponents() {
        splash_bg = new javax.swing.JLabel();

        setLayout(null);

        splash_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selection/fcfs_loading.png"))); // NOI18N
        splash_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        splash_bg.setOpaque(true);
        add(splash_bg);
        splash_bg.setBounds(0, 0, 1280, 720);
    }                   
               
    private javax.swing.JLabel splash_bg;             
}
