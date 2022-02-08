package fr.rtp.examples.generali;

public class IGenericService {

    public InformationIntermediaire getInformationIntermediaire(String codeCompagnie, String codePortefeuille) {

        return new InformationIntermediaire(new Bureau(), new Intermediaire());
    }

}
