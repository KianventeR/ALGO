public class About extends javax.swing.JPanel {
    public About() {
        initComponents();
    }

    private void initComponents() {
        minimize = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        about_return = new javax.swing.JButton();
        jTextArea1 = new javax.swing.JTextArea();
        about_bg = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setLayout(null);

        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/minimize.png")));
        minimize.setBorder(null);
        minimize.setBorderPainted(false);
        minimize.setContentAreaFilled(false);
        minimize.setFocusPainted(false);
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeMouseExited(evt);
            }
        });
        minimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeActionPerformed(evt);
            }
        });
        add(minimize);
        minimize.setBounds(1190, 10, 40, 40);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/close.png")));
        exit.setBorder(null);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
        });
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        add(exit);
        exit.setBounds(1230, 10, 40, 40);

        about_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png")));
        about_return.setBorder(null);
        about_return.setBorderPainted(false);
        about_return.setContentAreaFilled(false);
        about_return.setFocusPainted(false);
        about_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                about_returnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                about_returnMouseExited(evt);
            }
        });
        about_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                about_returnActionPerformed(evt);
            }
        });
        add(about_return);
        about_return.setBounds(140, 530, 70, 70);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Poppins", 0, 18));
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("ALGO: A CPU SCHEDULING ALGORITHM SIMULATOR\n\nThis program is developed by Francis Lorenzo A. Espiña, MelbenKian R. Gabiana, and Shann Aurelle G. Ripalda (FMS STUDIOS) \nas a group project for CMSC 125. It is also built using Java \nlanguage and the GUI  is constructed using Netbeans IDE.");
        jTextArea1.setBorder(null);
        jTextArea1.setFocusable(false);
        jTextArea1.setOpaque(false);
        add(jTextArea1);
        jTextArea1.setBounds(140, 240, 570, 210);

        about_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/about/about_background.png")));
        about_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        about_bg.setMaximumSize(new java.awt.Dimension(1280, 720));
        about_bg.setMinimumSize(new java.awt.Dimension(1280, 720));
        about_bg.setOpaque(true);
        about_bg.setPreferredSize(new java.awt.Dimension(1280, 720));
        add(about_bg);
        about_bg.setBounds(0, 0, 1280, 720);
    }

    private void about_returnMouseEntered(java.awt.event.MouseEvent evt) {
        about_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return_hover.png")));
    }

    private void about_returnMouseExited(java.awt.event.MouseEvent evt) {
        about_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png")));
    }

    private void about_returnActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        ALGO.card.show(ALGO.mainPanel, "1");
    }

    private void minimizeMouseEntered(java.awt.event.MouseEvent evt) {
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/minimize_hover.png")));
    }

    private void minimizeMouseExited(java.awt.event.MouseEvent evt) {
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/minimize.png")));
    }

    private void minimizeActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        ALGO.mainFrame.setState(java.awt.Frame.ICONIFIED);
    }

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/close_hover.png")));
    }

    private void exitMouseExited(java.awt.event.MouseEvent evt) {
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/close.png")));
    }

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        System.exit(0);
    }

    private javax.swing.JLabel about_bg;
    private javax.swing.JButton about_return;
    private javax.swing.JButton exit;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton minimize;
}
