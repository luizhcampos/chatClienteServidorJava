package com.chat.cliente;

import com.chat.cliente.thread.ReceberMensagemServidor;
import com.chat.cliente.gui.JanelaGui;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente extends JanelaGui {

    private Socket socket;

    public static void main(String[] args) {
	    new Cliente();
        new Cliente();
        //new Cliente();
    }

    @Override
    protected boolean conectar() {
        System.out.println("Conectando no servidor ...");
        try {
            this.socket = new Socket("127.0.0.1", 8080);

            ReceberMensagemServidor receberMensagemServidor = new ReceberMensagemServidor(this.socket, this);
            new Thread(receberMensagemServidor).start();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    protected void sendMessage(String mensagem) {
        System.out.println("Envia a mensagem via socket para o servidor - " + mensagem);
        try {
            OutputStream os = this.socket.getOutputStream();
            DataOutput dos = new DataOutputStream(os);
            dos.writeUTF(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
