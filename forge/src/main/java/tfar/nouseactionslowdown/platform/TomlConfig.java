package tfar.nouseactionslowdown.platform;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;
import tfar.nouseactionslowdown.NoUseActionSlowdown;

public class TomlConfig implements MLConfig {
    public static final Server CLIENT;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<Server, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = specPair2.getRight();
        CLIENT = specPair2.getLeft();
    }

    @Override
    public boolean disableUsingItemSlowdown() {
        return Server.disableItemUsingSlowdown.get();
    }

    @Override
    public boolean disableEatingSlowdown() {
        return Server.disableEatingSlowdown.get();
    }

    @Override
    public boolean disableDrinkingSlowdown() {
        return Server.disableDrinkingSlowdown.get();
    }

    @Override
    public boolean disableBlockingSlowdown() {
        return Server.disableBlockingSlowdown.get();
    }

    @Override
    public boolean disableBowSlowdown() {
        return Server.disableBowSlowdown.get();
    }

    @Override
    public boolean disableCrossbowSlowdown() {
        return Server.disableCrossbowSlowdown.get();
    }


    //   EAT,
    //   DRINK,
    //   BLOCK,
    //   BOW,
    public static class Server {
        public static ForgeConfigSpec.BooleanValue disableItemUsingSlowdown;
        public static ForgeConfigSpec.BooleanValue disableEatingSlowdown;
        public static ForgeConfigSpec.BooleanValue disableDrinkingSlowdown;
        public static ForgeConfigSpec.BooleanValue disableBlockingSlowdown;
        public static ForgeConfigSpec.BooleanValue disableBowSlowdown;
        public static ForgeConfigSpec.BooleanValue disableCrossbowSlowdown;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("general");
           // disableItemUsingSlowdown = builder.define("disable_using_item_slowdown",true);

            disableEatingSlowdown = builder.define("disable_eating_slowdown",true);
            disableDrinkingSlowdown = builder.define("disable_drinking_slowdown",true);
            disableBlockingSlowdown = builder.define("disable_blocking_slowdown",true);
            disableBowSlowdown = builder.define("disable_bow_slowdown",true);
            disableCrossbowSlowdown = builder.define("disable_crossbow_slowdown",true);

            builder.pop();
        }
    }

    public static void configEvent(ModConfigEvent event) {
        if (event.getConfig().getModId().equals(NoUseActionSlowdown.MOD_ID)) {
        }
    }
}
