package net.cortexmodders.atomtech.element;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

import net.cortexmodders.atomtech.lib.ATLogger;
import argo.jdom.JdomParser;
import argo.jdom.JsonNode;
import argo.jdom.JsonRootNode;
import argo.jdom.JsonStringNode;
import argo.saj.InvalidSyntaxException;

import com.google.common.base.Throwables;

public class ElementCollection
{
    
    private static JdomParser parser = new JdomParser();
    private HashMap<String, Element> elements;
    
    public ElementCollection()
    {
        this.elements = new HashMap<String, Element>();
    }
    
    /**
     * Adds an Element to the collection.
     * 
     * @param e
     */
    public void addElement(final Element e)
    {
        this.elements.put(e.name, e);
    }
    
    /**
     * Get an Element by it's name.
     * 
     * @param name
     * @return
     */
    public Element getElementByName(final String name)
    {
        return this.elements.get(name);
    }
    
    /**
     * Get an Element from a wanted symbol.
     * 
     * @param symbol
     * @return
     */
    public Element getElementBySymbol(final String symbol)
    {
        // loops through elements. if it finds the element with
        // the wanted symbol, return it. if nothing is found and
        // the loop is finished, return null.
        for (Element e : this.elements.values())
            if (e.symbol == symbol)
                return e;
        return null;
    }
    
    public void parseElementInfo(final String fileName)
    {
        InputStream is = this.getClass().getResourceAsStream(fileName);
        InputStreamReader reader = new InputStreamReader(is);
        try
        {
            JsonRootNode root = parser.parse(reader);
            
            for (Entry<JsonStringNode, JsonNode> n : root.getFields().entrySet())
                new Element(n.getKey().getStringValue(), n.getValue());
            
        }
        catch (InvalidSyntaxException e)
        {
            ATLogger.severe("Failed to parse: " + fileName + ". This means you could have a corrupt mod jar.");
        }
        catch (Exception e)
        {
            throw Throwables.propagate(e);
        }
    }
}
