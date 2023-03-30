public class SelectPreempt extends javax.swing.JPanel {
    public SelectPreempt() {
        initComponents();
    }
                      
    private void initComponents() {
       
        minimize = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        select_title = new javax.swing.JLabel();
        select_select = new javax.swing.JLabel();
        select_preemptP = new javax.swing.JButton();
        select_nonpreemptP = new javax.swing.JButton();
        select_logo = new javax.swing.JLabel();
        select_fms = new javax.swing.JLabel();
        select_vol = new javax.swing.JButton();
        select_return = new javax.swing.JButton();
        select_bg = new javax.swing.JLabel();

        setLayout(null);

        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/minimize.png"))); // NOI18N
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

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/close.png"))); // NOI18N
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
        select_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo/algo-small.png"))); // NOI18N
        select_title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(select_title);
        select_title.setBounds(150, 90, 150, 40);

        select_select.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_select.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/select_algo.png"))); // NOI18N
        select_select.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(select_select);
        select_select.setBounds(150, 140, 760, 60);

        select_preemptP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/preemptive.png"))); // NOI18N
        select_preemptP.setBorder(null);
        select_preemptP.setBorderPainted(false);
        select_preemptP.setContentAreaFilled(false);
        select_preemptP.setFocusPainted(false);
        select_preemptP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_preemptP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_preemptPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_preemptPMouseExited(evt);
            }
        });
        select_preemptP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_preemptPActionPerformed(evt);
            }
        });
        add(select_preemptP);
        select_preemptP.setBounds(150, 310, 350, 50);

        select_nonpreemptP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/non-preemptive.png"))); // NOI18N
        select_nonpreemptP.setBorder(null);
        select_nonpreemptP.setBorderPainted(false);
        select_nonpreemptP.setContentAreaFilled(false);
        select_nonpreemptP.setFocusPainted(false);
        select_nonpreemptP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_nonpreemptP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_nonpreemptPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_nonpreemptPMouseExited(evt);
            }
        });
        select_nonpreemptP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_nonpreemptPActionPerformed(evt);
            }
        });
        add(select_nonpreemptP);
        select_nonpreemptP.setBounds(150, 370, 460, 50);

        select_logo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        select_logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(select_logo);
        select_logo.setBounds(710, 210, 350, 330);

        select_fms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/fms-dark.png"))); // NOI18N
        select_fms.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(select_fms);
        select_fms.setBounds(1030, 20, 240, 130);

        select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png"))); // NOI18N
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

        select_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png"))); // NOI18N
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

        select_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/select_bg.png"))); // NOI18N
        select_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        select_bg.setOpaque(true);
        add(select_bg);
        select_bg.setBounds(0, 0, 1280, 720);
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

    private void select_nonpreemptPMouseEntered(java.awt.event.MouseEvent evt) {                                                
        select_nonpreemptP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/non-preemptive_hover.png")));
    }                                               

    private void select_nonpreemptPMouseExited(java.awt.event.MouseEvent evt) {                                               
        select_nonpreemptP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/non-preemptive.png")));
    }                                              

    private void select_nonpreemptPActionPerformed(java.awt.event.ActionEvent evt) {         
        if(ALGO.select.algo == "prio") {
            ALGO.select.algo_setter("prio-np");  
        }else {
            ALGO.select.algo_setter("sjf-np");  
        }                                          
        Music.sfx();
        ALGO.input.setInputs();
        ALGO.card.show(ALGO.mainPanel, "6");
    }                                                  

    private void select_preemptPMouseEntered(java.awt.event.MouseEvent evt) {                
        select_preemptP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/preemptive_hover.png")));
    }                                            

    private void select_preemptPMouseExited(java.awt.event.MouseEvent evt) {                                     
        select_preemptP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/select/preemptive.png")));
    }                                           

    private void select_preemptPActionPerformed(java.awt.event.ActionEvent evt) {            
        if(ALGO.select.algo == "prio") {
            ALGO.select.algo_setter("prio-p");  
        }else {
            ALGO.select.algo_setter("sjf-p");  
        }                                               
        Music.sfx();
        ALGO.input.setInputs();
        ALGO.card.show(ALGO.mainPanel, "6");
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

    private void select_returnMouseEntered(java.awt.event.MouseEvent evt) {                                           
        select_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return_hover.png")));
    }                                          

    private void select_returnMouseExited(java.awt.event.MouseEvent evt) {                                          
        select_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/window/return.png")));
    }                                         

    private void select_returnActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Music.sfx();
        ALGO.card.show(ALGO.mainPanel, "1");
    }      
    
    public void resetAudioButton() {
        if(ALGO.sound == true) {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/mute.png")));
        } else {
            select_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu/buttons/muteX.png")));
        }
    }
    
    public void setIcon() {
        if(ALGO.select.algo == "sjf") {
            select_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3D/sjf.png")));
        } else {
            select_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3D/prio.png")));
        }
    }
             
    private javax.swing.JButton exit;
    private javax.swing.JButton minimize;
    private javax.swing.JLabel select_bg;
    private javax.swing.JLabel select_fms;
    private javax.swing.JLabel select_logo;
    private javax.swing.JButton select_return;
    private javax.swing.JButton select_preemptP;
    private javax.swing.JLabel select_select;
    private javax.swing.JButton select_nonpreemptP;
    private javax.swing.JLabel select_title;
    private javax.swing.JButton select_vol;         
}
