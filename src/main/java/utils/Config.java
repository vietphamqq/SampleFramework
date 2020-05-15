package utils;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Config {

    public final static Logger LOGGER = LogManager.getLogger("General");

    public static Environments ENV = ConfigFactory.create(Environments.class, System.getenv());

}
