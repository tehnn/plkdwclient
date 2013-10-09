package myclient;

import UTEHN.SimpleMySQL;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.ini4j.Ini;

public class Client2 extends javax.swing.JFrame {

    private String pcucode = null;
    private int PORT;
    private String ServerIp = null;
    Socket socket = null;
    SocketAddress sockaddr = null;
    PrintWriter out = null;
    BufferedReader brinp = null;
    SystemTray tray = SystemTray.getSystemTray();
    TrayIcon trayIcon;
    boolean isSending = false;
    String hishost, hisuser, hispass, hisdb;
    Timer timer = new javax.swing.Timer(1 * 20 * 1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (isSending == false) {

                isSending = true;
                btnStartActionPerformed(null);


            }
        }
    });

    public Client2() {


        File iniFile = new File("config.ini");
        if (!iniFile.exists()) {
            try {
                iniFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        ImageIcon img = new ImageIcon("sync.png");

        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        trayIcon = new TrayIcon(img.getImage());

        try {
            tray.add(trayIcon);
            trayIcon.displayMessage("Plk Dw Sync Client is running...",
                    "Phitsanulok Public Helath Office", TrayIcon.MessageType.INFO);

        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        final PopupMenu popup = new PopupMenu();

        MenuItem aboutItem = new MenuItem("About");
        MenuItem exitItem = new MenuItem("Exit");
        MenuItem showItem = new MenuItem("Show");

        //Add components to popup menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(showItem);
        popup.addSeparator();
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

        trayIcon.setToolTip("Plk Dw Sync Client is running...");

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });

        showItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Plk Dw Sync Client.\n");
            }
        });

        //tray

        initComponents();


        setIconImage(img.getImage());
        setLocationRelativeTo(null);
        setVisible(true);


    }// end Contructure

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPcuCode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbStatusHis = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        btnHideWin = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cli = new javax.swing.JTextArea();
        txtTable = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        log_com = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        txtCountTable = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuClose = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnuHisCon = new javax.swing.JMenuItem();
        mnuAboutFF = new javax.swing.JMenu();
        mnuAbout = new javax.swing.JMenuItem();

        setTitle("Plk Dw Sync Client 1.0");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setBackground(new java.awt.Color(51, 153, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("PLK DW Client 1.0");

        txtPcuCode.setText("00000");

        jLabel5.setText("PCUCODE");

        lbStatusHis.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbStatusHis.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbStatusHis.setText("Status...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPcuCode, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbStatusHis)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPcuCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(lbStatusHis))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("GateWay");

        jLabel3.setText("Port");

        btnStart.setText("Start Sync");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnHideWin.setText("Hide to Tray");
        btnHideWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHideWinActionPerformed(evt);
            }
        });

        btnStop.setText(" Stop Sync ");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(102, 102, 102));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel4.setPreferredSize(new java.awt.Dimension(34, 30));

        btnExit.setText("       Exit      ");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        cli.setColumns(20);
        cli.setRows(5);
        jScrollPane1.setViewportView(cli);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTable)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Status", jPanel2);

        log_com.setColumns(20);
        log_com.setRows(5);
        jScrollPane2.setViewportView(log_com);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Command", jPanel3);

        log.setColumns(20);
        log.setRows(5);
        jScrollPane3.setViewportView(log);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Log", jPanel4);

        txtCountTable.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        txtCountTable.setForeground(javax.swing.UIManager.getDefaults().getColor("PropSheet.selectionBackground"));
        txtCountTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCountTable.setText("TABLES");

        jMenu1.setText("File");

        mnuClose.setText("Close");
        mnuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCloseActionPerformed(evt);
            }
        });
        jMenu1.add(mnuClose);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Setting");

        mnuHisCon.setText("His Connection");
        mnuHisCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHisConActionPerformed(evt);
            }
        });
        jMenu2.add(mnuHisCon);

        jMenuBar1.add(jMenu2);

        mnuAboutFF.setText("Help");

        mnuAbout.setText("เกี่ยวกับ");
        mnuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAboutActionPerformed(evt);
            }
        });
        mnuAboutFF.add(mnuAbout);

        jMenuBar1.add(mnuAboutFF);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExit)
                        .addGap(18, 18, 18)
                        .addComponent(txtCountTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHideWin)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart)
                    .addComponent(jLabel2)
                    .addComponent(btnStop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnHideWin)
                    .addComponent(txtCountTable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed

        if (txtPcuCode.getText().toString().trim().equals("")) {
            return;
        }
        this.ServerIp = txtIp.getText().toString().trim();
        this.PORT = Integer.valueOf(txtPort.getText().toString().trim());
        if (ServerIp.equals(null) || PORT == 0) {
            return;
        }
        this.pcucode = txtPcuCode.getText().toString().trim();

        socket = new Socket();

        sockaddr = new InetSocketAddress(ServerIp, PORT);

        try {
            Ini ini = new Ini(new File("config.ini"));
            ini.put("SERVER", "host", this.ServerIp);
            ini.put("SERVER", "port", this.PORT);

            ini.store();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        log_com.setText("");
        isSending = true;

        Sender mySend = new Sender();
        Thread send = new Thread(mySend);
        send.start();


        log.append("\n" + new Date() + ":" + "Sync Started..");
        log.setCaretPosition(log.getDocument().getLength());

        btnStart.setEnabled(false);

    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        //System.out.println("Click Stop.");
        isSending = false;
        btnStart.setEnabled(true);
        try {
            socket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnStopActionPerformed

    private void mnuCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCloseActionPerformed
        tray.remove(trayIcon);
        System.exit(0);
    }//GEN-LAST:event_mnuCloseActionPerformed

    private void btnHideWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHideWinActionPerformed

        this.dispose();
        trayIcon.displayMessage("Plk Dw Sync Client is running...",
                "Phitsanulok Public Helath Office", TrayIcon.MessageType.INFO);

    }//GEN-LAST:event_btnHideWinActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        trayIcon.displayMessage("Plk Dw Sync Client is running...",
                "Phitsanulok Public Helath Office", TrayIcon.MessageType.INFO);
    }//GEN-LAST:event_formWindowClosing

    private void mnuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAboutActionPerformed

        JOptionPane.showMessageDialog(null,
                "Plk Dw Sync Client.\n");
    }//GEN-LAST:event_mnuAboutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed

        tray.remove(trayIcon);
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void mnuHisConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHisConActionPerformed

        JDialog dlg = new SetHisDialog(this, true);
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);

    }//GEN-LAST:event_mnuHisConActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cli.requestFocus();

        try {
            Ini ini = new Ini(new File("config.ini"));

            hishost = ini.get("Connection", "host");
            hisuser = ini.get("Connection", "user");
            hispass = ini.get("Connection", "pass");
            hisdb = ini.get("Connection", "database");
            pcucode = ini.get("PCUCODE", "pcucode");
            txtIp.setText(ini.get("SERVER", "host"));
            txtPort.setText(ini.get("SERVER", "port"));
            txtPcuCode.setText(pcucode);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        SimpleMySQL my = new SimpleMySQL();

        if (my.connect(hishost, hisuser, hispass, hisdb, "tis-620")) {
            lbStatusHis.setForeground(Color.GREEN);
            lbStatusHis.setText(hisdb + " : Readey.");
        } else {
            lbStatusHis.setForeground(Color.red);
            lbStatusHis.setText(hisdb + " : No Readey.!!");
        }
        my.query("CREATE DATABASE IF NOT EXISTS PlKDW");

        my.select_db("PLKDW");

        //my.query("DROP TABLE IF EXISTS table_all");
        String csql2 = "CREATE TABLE IF NOT EXISTS table_all as SELECT TABLE_NAME, "
                + " TABLE_ROWS,CONCAT('SELECT * FROM ',TABLE_NAME) AS COMM  "
                + " FROM `information_schema`.`tables` "
                + " WHERE `table_schema` = '" + hisdb + "' order by TABLE_ROWS";
        synchronized (this) {
            my.query(csql2);
        }


        my.close();
        timer.start();


    }//GEN-LAST:event_formWindowOpened

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus

        try {
            Ini ini = new Ini(new File("config.ini"));
            hishost = ini.get("Connection", "host");
            hisuser = ini.get("Connection", "user");
            hispass = ini.get("Connection", "pass");
            hisdb = ini.get("Connection", "database");
            txtPcuCode.setText(ini.get("PCUCODE", "pcucode"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        SimpleMySQL my = new SimpleMySQL();

        if (my.connect(hishost, hisuser, hispass, hisdb, "tis-620")) {
            lbStatusHis.setForeground(Color.GREEN);
            lbStatusHis.setText(hisdb + " : Readey.");
        } else {
            lbStatusHis.setForeground(Color.red);
            lbStatusHis.setText(hisdb + " : No Readey.!!");
        }
        my.close();
    }//GEN-LAST:event_formWindowGainedFocus

    //sender
    private class Sender implements Runnable {

        public void run() {

            try {

                socket.connect(sockaddr);

                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);//*

                brinp = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String f = brinp.readLine();
                log_com.append(f);
                log_com.setCaretPosition(log_com.getDocument().getLength());
                //System.out.println("+++++++++");

                Vector<String> vector = new Vector<String>();
                SimpleMySQL mysql = new SimpleMySQL();

                mysql.connect(hishost, hisuser, hispass, "PLKDW", "tis-620");

                String sql = "SELECT * FROM table_all order by TABLE_NAME";
                ResultSet result = mysql.query(sql);
                //System.out.println(mysql.num_cols(result));
                try {
                    while (result.next()) {
                        if (!result.getString(1).equals("doraemon")) {
                            vector.add(result.getString(3));
                        }
                    }
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.append("\n" + new Date() + ":e1:" + e.getMessage());
                    log.setCaretPosition(log.getDocument().getLength());
                }
                mysql.select_db(hisdb);
                Iterator<String> it = vector.iterator();
                int i = 0;
                while (it.hasNext()) {
                    i++;
                    if (isSending) {
                        String value_sql = it.next();
                        String table_name[] = value_sql.split(" ");
                        try {
                            ResultSet rs = mysql.query(value_sql);
                            //ResultSetMetaData rsmd = rs.getMetaData();

                            int count = 0;

                            int num_col = mysql.num_cols(rs);
                            while (rs.next()) {
                                synchronized (this) {
                                    if (isSending) {
                                        try {
                                            Thread.sleep(10);
                                            Vector v = new Vector();
                                            v.add(pcucode);
                                            v.add(table_name[3]);
                                            //v.add(rs.getRow());

                                            for (int n = 1; n <= num_col; n++) {
                                                //String colname = rsmd.getColumnName(n);
                                                //String sss = "UPDATE " + table_name[3] + " SET " + colname + " = TRIM(TRAILING '\r\n' FROM " + colname + ")";
                                                //System.out.println(sss);
                                                //mysql.query(sss);
                                                v.add(rs.getString(n));

                                            }

                                            out.println(v);
                                            txtTable.setText("" + v);
                                            count++;
                                            v = null;
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                            log.append("\n" + new Date() + ":e2:" + e.getMessage());
                                            log.setCaretPosition(log.getDocument().getLength());
                                        }
                                    }
                                }


                                if (brinp.readLine().equals(null)) {
                                    isSending = false;
                                }

                            }
                            rs.close();
                            cli.append(i + ": " + value_sql + "  [" + count + "]\n");
                            cli.setCaretPosition(cli.getDocument().getLength());
                            txtCountTable.setText("TABLES: " + String.valueOf(i));
                            if (i % 100 == 0) {
                                cli.setText("");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                            log.append("\n" + new Date() + ":e3:" + e.getMessage());
                            log.setCaretPosition(log.getDocument().getLength());
                        }
                    }

                }

                log.append("\n" + new Date() + ":" + "Sync Success..");
                log.setCaretPosition(log.getDocument().getLength());

                isSending = false;
                mysql.close();
                socket.close();
                btnStart.setEnabled(true);



            } catch (IOException e) {

                socket = null;
                sockaddr = null;
                isSending = false;

                log.append("\n" + new Date() + ":e4:" + e.getMessage());
                log.setCaretPosition(log.getDocument().getLength());

                e.printStackTrace();
                btnStart.setEnabled(true);

            }


        }
    }
    //end sender
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHideWin;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JTextArea cli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbStatusHis;
    private javax.swing.JTextArea log;
    private javax.swing.JTextArea log_com;
    private javax.swing.JMenuItem mnuAbout;
    private javax.swing.JMenu mnuAboutFF;
    private javax.swing.JMenuItem mnuClose;
    private javax.swing.JMenuItem mnuHisCon;
    private javax.swing.JLabel txtCountTable;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextField txtPcuCode;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtTable;
    // End of variables declaration//GEN-END:variables
}
