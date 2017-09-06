package br.ufjf.dcc171;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListDataEvent;

public class JanelaListaPessoas extends JFrame {
    private List<Pessoa> pessoas = new ArrayList<>();
    
    
    private final JLabel lblNome = new JLabel("Nome:");
    private final JTextField txtNome = new JTextField(10);
    private final JLabel lblIdade = new JLabel("Idade:");
    private final JTextField txtIdade = new JTextField(10);
    private final JButton btnExcluir = new JButton("Excluir");
    private final PessoasListModel modelo = new PessoasListModel(pessoas);
    private final JList lstNumeros = new JList(modelo);

    public JanelaListaPessoas() throws HeadlessException {
        super("Lista");
        setLayout(new BorderLayout(5, 5));
        JPanel pnlNome = new JPanel();
        pnlNome.setLayout(new BorderLayout(5, 5));
        pnlNome.add(lblNome, BorderLayout.WEST);
        pnlNome.add(txtNome, BorderLayout.CENTER);
        JPanel pnlIdade = new JPanel();
        pnlIdade.setLayout(new BorderLayout(5, 5));
        pnlIdade.add(lblIdade, BorderLayout.WEST);
        pnlIdade.add(txtIdade, BorderLayout.CENTER);
        JPanel pnlEntrada = new JPanel(new FlowLayout());
        pnlEntrada.add(pnlNome);
        pnlEntrada.add(pnlIdade);
        add(pnlEntrada, BorderLayout.NORTH);
        add(btnExcluir, BorderLayout.SOUTH);
        add(new JScrollPane(lstNumeros), BorderLayout.CENTER);

        
        
        for (int i = 0; i < 3; i++) {
            Pessoa p = new Pessoa();
            p.setNome("Pessoa "+i);
            p.setIdade(18+i);
            pessoas.add(p);
        }

        txtNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIdade.requestFocus();
            }
        });
        
        txtIdade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtNome.getText().isEmpty()){
                    txtNome.requestFocus();
                    return;
                }
                Integer idade = Integer.parseInt(txtIdade.getText());
                String nome = txtNome.getText();
                Pessoa p =new Pessoa();
                p.setNome(nome);
                p.setIdade(idade);
                pessoas.add(p);
                txtNome.setText("");
                txtIdade.setText("");
                txtNome.requestFocus();
                //lstNumeros.updateUI();
                modelo.getDataListeners().get(0).contentsChanged(new ListDataEvent(p, ListDataEvent.INTERVAL_ADDED, pessoas.size()-1, pessoas.size()-1));
            }
        });
        

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Pessoa> selecionados = lstNumeros.getSelectedValuesList(); 
                for (Pessoa selecionado : selecionados) {
                    pessoas.remove(selecionado);
                }
                lstNumeros.clearSelection();
                lstNumeros.updateUI();
            }
        });
    }

}
