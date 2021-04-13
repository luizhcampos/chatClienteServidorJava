package com.chat.servidor;

import com.chat.servidor.thread.RecebeMensagemCliente;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    private List<RecebeMensagemCliente> clientes = new ArrayList<>();

    public static void main(String[] args) {

        Servidor servidor = new Servidor();
        servidor.aguardaConexoes();

    }

    public void aguardaConexoes(){

        try (ServerSocket server = new ServerSocket(8080)){
            while (true) {
                System.out.println("Aguardando Conexões ...");
                Socket socket = server.accept();

                RecebeMensagemCliente recebeMensagemCliente = new RecebeMensagemCliente(socket, this);
                new Thread(recebeMensagemCliente).start();

                this.clientes.add(recebeMensagemCliente);
                System.out.println("Novo Cliente Conectado ...");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensagensClientes (String mensagem) {
        for (RecebeMensagemCliente cliente : this.clientes){
            cliente.enviarMensagem(mensagem);
        }
    }
}
