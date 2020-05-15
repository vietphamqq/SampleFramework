package utils.factory;

import java.util.Arrays;
import java.util.Optional;

public enum Run {
    BROWSER_STACK("bs", "browser stack", "BrowserStack"),
    LOCAL("local", "lc");

    private String[] text;

    Run(String... text) {
        this.text = text;
    }

    public static Run fromText(String text) {
        Optional<Run> temp = Arrays.stream(values())
                .filter(ru -> Arrays.stream(ru.text).anyMatch(x -> x.equalsIgnoreCase(text)))
                .findFirst();
        return temp.get();
    }

    public String[] getText() {
        return this.text;
    }
}

