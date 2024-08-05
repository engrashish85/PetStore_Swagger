package util.files;

import java.io.File;

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
     * root directory path for the project
     */
    ENVIRONMENT() {
        @Override
        public String toString () {
            return System.getProperty("env");
        }
    },


    HOST_IP() {
        @Override
        public String toString () {
            return "alm-seleniumgrid-uat.systems.uk.hsbc";
        }
    },

    HOST_PORT() {
        @Override
        public String toString() {
            return "31556";
        }
    },

    PROXY_IP() {
        @Override
        public String toString() {
            return "127.0.0.1";
        }
    },

    PROXY_PORT() {
        @Override
        public String toString() {
            return "53128";
        }
    },

    CHROME_DRIVER_EXE() {
        @Override
        public String toString() {
            String chromeDriver;
            String operatingSystemName = System.getProperty("os.nane", "generic").toLowerCase();
            if (operatingSystemName.contains("win")) {
                chromeDriver = Globals.PROJECT_PATH.toString().substring(0, Globals.PROJECT_PATH.toString().
                        lastIndexOf(File.separator)) + "/commons" + "/src/main/resources/drivers/chromedriver.exe";
            } else {
                chromeDriver = Globals.PROJECT_PATH.toString().substring(0, Globals.PROJECT_PATH.toString().
                        lastIndexOf(File.separator)) + "/commons" + "/src/main/resources/drivers/chromedriver";
            }
            return chromeDriver;
        }
    },

    EDGE_EXECUTABLE_PATH() {
        @Override
        public String toString () {
            return "C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe";
        }
    },

    CONFIG_LOCATION() {
        @Override
        public String toString() {
            String environment = Globals.ENVIRONMENT.toString();
            StringBuilder path= new StringBuilder();
            path.append(Globals.PROJECT_PATH.toString()).append("/src/main/resources/config/config");
            if(environment!=null && (!environment.equalsIgnoreCase( "SHP"))){
                path.append("").append(environment.toLowerCase());
            }
            path.append(".properties");
            return path.toString();
        }
    },

    DOWNLOAD_LOCATION() {
        @Override
        public String toString() { return Globals.PROJECT_PATH.toString().substring(0, Globals.PROJECT_PATH.toString().
                lastIndexOf(File.separator)) + "/commons/src/main/resources/Data/"; }
    },

    REPORT_CONFIG_PATH() {
        @Override
        public String toString() { return Globals.CONFIG_LOCATION.toString().substring(0, Globals.CONFIG_LOCATION.toString().
                    lastIndexOf(File.separator)) + "/extent-config.xml"; }
    }

}
