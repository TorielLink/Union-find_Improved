package subClasses;

/**
 * Class for FriendshipListBigDataTest
 */
public class PrenomStat implements Comparable<PrenomStat> {
    private final String name;
    private final int sex;
    private final int timesGiven;

    /**
     * Constructor
     * @param name the name
     * @param sex the sex (1 for boy and 2 for girl)
     * @param timesGiven the number of times the name was given
     */
    public PrenomStat(String name, int sex, int timesGiven) {
        this.name = name;
        this.sex = sex;
        this.timesGiven = timesGiven;
    }

    /**
     * Function to compare two PrenomStat
     * @param prenomStat the object to be compared
     * @return 0 if equal
     */
    @Override
    public int compareTo(PrenomStat prenomStat) {
        return this.name.compareTo(prenomStat.name);
    }
}
