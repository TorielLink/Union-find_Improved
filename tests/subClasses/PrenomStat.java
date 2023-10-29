package subClasses;

public class PrenomStat implements Comparable<PrenomStat> {
    private final String name;
    private final int sex;
    private final int timesGiven;

    public PrenomStat(String name, int sex, int timesGiven) {
        this.name = name;
        this.sex = sex;
        this.timesGiven = timesGiven;
    }

    @Override
    public int compareTo(PrenomStat prenomStat) {
        return this.name.compareTo(prenomStat.name);
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public int getTimesGiven() {
        return timesGiven;
    }
}
