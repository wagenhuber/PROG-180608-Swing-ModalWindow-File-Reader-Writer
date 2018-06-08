package de.wagenhuber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class fenster extends JFrame {

    private JMenuItem jmitemBeenden;
    private JMenu jmenuDatei;
    private JMenuBar jmenuBar;
    private JTable table;
    private MyTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton jbtSave, jbtnLoad;
    private JPanel jPanelNorth;
    private JFileChooser jFileChooser;

    public fenster() throws HeadlessException {

        initMenu();
        initComponents();
        initEvents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }

    private void initMenu() {
        jmitemBeenden = new JMenuItem("Exit");
        jmenuDatei = new JMenu("Datei");
        jmenuDatei.add(jmitemBeenden);
        jmenuBar = new JMenuBar();
        jmenuBar.add(jmenuDatei);
        this.setJMenuBar(jmenuBar);
    }

    private void initComponents() {

        jFileChooser = new JFileChooser();

        tableModel = new MyTableModel();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        this.add(scrollPane);

        jPanelNorth = new JPanel();
        jPanelNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
        jbtSave = new JButton("Save");
        jPanelNorth.add(jbtSave);
        jbtnLoad = new JButton("Load");
        jPanelNorth.add(jbtnLoad);
        this.add(jPanelNorth, BorderLayout.NORTH);


    }


    public void initEvents() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                beenden();
            }
        });


        jmitemBeenden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beenden();
            }
        });

        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    speichern();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jbtnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    laden();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void laden() throws IOException {
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File file = jFileChooser.getSelectedFile();
        tableModel.laden(file);
    }

    private void speichern() throws IOException {
        int result = jFileChooser.showSaveDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File file = jFileChooser.getSelectedFile();

        tableModel.speichern(file);
    }

    private void beenden() {
        System.exit(NORMAL);
    }


    public static void main(String[] args) {
        new fenster();
    }
}
