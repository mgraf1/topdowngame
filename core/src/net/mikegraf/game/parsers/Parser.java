package net.mikegraf.game.parsers;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import net.mikegraf.game.exceptions.ConfigFormatException;

/* Abstract base class for all parsers in the project. */
public abstract class Parser<T> {

    protected String filePath;

    /* Parse the given xml file and return an object. */
    public T parseFile(String filePath) throws IOException, ConfigFormatException {

        T retval = null;

        this.filePath = filePath;
        FileHandle fileHandle = Gdx.files.internal(filePath);
        XmlReader reader = new XmlReader();
        Element root = reader.parse(fileHandle);

        // Let subclasses parse the file however they wish.
        retval = handleParsing(root);

        return retval;
    }

    protected String parseElement(Element parent, String elementName, boolean required) throws ConfigFormatException {
        Element element = parent.getChildByName(elementName);
        if (element == null) {
            if (required) {
                throw new ConfigFormatException("Element missing: " + elementName);
            } else {
                return null;
            }
        }
        String stringToReturn = element.getText();
        if (stringToReturn.length() == 0) {
            throw new ConfigFormatException("Element empty: " + elementName);
        }
        return stringToReturn;
    }

    protected int parseIntElement(Element parent, String elementName, boolean required) throws ConfigFormatException {
        String parsedElement = parseElement(parent, elementName, required);
        return Integer.parseInt(parsedElement);
    }

    protected float parseFloatElement(Element parent, String elementName, boolean required)
            throws ConfigFormatException {
        String parsedElement = parseElement(parent, elementName, required);
        return Float.parseFloat(parsedElement);
    }

    protected abstract T handleParsing(Element root) throws IOException, ConfigFormatException;
}
