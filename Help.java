public class Help extends javax.swing.JPanel {
    public Help() {
        initComponents();
    }

    private void initComponents() {
        minimize = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        help_return = new javax.swing.JButton();
        help_bg = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
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

        help_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png"))); 
        help_return.setBorder(null);
        help_return.setBorderPainted(false);
        help_return.setContentAreaFilled(false);
        help_return.setFocusPainted(false);
        help_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_returnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_returnMouseExited(evt);
            }
        });
        help_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                help_returnActionPerformed(evt);
            }
        });
        add(help_return);
        help_return.setBounds(130, 600, 70, 70);

        help_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help/help.png"))); 
        help_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        help_bg.setOpaque(true);
        add(help_bg);
        help_bg.setBounds(0, 0, 1280, 720);
    }

    private void help_returnMouseEntered(java.awt.event.MouseEvent evt) {
        help_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return_hover.png")));
    }

    private void help_returnMouseExited(java.awt.event.MouseEvent evt) {
        help_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png")));
    }

    private void help_returnActionPerformed(java.awt.event.ActionEvent evt) {
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

    private javax.swing.JButton exit;
    private javax.swing.JLabel help_bg;
    private javax.swing.JButton help_return;
    private javax.swing.JButton minimize;
}
