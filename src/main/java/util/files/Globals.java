package util.files;

import java.io.IOException;

/**
 * global variables for projects
 */
public enum Globals {

    /**
     * root directory path for the project
     */
    PROJECT_PATH() {
        @Override
        public String toString() {
            return System.getProperty("user.dir");
        }
    },

    /**
     * Base URL for rest assured
     */
    BASE_URL() {
        @Override
        public String toString () {
            try {
                return PropertyFileReader.readProperties(Globals.CONFIG_LOCATION.toString()).get("baseUrl");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    },

    CONFIG_LOCATION() {
        @Override
        public String toString() {
            StringBuilder path= new StringBuilder();
            path.append(Globals.PROJECT_PATH.toString()).append("/src/main/resources/config/config.properties");
            return path.toString();
        }
    },
}
