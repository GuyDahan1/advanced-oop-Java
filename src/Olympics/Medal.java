package Olympics;

/**
 * Represents a Medal.
 */
public class Medal {
    /**
     * Represents this medal's type.
     */
    private final MedalType type;
    private final String tournament;
    private final int year;

    /**
     * Creates a medal with the specified tournament's name,tyoe of medal, and year.
     *
     * @param Tour name of tournament.
     * @param type type of medal.
     * @param Year year of event
     * @see MedalType
     */
    public Medal(String Tour, MedalType type, int Year) {

        this.tournament = Tour;
        this.type = type;
        this.year = Year;
    }

    /**
     * Creates a medal with the specified tournament's name,type of medal, and year.
     *
     * @param Tour name of tournament.
     * @param type type of medal.
     * @see MedalType
     * year's default value is 2020
     */
    public Medal(String Tour, MedalType type) {
        this.tournament = Tour;
        this.type = type;
        this.year = 2020;
    }

    /**
     * Creates a medal with the specified tournament's name,type of medal, and year.
     * <p>
     * medal's type default value is Bronze
     * year's default value is 2020
     *
     * @param Tour name of tournament.
     */
    public Medal(String Tour) {
        this.tournament = Tour;
        this.type = MedalType.Bronze;
        this.year = 2020;
    }

    /**
     * Creates a medal with the specified tournament's name,type of medal, and year.
     * tournament's default value is ""
     * medal's type default value is Bronze
     * year's default value is 2020
     */
    public Medal() {
        this.tournament = "";
        this.type = MedalType.Bronze;
        this.year = 2020;
    }

    /**
     * Gets the medal's tournament.
     *
     * @return name of tournament.
     */
    public String getTournament() {
        return tournament;
    }

    /**
     * Gets the tournament's year.
     *
     * @return year of tournament.
     */
    public int getYear() {
        return year;
    }

    /**
     * @return readable info of Medal object.
     */
    public String toString() {
        return "\n\tTournament : " + tournament + "\n\tType : " + type.toString() + "\n\tYear : " + year;
    }

    /**
     * Gets the medal's type.
     *
     * @return this type of medal.
     * @see Medal
     */
    public MedalType getType() {
        return this.type;
    }

}
