package resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * Static utility class for retrieving the simulations' properties. Extends the functionality of
 * the base java class java.util.Properties. All methods are static and fields are intialized in
 * a static block.
 *
 * @author Ben Schwennesen
 */
public final class PropertiesGetter {

    /**
     * name of the file storing the properties for the breakout game variant
     */
    private static final String PROPERTIES_FILE = "simulation.properties";

    /**
     * this class uses java.util.Properties as a base and extends its functionality
     */
    private static final Properties PROPERTIES;

    /**
     * Keys used to access the configuration values in the .properties file
     */
    /** grid configuration keys */
    private static final String GRID_DIMENSION_KEY = "grid-dimension";
    private static final String CELL_SIZE_KEY = "cell-size";
    /** Conway configuration keys */
    private static final String CONWAY_DEAD_KEY = "dead-state";
    private static final String CONWAY_LIVE_KEY = "live-state";

    /**
     * Blank, private constructor to ensure no other class tries to create an instance of this
     * utility class.
     */
    private PropertiesGetter() {
        // do nothing
    }

    /** static block to initialize the static java.util.Properties member */
    static {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(PropertiesGetter.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a property that is know to be an integer.
     *
     * @param key - the key used to index the desired configuration value
     * @return value - the integer configuration value we want to get
     */
    private static int getIntegerProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        // if the key is not found, Properties will return null and we should return a default value
        if (value == null) {
            return -1;
        }
        return Integer.parseInt(value);
    }

    /**
     * @return the dimension of the simulation grid (both row and column since the grid is always
     * a square, for the moment)
     */
    public static int getGridDimension() {
        return getIntegerProperty(GRID_DIMENSION_KEY);
    }

    /**
     * @return the length of all sides of any type of cell (cells are square)
     */
    public static int getCellSize() {
        return getIntegerProperty(CELL_SIZE_KEY);
    }

    /**
     * @return the integer value used to represent the 'dead' state in Conway's Game of Life
     */
    public static int getConwayDeadStateValue() {
        return getIntegerProperty(CONWAY_DEAD_KEY);
    }

    /**
     * @return the integer value used to represent the 'live' state in Conway's Game of Life
     */
    public static int getConwayLiveStateValue() {
        return getIntegerProperty(CONWAY_LIVE_KEY);
    }
}