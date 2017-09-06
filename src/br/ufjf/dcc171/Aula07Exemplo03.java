package br.ufjf.dcc171;

import javax.swing.JFrame;

public class Aula07Exemplo03 {

    public static void main(String[] args) {
       JanelaListaPessoas janela = new JanelaListaPessoas();
       janela.setSize(400, 200);
       janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       janela.setLocationRelativeTo(null);
       janela.setVisible(true);
       
    }
    
}
