import java.util.prefs.Preferences;

public class FileDestination {

    Preferences prefs = Preferences.userNodeForPackage(FileDestination.class);

    String fileDestination;

    public void setFileDestination(String FD) {
        prefs.put("file location", FD);
    }

    public String getFileDestination() {
        String defaultVal = "";
        fileDestination = prefs.get("file location", defaultVal);
        return fileDestination;
    }
}