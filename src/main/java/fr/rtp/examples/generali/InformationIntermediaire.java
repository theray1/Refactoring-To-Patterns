package fr.rtp.examples.generali;

public class InformationIntermediaire {

    private Bureau bureau;
    private Intermediaire intermediaire;

    public InformationIntermediaire() {
    }

    public InformationIntermediaire(Bureau bureau, Intermediaire intermediaire) {
        this.bureau = bureau;
        this.intermediaire = intermediaire;
    }

    public Bureau getBureau() {
        return bureau;
    }

    public void setBureau(Bureau bureauFromGeneric) {
        bureau = bureauFromGeneric;
    }

    public Intermediaire getIntermediaire() {
        return intermediaire;
    }

    public void setIntermediaire(Intermediaire intermediaire) {
        this.intermediaire = intermediaire;
    }

}
