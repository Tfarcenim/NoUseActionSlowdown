package tfar.nouseactionslowdown;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import tfar.nouseactionslowdown.platform.TomlConfig;

@Mod(NoUseActionSlowdown.MOD_ID)
public class NoUseActionSlowdownForge {
    
    public NoUseActionSlowdownForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TomlConfig.SERVER_SPEC);

        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.
        NoUseActionSlowdown.init();
        
    }
}