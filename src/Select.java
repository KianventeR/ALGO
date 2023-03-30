public class Select extends javax.swing.JPanel {
    public Select() {
        initComponents();
    }

    private void initComponents() {
        minimize = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        select_title = new javax.swing.JLabel();
        select_select = new javax.swing.JLabel();
        select_fcfs = new javax.swing.JButton();
        select_rr = new javax.swing.JButton();
        select_sjf = new javax.swing.JButton();
        select_prio = new javax.swing.JButton();
        select_logo = new javax.swing.JLabel();
        select_fms = new javax.swing.JLabel();
        select_vol = new javax.swing.JButton();
        select_return = new javax.swing.JButton();
        select_bg = new javax.swing.JLabel();

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

        select_title.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo/algo-small.png"))); 
        select_title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(select_title);
        select_title.setBounds(150, 90, 150, 40);

        select_select.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_select.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/select_algo.png"))); 
        select_select.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(select_select);
        select_select.setBounds(150, 140, 760, 60);

        select_fcfs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/fcfs.png"))); 
        select_fcfs.setBorder(null);
        select_fcfs.setBorderPainted(false);
        select_fcfs.setContentAreaFilled(false);
        select_fcfs.setFocusPainted(false);
        select_fcfs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_fcfs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_fcfsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_fcfsMouseExited(evt);
            }
        });
        select_fcfs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_fcfsActionPerformed(evt);
            }
        });
        add(select_fcfs);
        select_fcfs.setBounds(150, 260, 190, 50);

        select_rr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/round-robin.png"))); 
        select_rr.setBorder(null);
        select_rr.setBorderPainted(false);
        select_rr.setContentAreaFilled(false);
        select_rr.setFocusPainted(false);
        select_rr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_rr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_rrMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_rrMouseExited(evt);
            }
        });
        select_rr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_rrActionPerformed(evt);
            }
        });
        add(select_rr);
        select_rr.setBounds(150, 320, 352, 50);

        select_sjf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/sjf.png"))); 
        select_sjf.setBorder(null);
        select_sjf.setBorderPainted(false);
        select_sjf.setContentAreaFilled(false);
        select_sjf.setFocusPainted(false);
        select_sjf.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_sjf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_sjfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_sjfMouseExited(evt);
            }
        });
        select_sjf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_sjfActionPerformed(evt);
            }
        });
        add(select_sjf);
        select_sjf.setBounds(150, 380, 490, 50);

        select_prio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/priority.png"))); 
        select_prio.setBorder(null);
        select_prio.setBorderPainted(false);
        select_prio.setContentAreaFilled(false);
        select_prio.setFocusPainted(false);
        select_prio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_prio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_prioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_prioMouseExited(evt);
            }
        });
        select_prio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_prioActionPerformed(evt);
            }
        });
        add(select_prio);
        select_prio.setBounds(150, 440, 270, 50);

        select_logo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3D/fcfs.png"))); 
        select_logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(select_logo);
        select_logo.setBounds(710, 210, 350, 330);

        select_fms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/fms-dark.png"))); 
        select_fms.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(select_fms);
        select_fms.setBounds(1030, 20, 240, 130);

        select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png"))); 
        select_vol.setBorder(null);
        select_vol.setBorderPainted(false);
        select_vol.setContentAreaFilled(false);
        select_vol.setFocusPainted(false);
        select_vol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_volMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_volMouseExited(evt);
            }
        });
        select_vol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_volActionPerformed(evt);
            }
        });
        add(select_vol);
        select_vol.setBounds(50, 630, 60, 50);

        select_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png"))); 
        select_return.setBorder(null);
        select_return.setBorderPainted(false);
        select_return.setContentAreaFilled(false);
        select_return.setFocusPainted(false);
        select_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_returnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_returnMouseExited(evt);
            }
        });
        select_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_returnActionPerformed(evt);
            }
        });
        add(select_return);
        select_return.setBounds(1180, 620, 70, 70);

        select_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/select_bg.png"))); 
        select_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        select_bg.setOpaque(true);
        add(select_bg);
        select_bg.setBounds(0, 0, 1280, 720);
    }

    private void select_fcfsMouseEntered(java.awt.event.MouseEvent evt) {
        select_fcfs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/fcfs_hover.png")));
        select_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3D/fcfs.png")));
    }

    private void select_fcfsMouseExited(java.awt.event.MouseEvent evt) {
        select_fcfs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/fcfs.png")));
    }

    private void select_fcfsActionPerformed(java.awt.event.ActionEvent evt) {
        algo_setter("fcfs");
        Music.sfx();
        ALGO.input.resetAudioButton();
        ALGO.input.setInputs();
        ALGO.load.setBG(algo);
        ALGO.card.show(ALGO.mainPanel, "8");

        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                ALGO.card.show(ALGO.mainPanel, "6");
            }
        }, 2 * 1000); // Miliseconds
    }

    public void algo_setter(String string) {
        algo = string;
    }
    
    public String algo;
    private void select_rrMouseEntered(java.awt.event.MouseEvent evt) {
        select_rr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/round-robin_hover.png")));
        select_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3D/rr.png")));
    }

    private void select_rrMouseExited(java.awt.event.MouseEvent evt) {
        select_rr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/round-robin.png")));
    }

    private void select_rrActionPerformed(java.awt.event.ActionEvent evt) {
        algo_setter("rr");
        Music.sfx();
        ALGO.input.resetAudioButton();
        ALGO.load.setBG(algo);
        ALGO.input.setInputs();
        ALGO.card.show(ALGO.mainPanel, "8");

        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                ALGO.card.show(ALGO.mainPanel, "6");
            }
        }, 2 * 1000); // Miliseconds
    }

    private void select_returnMouseEntered(java.awt.event.MouseEvent evt) {
        select_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return_hover.png")));
    }

    private void select_returnMouseExited(java.awt.event.MouseEvent evt) {
        select_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png")));
    }

    private void select_returnActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        ALGO.menu.resetAudioButton();
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

    private void select_sjfMouseEntered(java.awt.event.MouseEvent evt) {
        select_sjf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/sjf_hover.png")));
        select_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3D/sjf.png")));
    }

    private void select_sjfMouseExited(java.awt.event.MouseEvent evt) {
        select_sjf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/sjf.png")));
    }

    private void select_sjfActionPerformed(java.awt.event.ActionEvent evt) {
        algo_setter("sjf");
        Music.sfx();
        ALGO.input.resetAudioButton();
        ALGO.selectP.setIcon();
        ALGO.card.show(ALGO.mainPanel, "9");
    }

    private void select_prioMouseEntered(java.awt.event.MouseEvent evt) {
        select_prio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/priority_hover.png")));
        select_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3D/prio.png")));
    }

    private void select_prioMouseExited(java.awt.event.MouseEvent evt) {
        select_prio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/priority.png")));
    }

    private void select_prioActionPerformed(java.awt.event.ActionEvent evt) {
        algo_setter("prio");
        Music.sfx();
        ALGO.input.resetAudioButton();
        ALGO.selectP.setIcon();
        ALGO.card.show(ALGO.mainPanel, "9");
    }

    private void select_volMouseEntered(java.awt.event.MouseEvent evt) {
        if(ALGO.sound == true) {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute_hover.png")));
        } else {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/muteX_hover.png")));
        }
    }

    private void select_volMouseExited(java.awt.event.MouseEvent evt) {
        if(ALGO.sound == true) {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png")));
        } else {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/muteX.png")));
        }
    }

    private void select_volActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        if(ALGO.sound == true) {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/muteX.png")));
            ALGO.sound = false;
            
            Music.bgMusic.pause();
        } else {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png")));
            ALGO.sound = true;
           
            try {
                Music.bgMusic.resume();
            } catch (Exception ex) {}
        }
    }

    public void resetAudioButton() {
        if(ALGO.sound == true) {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png")));
        } else {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/muteX.png")));
        }
    }

    private javax.swing.JButton exit;
    private javax.swing.JButton minimize;
    private javax.swing.JLabel select_bg;
    private javax.swing.JButton select_fcfs;
    private javax.swing.JLabel select_fms;
    private javax.swing.JLabel select_logo;
    private javax.swing.JButton select_prio;
    private javax.swing.JButton select_return;
    private javax.swing.JButton select_rr;
    private javax.swing.JLabel select_select;
    private javax.swing.JButton select_sjf;
    private javax.swing.JLabel select_title;
    private javax.swing.JButton select_vol;
}
