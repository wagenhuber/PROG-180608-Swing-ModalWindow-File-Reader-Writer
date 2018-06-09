package de.wagenhuber;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyTableModel extends AbstractTableModel {

    private List<Auto> autoListe;
    private String[] columns = {"Eigentuemer", "Kennzeichen", "Modell", "Ps"};

    public MyTableModel() {
        this.autoListe = new ArrayList<>();
        //autoListe.add(new Auto("RO-GW-321", "Audi A3", "Wagenhuber", 105));

    }

    @Override
    public int getRowCount() {
        return autoListe.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Auto auto = autoListe.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return auto.getEigentuemer();
            case 1:
                return auto.getKennzeichen();
            case 2:
                return auto.getModel();
            case 3:
                return auto.getPs();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }


    public void speichern(File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        for (int i = 0; i < columns.length; i++) {
            bw.write(columns[i] + ";");
        }
        bw.newLine();

        for (Auto auto : autoListe) {
            bw.write(auto.getEigentuemer() + ";" + auto.getKennzeichen() + ";" + auto.getModel() + ";" + auto.getPs());
            bw.newLine();
        }
        if (bw != null) {
            bw.close();
        }
    }

    public void laden(File file) throws IOException {
        BufferedReader bw = new BufferedReader(new FileReader(file));
        String line = bw.readLine();//Lesen für ersten Durchlauf!
        while (line != null) {
            String splits[] = line.split(";");
            Auto auto = new Auto(splits[0], splits[1], splits[2], Integer.valueOf(splits[3]));
            System.out.println("Auto aus Datei:");
            System.out.println(auto.toString());
            autoListe.add(auto);
            line = bw.readLine();//erneutes Lesen bereits für nächsten Durchlauf!
        }

        if (bw != null) {
            bw.close();
        }
        this.fireTableDataChanged();
    }


    public void addAutoToList(Auto auto) {
        this.autoListe.add(auto);
        this.fireTableDataChanged();
    }
}
