package fr.fabien.webcrawler.apec.internal.input;
                 
public class SortsVO
{
    private String type;

    private String direction;

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getDirection ()
    {
        return direction;
    }

    public void setDirection (String direction)
    {
        this.direction = direction;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [type = "+type+", direction = "+direction+"]";
    }
}
			
			