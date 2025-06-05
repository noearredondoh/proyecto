package com.uaz.invexort;

public class VentasMes {
    private String mes;
    private double total;

    public VentasMes(String mes, double total) {
        this.mes = mes;
        this.total = total;
    }

    // Getters y setters
    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
