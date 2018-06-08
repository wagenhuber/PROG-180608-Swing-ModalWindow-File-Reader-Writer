package de.wagenhuber;

import java.util.Objects;

public class Auto {

    private String eigentuemer;
    private String kennzeichen;
    private String model;
    private int ps;

    public Auto(String kennzeichen, String model, String eigentuemer, int ps) {
        this.kennzeichen = kennzeichen;
        this.model = model;
        this.eigentuemer = eigentuemer;
        this.ps = ps;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEigentuemer() {
        return eigentuemer;
    }

    public void setEigentuemer(String eigentuemer) {
        this.eigentuemer = eigentuemer;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return ps == auto.ps &&
                Objects.equals(kennzeichen, auto.kennzeichen) &&
                Objects.equals(model, auto.model) &&
                Objects.equals(eigentuemer, auto.eigentuemer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(kennzeichen, model, eigentuemer, ps);
    }

    @Override
    public String toString() {
        return "Auto{" +
                "kennzeichen='" + kennzeichen + '\'' +
                ", model='" + model + '\'' +
                ", eigentuemer='" + eigentuemer + '\'' +
                ", ps=" + ps +
                '}';
    }
}
