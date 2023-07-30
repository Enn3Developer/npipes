package npipes.config;

import net.minecraftforge.common.Configuration;

import java.io.File;

public class ConfigHandler {
    public static void init(File file) {
        Configuration config = new Configuration(file);
        config.load();
        config.save();
    }
}
