package compumoprojekt.httpsgithub.studonotatnik;

/**
 * Created by SAMSUNG on 2016-05-23.
 */
public class Note {
    public String Temat;
    public String Tresc;

    // Getters and setters are not required for this example.
    // GSON sets the fields directly using reflection.

    @Override
    public String toString() {
        return Temat + " - " + Tresc;
    }
}
