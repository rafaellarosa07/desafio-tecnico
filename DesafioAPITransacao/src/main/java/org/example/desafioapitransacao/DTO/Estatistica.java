package org.example.desafioapitransacao.DTO;

public class Estatistica {
    private int count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public Estatistica(int count, double sum, double avg, double min, double max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public int getCount() {
        return count;
    }
    public double getAvg() {
        return avg;
    }
    public double getMin() {
        return min;
    }
    public double getMax() {
        return max;
    }
    public double getSum() {
        return sum;
    }
}
