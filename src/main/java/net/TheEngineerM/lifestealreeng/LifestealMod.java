package net.TheEngineerM.lifestealreeng;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.TheEngineerM.lifestealreeng.block.ModBlocks;
import net.TheEngineerM.lifestealreeng.item.ModCreativeModeTabs;
import net.TheEngineerM.lifestealreeng.item.ModItems;
//import net.minecraft.core.NonNullList;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.item.CreativeModeTab;
//import net.minecraft.world.item.CreativeModeTabs;
//import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.*;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraft.world.item.crafting.*;
//import net.minecraft.util.*;
//import net.minecraftforge.event.*;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.core.NonNullList;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.ShapedRecipe;
//import net.minecraftforge.event.server.ServerStartingEvent;



// The value here should match an entry in the META-INF/mods.toml file
@Mod(LifestealMod.MOD_ID)
public class LifestealMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "lifestealreeng";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public LifestealMod()
    {

        LOGGER.info("Initializing LifestealMod");

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        // modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        LOGGER.info("LifestealMod initialization complete");
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    /*
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        LOGGER.info("Adding items to creative tab");

        CreativeModeTab ingredientsTab = CreativeModeTabs.TAB_INGREDIENTS;

        if(event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.accept(ModItems.SAPPHIRE);
            event.accept(ModItems.SAPPHIRE_DUST);
        }
    }
    */

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("Server is starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("Client setup");
        }
    }
       
}
