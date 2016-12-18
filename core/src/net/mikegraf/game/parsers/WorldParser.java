package net.mikegraf.game.parsers;

import java.io.IOException;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.parsers.models.LevelData;

/* Responsible for parsing xml and obtaining world information. */
public class WorldParser extends Parser<LevelData[][]> {

    // Constants.
    private final String A_WIDTH = "width";
    private final String A_HEIGHT = "height";
    private final String T_LEVEL = "level";
    private final String T_LEVEL_T_NAME = "name";
    private final String T_LEVEL_T_FILENAME = "filename";
    private final String T_LEVEL_T_X = "x";
    private final String T_LEVEL_T_Y = "y";

    @Override
    protected LevelData[][] handleParsing(Element root) throws IOException, ConfigFormatException {

        // Get world width.
        int width = 0;
        String widthStr = root.getAttribute(A_WIDTH);
        if (widthStr.length() == 0) {
            throw new ConfigFormatException(filePath + " must have width attribute.");
        }

        try {
            width = Integer.parseInt(widthStr);
        } catch (NumberFormatException nfe) {
            throw new ConfigFormatException(filePath + " must have int for width property");
        }

        // Get world height.
        int height = 0;
        String heightStr = root.getAttribute(A_HEIGHT);
        if (heightStr.length() == 0) {
            throw new ConfigFormatException(filePath + " must have height attribute.");
        }

        try {
            height = Integer.parseInt(heightStr);
        } catch (NumberFormatException nfe) {
            throw new ConfigFormatException(filePath + " must have int for height property");
        }

        // Start building LevelData.
        LevelData[][] retval = new LevelData[height][width];

        String levelName;
        String fileName;
        int levelX;
        int levelY;
        String levelXStr;
        String levelYStr;
        Array<Element> items = root.getChildrenByName(T_LEVEL);
        for (Element item : items) {

            // Get level name.
            levelName = item.getChildByName(T_LEVEL_T_NAME).getText();

            // Get file name.
            fileName = item.getChildByName(T_LEVEL_T_FILENAME).getText();

            // Get level x position in world.
            levelXStr = item.getChildByName(T_LEVEL_T_X).getText();
            if (levelXStr.length() == 0) {
                throw new ConfigFormatException(filePath + ":" + levelName + " must have x attribute.");
            }
            try {
                levelX = Integer.parseInt(levelXStr);
            } catch (NumberFormatException nfe) {
                throw new ConfigFormatException(filePath + ":" + levelName + " must have int for x attribute");
            }

            // Get level y position in world.
            levelYStr = item.getChildByName(T_LEVEL_T_Y).getText();
            if (levelYStr.length() == 0) {
                throw new ConfigFormatException(filePath + ":" + levelName + " must have y attribute.");
            }

            // Further verify parsed information is good.
            try {
                levelY = Integer.parseInt(levelYStr);
            } catch (NumberFormatException nfe) {
                throw new ConfigFormatException(filePath + ":" + levelName + " must have int for y attribute");
            }

            if (levelX >= width || levelX < 0) {
                throw new ConfigFormatException(filePath + ":" + levelName + " x must be within 0 and world width");
            }

            if (levelY >= height || levelY < 0) {
                throw new ConfigFormatException(filePath + ":" + levelName + " y must be within 0 and world height");
            }

            if (retval[levelY][levelX] != null) {
                throw new ConfigFormatException(filePath + ":" + levelName + " there cannot be "
                        + "two levels at coords (" + levelX + ", " + levelY + ").");
            }

            retval[levelY][levelX] = new LevelData(levelName, fileName);
        }

        verifyWorldIsPopulated(retval, filePath);

        return retval;
    }

    // Throw an error if every row in level data is not populated.
    private void verifyWorldIsPopulated(LevelData[][] data, String filePath) throws ConfigFormatException {
        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[y].length; x++) {
                if (data[y][x] == null) {
                    throw new ConfigFormatException(
                            filePath + ":" + " " + "has no level at coords (" + x + ", " + y + ").");
                }
            }
        }
    }
}
