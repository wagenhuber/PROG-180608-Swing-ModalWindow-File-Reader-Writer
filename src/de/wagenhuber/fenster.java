package de.wagenhuber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class fenster extends JFrame {

    private JMenuItem jmitemBeenden, jmitemNeu;
    private JMenu jmenuDatei;
    private JMenuBar jmenuBar;
    private JTable table;
    private MyTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton jbtSave, jbtnLoad, jbtnNeuerDatensatz, jbtnDatensatzLoeschen, jbtnLoadFromDB;
    private JPanel jPanelNorth;
    private JFileChooser jFileChooser;
    private DAO dao;


    public fenster() throws HeadlessException, SQLException {

        initMenu();
        initComponents();
        initEvents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }

    private void initMenu() {
        jmitemNeu = new JMenuItem("Neu");
        jmitemBeenden = new JMenuItem("Exit");
        jmenuDatei = new JMenu("Datei");
        jmenuDatei.add(jmitemNeu);
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
        jbtnLoadFromDB = new JButton("Load from DB");
        jPanelNorth.add(jbtnLoadFromDB);
        jbtnNeuerDatensatz = new JButton("Neuer Datensatz");
        jPanelNorth.add(jbtnNeuerDatensatz);
        jbtnDatensatzLoeschen = new JButton("Löschen");
        jPanelNorth.add(jbtnDatensatzLoeschen);

        this.add(jPanelNorth, BorderLayout.NORTH);


    }


    public void initEvents() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                beenden();
            }
        });

        jbtnLoadFromDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DAO(tableModel);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });


        jbtnDatensatzLoeschen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loeschen();
            }
        });

        jbtnNeuerDatensatz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new addAuto(tableModel);
            }
        });

        jmitemNeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                neu();
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

    private void loeschen() {
        int rowCount = table.getSelectedRow();
        String bezeichnung = ((String) tableModel.getValueAt(rowCount, 0));
        tableModel.loeschen(bezeichnung);
    }

    private void neu() {
        int result = JOptionPane.showConfirmDialog(this, "Nicht gespeichert Daten gehen verloren...", "Neue Tabelle anlegen", JOptionPane.YES_NO_OPTION);
        if (result == 1) {
            return;
        }
        tableModel = new MyTableModel();
        table.setModel(tableModel);
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


    public static void main(String[] args) throws SQLException {
        new fenster();
    }
}
