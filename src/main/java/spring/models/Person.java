package spring.models;

public class Person {
    private String FIO;
    private int bitrthDate;

    public Person(String FIO, int bitrthDate) {
        this.FIO = FIO;
        this.bitrthDate = bitrthDate;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getBitrthDate() {
        return bitrthDate;
    }

    public void setBitrthDate(int bitrthDate) {
        this.bitrthDate = bitrthDate;
    }
}
