package Classes;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.*;

class Client extends Frame implements ActionListener, WindowListener {
    TextField tf, tf1;
    TextField inp1, inp1_2, inp2, inp2_2, inp3, inp3_2, inp4, inp4_2, inpd, inpd_2;
    TextArea ta;
    Label la, la1, la2, la3, la4;
    Socket sock = null;
    InputStream is = null;
    OutputStream os = null;

    public static void main(String args[]) {
        Client c = new Client();
        c.GUI();
    }

    private void GUI() {
        setTitle("Application | LAB4.3");
        tf = new TextField("127.0.0.1");
        tf1 = new TextField("1024");

        inp1 = new TextField();
        inp1_2 = new TextField();
        inp2 = new TextField();
        inp2_2 = new TextField();
        inp3 = new TextField();
        inp3_2 = new TextField();
        inp4 = new TextField();
        inp4_2 = new TextField();
        inpd = new TextField();
        inpd_2 = new TextField();

        ta = new TextArea();
        la = new Label("IP: ");
        la1 = new Label("PORT: ");
        la2 = new Label("Coordinates:");
        la3 = new Label("Result:");
        la4 = new Label(" ");
        Button btn = new Button("Connect");
        Button btn1 = new Button("Send");
        tf.setBounds(200, 50, 70, 25);
        tf1.setBounds(330, 50, 70, 25);

        inp1.setBounds(150, 200, 50, 25);
        inp1_2.setBounds(150,225,50, 25);
        inp2.setBounds(210, 200, 50, 25);
        inp2_2.setBounds(210,225,50, 25);
        inp3.setBounds(270, 200, 50, 25);
        inp3_2.setBounds(270,225,50, 25);
        inp4.setBounds(330, 200, 50, 25);
        inp4_2.setBounds(330,225,50, 25);
        inpd.setBounds(390, 200, 50, 25);
        inpd_2.setBounds(390,225,50, 25);


        ta.setBounds(150, 350, 150, 100);
        btn.setBounds(50, 50, 70, 25);
        btn1.setBounds(50, 200, 70, 25);
        la.setBounds(130, 50, 150, 25);
        la1.setBounds(280, 50, 150, 25);
        la2.setBounds(150, 150, 150, 75);
        la3.setBounds(160, 325, 150, 25);
        la4.setBounds(600, 10, 150, 25);
        add(tf);
        add(tf1);
        add(inp1);
        add(inp1_2);
        add(inp2);
        add(inp2_2);
        add(inp3);
        add(inp3_2);
        add(inp4);
        add(inp4_2);
        add(inpd);
        add(inpd_2);
        add(btn);
        add(btn1);
        add(ta);
        add(la);
        add(la1);
        add(la2);
        add(la3);
        add(la4);
        setSize(550, 500);
        setVisible(true);
        addWindowListener(this);
        btn.addActionListener(al);
        btn1.addActionListener(this);
        inp1.getText();
        inp1_2.getText();
        inp2.getText();
        inp2_2.getText();
        inp3.getText();
        inp3_2.getText();
        inp4.getText();
        inp4_2.getText();
        inpd.getText();
        inpd_2.getText();
    }

    public void windowClosing(WindowEvent we) {
        if (sock != null && !sock.isClosed()) { // если сокет не пустой и сокет открыт
            try {
                sock.close(); // сокет закрывается
            } catch (IOException e) { }
        }
        this.dispose();
    }

    public void windowActivated(WindowEvent we) {} ;
    public void windowClosed(WindowEvent we) {};
    public void windowDeactivated(WindowEvent we) {};
    public void windowDeiconified(WindowEvent we) {} ;
    public void windowIconified(WindowEvent we) {};
    public void windowOpened(WindowEvent we) { } ;
    public void actionPerformed(ActionEvent e) {
    if (sock == null) {
        return;
    }
    try {
        is = sock.getInputStream();
        os = sock.getOutputStream();
        String numbers = "";
        numbers += inp1.getText() + " ";
        numbers += inp1_2.getText() + " ";
        numbers += inp2.getText() + " ";
        numbers += inp2_2.getText() + " ";
        numbers += inp3.getText() + " ";
        numbers += inp3_2.getText() + " ";
        numbers += inp4.getText() + " ";
        numbers += inp4_2.getText() + " ";
        numbers += inpd.getText() + " ";
        numbers += inpd_2.getText() + " ";
        os.write(numbers.getBytes());
        byte[] bytes = new byte[1024];
        is.read(bytes);
        String str = new String(bytes, "UTF-8");

        ta.setText(null);
        ta.append(str);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

    public void actionPerformed2(ActionEvent e) {}
    ActionListener al = new ActionListener() { //событие на нажатие кнопки
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                sock = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
            }
            catch (NumberFormatException e) { }
            catch (UnknownHostException e) { }
            catch (IOException e) { }
        }
    };
}