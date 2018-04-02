package loader;

enum ErrorMessage {
    FAIL_LOAD_TEXTURE("Couldn't to load %s texture");

    private final String massageFormat;

    ErrorMessage(String messageFormat) {
        this.massageFormat = messageFormat;
    }

    public String format(String texturePath) {
        return String.format(massageFormat, texturePath);
    }
}
