package cz.cvut.fel.via.zboziforandroid.client;

public enum ERegions {
    PRAHA("CZ010"), STREDOCESKY_KRAJ("CZ020"), JIHOCESKY_KRAJ("CZ031"), 
    PLZENSKY_KRAJ("CZ032"), KARLOVARSKY_KRAJ("CZ041"), USTECKY_KRAJ("CZ042"),
    LIBERECKY_KRAJ("CZ051"), KRALOVEHRADECKY_KRAJ("CZ052"), 
    PARDUBICKY_KRAJ("CZ053"), VYSOCINA("CZ061"), JIHOMORAVSKY_KRAJ("CZ062"),
    OLOMOUCKY_KRAJ("CZ071"), ZLINSKY_KRAJ("CZ072"), MORAVSKOSLEZSKY_KRAJ("CZ080");
    
    public final String text;
    
    private ERegions(final String text) {
        this.text = text;
    }
}