package de.wagenhuber;


import sun.plugin2.ipc.windows.WindowsEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class addAuto extends JDialog {

    private JTextField jtfEigentuemer, jtfKennzeichen, jtfmodel, jtfps;
    private JButton jbtnSave, jbtnCancel;
    private JPanel jpCenter1, jpCenter2, jpCenter3, jpCenter4, jpCenter5;
    private MyTableModel tableModel;

    public addAuto(MyTableModel tableModel) throws HeadlessException {

        this.setModal(true);
        this.tableModel = tableModel;
        this.setLayout(new GridLayout(5, 1));

        initComponents();
        initEvents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 400);
        this.setVisible(true);

    }

    private void initEvents() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                beenden();
            }
        });

        jbtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beenden();
            }
        });

        jbtnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speichern();
            }
        });
    }

    private void beenden() {
        this.dispose();
    }

    private void speichern() {
        tableModel.addAutoToList(new Auto(jtfEigentuemer.getText(), jtfKennzeichen.getText(), jtfmodel.getText(), Integer.valueOf(jtfps.getText())));
        beenden();
    }


    private void initComponents() {
        jpCenter1 = new JPanel();
        jpCenter1.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpCenter2 = new JPanel();
        jpCenter2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpCenter3 = new JPanel();
        jpCenter3.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpCenter4 = new JPanel();
        jpCenter4.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpCenter5 = new JPanel();
        jpCenter5.setLayout(new FlowLayout(FlowLayout.LEFT));

        jtfEigentuemer = new JTextField(10);
        jtfKennzeichen = new JTextField(10);
        jtfmodel = new JTextField(10);
        jtfps = new JTextField(10);

        jbtnCancel = new JButton("Cancel");
        jbtnSave = new JButton("Save");

        jpCenter1.add(new JLabel("Eigentümer:"));
        jpCenter1.add(jtfEigentuemer);
        jpCenter2.add(new JLabel("Kennzeichen:"));
        jpCenter2.add(jtfKennzeichen);
        jpCenter3.add(new JLabel("Modell:"));
        jpCenter3.add(jtfmodel);
        jpCenter4.add(new JLabel("Ps:"));
        jpCenter4.add(jtfps);
        jpCenter5.add(jbtnSave);
        jpCenter5.add(jbtnCancel);

        this.add(jpCenter1);
        this.add(jpCenter2);
        this.add(jpCenter3);
        this.add(jpCenter4);
        this.add(jpCenter5);

    }


}
