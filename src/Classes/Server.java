package Classes;

import java.net.*;
import java.io.*;

class Server {
    static int countclients = 0;
    public static void main(String args[]) throws IOException {
        ServerSocket sock = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            sock = new ServerSocket(1024);
            while (true) {
                Socket client = sock.accept();
                countclients++;
                System.out.println("Client " + countclients + " connected");
                is = client.getInputStream();
                os = client.getOutputStream();
                boolean flag = true;
                while (flag) {
                    byte[] bytes = new byte[1024];
                    is.read(bytes);
                    String str = new String(bytes, "UTF-8");
                    String[] numbers = str.split(" ");
                    String m = "";
                    bytes = new byte[1024];

                    int[] dots = new int[10];
                    for (int i = 0; i < numbers.length - 1; i++) {
                        System.out.println("Client's number: " + numbers[i]);
                        dots[i] = Integer.parseInt(numbers[i]);
                    }
                    // 0-7 - rect     8-9 - dot

                    m = "Isn't";
                    if ((dots[8] <= dots[2]) && (dots[8] <= dots[6])
                    && (dots[8] >= dots[0]) && dots[8] >= dots[4]) {
                        if ((dots[9] <= dots[5]) && (dots[9] <= dots[7])
                                && (dots[9] >= dots[1]) && dots[9] >= dots[3]) {
                            m = "In";
                        }
                    }

                    bytes = m.getBytes();
                    os.write(bytes);
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        }
        finally {
            is.close();
            os.close();
            sock.close();
            System.out.println("Client " + countclients + " disconnected");
        }
    }
}