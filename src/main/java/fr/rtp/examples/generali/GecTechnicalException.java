package fr.rtp.examples.generali;

public class GecTechnicalException extends RuntimeException {

    private static final long serialVersionUID = 2123984441321449397L;

    public GecTechnicalException(String message, TechnicalException e, String errorServiceTechnical) {
        super(message, e);
    }

    public static final String ERROR_SERVICE_TECHNICAL = null;

    public Object getKey() {
        return null;
    }

}
