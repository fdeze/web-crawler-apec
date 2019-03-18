                 package fr.fabien.webcrawler.apec.internal.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Critere
{
	@JsonProperty("pointGeolocDeReference")
    private Object pointGeolocDeReference;

    private String motsCles;

    private String[] niveauxExperience;

    private String activeFiltre;

    private String[] typesConvention;

    private Pagination pagination;

    private String[] typesContrat;

    private String typeClient;

    private String[] fonctions;

    private String[] lieux;

    private Sorts[] sorts;

    private String[] secteursActivite;

	@JsonProperty("pointGeolocDeReference")
    public Object getPointGeolocDeReference ()
    {
        return pointGeolocDeReference;
    }
	
	@JsonProperty("pointGeolocDeReference")
    public void setPointGeolocDeReference (Object pointGeolocDeReference)
    {
        this.pointGeolocDeReference = pointGeolocDeReference;
    }

    public String getMotsCles ()
    {
        return motsCles;
    }

    public void setMotsCles (String motsCles)
    {
        this.motsCles = motsCles;
    }

    public String[] getNiveauxExperience ()
    {
        return niveauxExperience;
    }

    public void setNiveauxExperience (String[] niveauxExperience)
    {
        this.niveauxExperience = niveauxExperience;
    }

    public String getActiveFiltre ()
    {
        return activeFiltre;
    }

    public void setActiveFiltre (String activeFiltre)
    {
        this.activeFiltre = activeFiltre;
    }

    public String[] getTypesConvention ()
    {
        return typesConvention;
    }

    public void setTypesConvention (String[] typesConvention)
    {
        this.typesConvention = typesConvention;
    }

    public Pagination getPagination ()
    {
        return pagination;
    }

    public void setPagination (Pagination pagination)
    {
        this.pagination = pagination;
    }

    public String[] getTypesContrat ()
    {
        return typesContrat;
    }

    public void setTypesContrat (String[] typesContrat)
    {
        this.typesContrat = typesContrat;
    }

    public String getTypeClient ()
    {
        return typeClient;
    }

    public void setTypeClient (String typeClient)
    {
        this.typeClient = typeClient;
    }

    public String[] getFonctions ()
    {
        return fonctions;
    }

    public void setFonctions (String[] fonctions)
    {
        this.fonctions = fonctions;
    }

    public String[] getLieux ()
    {
        return lieux;
    }

    public void setLieux (String[] lieux)
    {
        this.lieux = lieux;
    }

    public Sorts[] getSorts ()
    {
        return sorts;
    }

    public void setSorts (Sorts[] sorts)
    {
        this.sorts = sorts;
    }

    public String[] getSecteursActivite ()
    {
        return secteursActivite;
    }

    public void setSecteursActivite (String[] secteursActivite)
    {
        this.secteursActivite = secteursActivite;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pointGeolocDeReference = "+pointGeolocDeReference+", motsCles = "+motsCles+", niveauxExperience = "+niveauxExperience+", activeFiltre = "+activeFiltre+", typesConvention = "+typesConvention+", pagination = "+pagination+", typesContrat = "+typesContrat+", typeClient = "+typeClient+", fonctions = "+fonctions+", lieux = "+lieux+", sorts = "+sorts+", secteursActivite = "+secteursActivite+"]";
    }
}