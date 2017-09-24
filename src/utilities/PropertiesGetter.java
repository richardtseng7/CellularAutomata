package utilities;

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

    private static final String PROPERTIES_FILE = "simulations.properties";
    private static final Properties PROPERTIES;

    /* keys used to access the configuration values in the .properties file */
    /* grid configuration value keys */
    private static final String GRID_DIMENSIONS_KEY = "grid-dimensions";
    private static final String CELL_SIDE_LENGTH_KEY = "cell-side-length";
    /* segregation configuration keys */
    private static final String DEFAULT_SATISFACTION_THRESHOLD_KEY =
            "default-satisfaction-threshold";
    /* spreading-fire configuration keys */
    private static final String DEFAULT_CATCH_FIRE_PROBABILITY_KEY =
            "default-catch-fire-probability";
    /* WaTor world configuration value keys */
    private static final String DEFAULT_SHARK_INITIAL_ENERGY_LEVEL_KEY =
            "default-shark-initial-energy-level";
    private static final String DEFAULT_ENERGY_LOSS_PER_CHRONON_KEY =
            "default-energy-loss-per-chronon";
    private static final String DEFAULT_ENERGY_GAIN_PER_FISH_KEY =
            "default-energy-gain-per-fish";

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
            PROPERTIES.load(PropertiesGetter.class.getClassLoader()
                    .getResourceAsStream(PROPERTIES_FILE));
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

    public static int getGridDimensions() {
        return getIntegerProperty(GRID_DIMENSIONS_KEY);
    }

    public static int getCellSideLength() {
        return getIntegerProperty(CELL_SIDE_LENGTH_KEY);
    }


    public static int getSharkDefaultInitialEnergyLevel() {
        return getIntegerProperty(DEFAULT_SHARK_INITIAL_ENERGY_LEVEL_KEY);
    }

    public static int getDefaultEnergyLossPerChronon() {
        return getIntegerProperty(DEFAULT_ENERGY_LOSS_PER_CHRONON_KEY);
    }

    public static int getDefaultEnergyGainPerFish() {
        return getIntegerProperty(DEFAULT_ENERGY_GAIN_PER_FISH_KEY);
    }

    /**
     * Get a property that is know to be a double.
     *
     * @param key - the key used to index the desired configuration value
     * @return value - the double configuration value we want to get
     */
    private static Double getDoubleProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        // if the key is not found, Properties will return null and we should return a default value
        if (value == null) {
            return -1.0;
        }
        return Double.parseDouble(value);
    }

    public static double getDefaultCatchFireProbability() {
        return getDoubleProperty(DEFAULT_CATCH_FIRE_PROBABILITY_KEY);
    }

    public static double getDefaultSatisfactionThreshold() {
        return getDoubleProperty(DEFAULT_SATISFACTION_THRESHOLD_KEY);
    }
}