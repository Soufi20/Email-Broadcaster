package javaapplication14;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class email extends javax.swing.JFrame {

    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";
    private static final String USERNAME = "soufioussama64@gmail.com";
    private static final String PASSWORD = "jqdz dkvk bdig snvc";
    private static final String FROM_EMAIL = "soufioussama64@gmail.com";
    public email() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(102, 204, 255));
        jButton1.setText("choose file\n");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setText("SEND\n");
        jButton2.setActionCommand("SEND");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
            JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(email.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            jTextField1.setText(selectedFile.getAbsolutePath());
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
       JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(email.this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
               jTextField1.setText(selectedFile.getAbsolutePath());
            }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String filePath = jTextField1.getText();
        String customMessage = jTextField2.getText();
        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(email.this, "Please choose exel file");
            return;
        }
        sendEmailsFromExcel(filePath, customMessage);
    }//GEN-LAST:event_jButton2ActionPerformed

 private void sendEmailNotification(String recipientEmail, String name, String secname, String user,String pass, String customMessage) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("TP3 RESEUX");

            String emailContent = String.format("%s\nName: %s\nSecondName: %s\nUserName: %s\nPASSWORD: %s\n",
                    customMessage,name, secname,user, pass);
            message.setText(emailContent);

            Transport.send(message);

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(this, "Error send " + recipientEmail + ": " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void sendEmailsFromExcel(String excelFilePath, String customMessage) {
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

           Sheet sheet = workbook.getSheetAt(0);
for (int i = 1; i <= sheet.getLastRowNum(); i++) {
    Row row = sheet.getRow(i);

              Cell emailCell = row.getCell(2);
          Cell name = row.getCell(0);
                     Cell sename = row.getCell(1);
              Cell username = row.getCell(3);
             Cell passw = row.getCell(4);


                if (emailCell != null && name != null && sename != null && username != null && passw !=null) {
                    String recipientEmail = emailCell.getStringCellValue();
                    String Name = name.getStringCellValue();
                    String secname = sename.getStringCellValue();
                    String user = username.getStringCellValue();
                    String pass = passw.getStringCellValue();

                    sendEmailNotification(recipientEmail, Name, secname, user, pass, customMessage);
                }
            }
            JOptionPane.showMessageDialog(this, "Emails sent sucss");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error read file " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
     public static void main(String args[]) {
        EventQueue.invokeLater(() -> new email().setVisible(true));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
