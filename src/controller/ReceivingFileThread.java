/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.PrivateChat;

public class ReceivingFileThread extends Thread {

    private final int BUFFER_SIZE = 1024;
    StringTokenizer tokenizer;
    BufferedWriter bw;
    BufferedReader br;
    Socket socketOfReceiver;
    String myDownloadFolder;
    String fileName;

    public ReceivingFileThread(Socket socketOfReceiver, String myDownloadFolder, String fileName, PrivateChat pc) {
        this.socketOfReceiver = socketOfReceiver;
        this.myDownloadFolder = myDownloadFolder;
        this.fileName = fileName;

        try {
            br = new BufferedReader(new InputStreamReader(socketOfReceiver.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socketOfReceiver.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ReceivingFileThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendToServer(String line) {
        try {
            this.bw.write(line);
            this.bw.newLine();
            this.bw.flush();
        } catch (java.net.SocketException e) {
            JOptionPane.showMessageDialog(null, "Server is close, can't send message!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        try {
            this.sendToServer("CMD_DOWNLOADFILE|"+fileName);
            bis = new BufferedInputStream(socketOfReceiver.getInputStream());
            fos = new FileOutputStream(myDownloadFolder + "\\" + fileName);

            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while((count = bis.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }

            JOptionPane.showMessageDialog(null, "File downloaded to\n"+myDownloadFolder + "\\" + fileName, "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(ReceivingFileThread.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("This socket for receiving file has benn close, so you don't need to worry about that!");
        } finally {
            try {
                if(bis != null) bis.close();
                if(fos != null) fos.close();
                socketOfReceiver.close();
            } catch (IOException ex) {
                Logger.getLogger(ReceivingFileThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
