import javax.swing.table.DefaultTableModel;

public class Results extends javax.swing.JPanel {
    public Results() {
        initComponents();
    }
    public void reset() {
        ALGO.input.prioArray.clear();
        ALGO.input.quantum = 0;
        ALGO.input.count = 1;
    }

    public void initComponents() {
        minimize = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        results_fms = new javax.swing.JLabel();
        results_gantt = new javax.swing.JLabel();
        results_title = new javax.swing.JLabel();
        results_label = new javax.swing.JLabel();
        results_vol = new javax.swing.JButton();
        results_menu = new javax.swing.JButton();
        results_return = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        results_table = new javax.swing.JTable();
        results_bg = new javax.swing.JLabel();

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

        results_fms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/fms-dark.png"))); 
        results_fms.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(results_fms);
        results_fms.setBounds(1030, 20, 240, 130);

        results_gantt.setFont(new java.awt.Font("Poppins Black", 0, 48)); // NOI18N
        results_gantt.setForeground(new java.awt.Color(255, 255, 255));
        results_gantt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        results_gantt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simulate/gant_cover.png"))); // NOI18N
        results_gantt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        //add(results_gantt);
        results_gantt.setBounds(110, 390, 1020, 220);

        results_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input/fcfs-title.png"))); 
        results_title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(results_title);
        results_title.setBounds(110, 70, 470, 110);

        results_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simulate/label.png"))); 
        results_label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(results_label);
        results_label.setBounds(110, 170, 1030, 90);

        results_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png"))); 
        results_vol.setBorder(null);
        results_vol.setBorderPainted(false);
        results_vol.setContentAreaFilled(false);
        results_vol.setFocusPainted(false);
        results_vol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                results_volMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                results_volMouseExited(evt);
            }
        });
        results_vol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                results_volActionPerformed(evt);
            }
        });
        add(results_vol);
        results_vol.setBounds(50, 630, 60, 50);

        results_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simulate/menu.png"))); 
        results_menu.setBorder(null);
        results_menu.setBorderPainted(false);
        results_menu.setContentAreaFilled(false);
        results_menu.setFocusPainted(false);
        results_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                results_menuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                results_menuMouseExited(evt);
            }
        });
        results_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                results_menuActionPerformed(evt);
            }
        });
        add(results_menu);
        results_menu.setBounds(510, 630, 210, 50);
   
        results_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png"))); 
        results_return.setBorder(null);
        results_return.setBorderPainted(false);
        results_return.setContentAreaFilled(false);
        results_return.setFocusPainted(false);
        results_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                results_returnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                results_returnMouseExited(evt);
            }
        });
        results_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                results_returnActionPerformed(evt);
            }
        });
        add(results_return);
        
        results_return.setBounds(1180, 620, 70, 70);
        results_table.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); 
        results_table.setFocusable(false);
        results_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "", "", "", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        results_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(results_table);
        if (results_table.getColumnModel().getColumnCount() > 0) {
            results_table.getColumnModel().getColumn(0).setResizable(false);
            results_table.getColumnModel().getColumn(0).setPreferredWidth(10);
            results_table.getColumnModel().getColumn(1).setResizable(false);
            results_table.getColumnModel().getColumn(1).setPreferredWidth(60);
            results_table.getColumnModel().getColumn(2).setResizable(false);
            results_table.getColumnModel().getColumn(2).setPreferredWidth(70);
            results_table.getColumnModel().getColumn(3).setResizable(false);
            results_table.getColumnModel().getColumn(3).setPreferredWidth(60);
            results_table.getColumnModel().getColumn(4).setResizable(false);
            results_table.getColumnModel().getColumn(4).setPreferredWidth(70);
            results_table.getColumnModel().getColumn(5).setResizable(false);
            results_table.getColumnModel().getColumn(5).setPreferredWidth(80);
            results_table.getColumnModel().getColumn(6).setResizable(false);
            results_table.getColumnModel().getColumn(6).setPreferredWidth(90);
            results_table.getColumnModel().getColumn(7).setResizable(false);
            results_table.getColumnModel().getColumn(7).setPreferredWidth(110);
            results_table.setFocusable(false);
        }
       
      
        add(jScrollPane1);
        jScrollPane1.setBounds(110, 250, 1020, 150);

        results_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input/input_bg.png"))); 
        results_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        results_bg.setOpaque(true);
        add(results_bg);
        results_bg.setBounds(0, 0, 1280, 720);
    }

    
    private void results_volMouseEntered(java.awt.event.MouseEvent evt) {
        if(ALGO.sound == true) {
            results_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute_hover.png")));
        } else {
            results_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/muteX_hover.png")));
        }
    }

    private void results_volMouseExited(java.awt.event.MouseEvent evt) {
        if(ALGO.sound == true) {
            results_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png")));
        } else {
            results_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/muteX.png")));
        }
    }

    private void results_volActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        if(ALGO.sound == true) {
            results_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/muteX.png")));
            ALGO.sound = false;
            
            Music.bgMusic.pause();
        } else {
            results_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png")));
            ALGO.sound = true;
           
            try {
                Music.bgMusic.resume();
            } catch (Exception ex) {}
        }
    }

    private void results_returnMouseEntered(java.awt.event.MouseEvent evt) {
        results_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return_hover.png")));
    }

    private void results_returnMouseExited(java.awt.event.MouseEvent evt) {
        results_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png")));
    }

    private void results_returnActionPerformed(java.awt.event.ActionEvent evt) {     
        reset();
        Music.sfx();
        ALGO.select.resetAudioButton(); 
        ALGO.input.setInputs();
        ALGO.input.quantum = 0;
        ALGO.card.show(ALGO.mainPanel, "5");
        DefaultTableModel model = (DefaultTableModel) results_table.getModel();
        model.setRowCount(0);
        remove(ALGO.input.chart);
    }

    private void results_menuMouseEntered(java.awt.event.MouseEvent evt) {
        results_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simulate/menu_hover.png")));
    }

    private void results_menuMouseExited(java.awt.event.MouseEvent evt) {
        results_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simulate/menu.png")));
    }
 
    private void results_menuActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        ALGO.menu.resetAudioButton();
        ALGO.input.setInputs();
        ALGO.input.quantum = 0;
        reset();
        ALGO.card.show(ALGO.mainPanel, "1");
        DefaultTableModel model = (DefaultTableModel) results_table.getModel();
        model.setRowCount(0);
        remove(ALGO.input.chart);
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

    public void setTitle() {
        if(ALGO.select.algo == "fcfs") {
            results_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input/fcfs-title.png"))); 
        }
        else if(ALGO.select.algo == "rr") {
            results_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input/rr-title.png"))); 
        }
        else if(ALGO.select.algo == "prio-np") {
            results_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input/prio_np-title.png"))); 
        }
        else if(ALGO.select.algo == "prio-p") {
            results_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input/prio_p-title.png"))); 
        }
        else if(ALGO.select.algo == "sjf-np"){
            results_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input/sjf_np-title.png"))); 
        }
        else if(ALGO.select.algo == "sjf-p"){
            results_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/input/sjf_p-title.png"))); 
        }
    }

    public void resetAudioButton() {
        if(ALGO.sound == true) {
            results_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png")));
        } else {
            results_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/muteX.png")));
        }
    }
    private javax.swing.JButton exit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton minimize;
    private javax.swing.JLabel results_bg;
    private javax.swing.JLabel results_fms;
    private javax.swing.JLabel results_label;
    private javax.swing.JButton results_menu;
    private javax.swing.JButton results_return;
    public static javax.swing.JTable results_table;
    private javax.swing.JLabel results_gantt;
    private javax.swing.JLabel results_title;
    private javax.swing.JButton results_vol;
   
    
}
