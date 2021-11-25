package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

  public static void main(String[] args) throws IOException {
    try (ServerSocket server = new ServerSocket(9000)) {
      while (!server.isClosed()) {
        Socket socket = server.accept();
        try (OutputStream out = socket.getOutputStream();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))) {
          String word = in.readLine().substring(6).split(" ")[0];
          if (("msg=Exit").equals(word)) {
            server.close();
          }
          out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
          if (("msg=Hello").equals(word)) {
            out.write("Hello".getBytes());
          }
          if (("msg=Any").equals(word)) {
            out.write("What".getBytes());
          }
          out.flush();
        }
      }
    }
  }
}
