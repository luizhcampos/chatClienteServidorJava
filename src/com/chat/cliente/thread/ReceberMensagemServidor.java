package com.chat.cliente.thread;

import com.chat.cliente.gui.JanelaGui;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class ReceberMensagemServidor implements Runnable{

    private Socket socket;
    private JanelaGui janela;

    public ReceberMensagemServidor(Socket socket, JanelaGui janela) {
        this.socket = socket;
        this.janela = janela;
    }

    @Override
    public void run() {
        while (true) {
            try {
                InputStream is = this.socket.getInputStream();
                DataInput dis = new DataInputStream(is);
                String msgRecebida = dis.readUTF();

                janela.adicionaMensagem(msgRecebida);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
