package fr.fabien.webcrawler.apec.internal.input;
               
public class Pagination
{
    private String startIndex;

    private String range;

    public String getStartIndex ()
    {
        return startIndex;
    }

    public void setStartIndex (String startIndex)
    {
        this.startIndex = startIndex;
    }

    public String getRange ()
    {
        return range;
    }

    public void setRange (String range)
    {
        this.range = range;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [startIndex = "+startIndex+", range = "+range+"]";
    }
}
			
			