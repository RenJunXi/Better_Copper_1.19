package com.renjunxi.mod.bettercopper;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


import static com.renjunxi.mod.bettercopper.CopperBukkit.COPPER_BUKKIT;
import static com.renjunxi.mod.bettercopper.CopperBukkit.COPPER_WATER_BUKKIT;

public class Bettercopper implements ModInitializer {


    public static ToolItem COPPER_SWORD = new SwordItem(com.renjunxi.mod.bettercopper.CopperMaterial.INSTANCE, 5, 3, new Item.Settings().group(ItemGroup.TOOLS));
    public static ToolItem COPPER_AXE = new com.renjunxi.mod.bettercopper.CopperAxe(com.renjunxi.mod.bettercopper.CopperMaterial.INSTANCE, 8, 2.0F, new Item.Settings().group(ItemGroup.TOOLS));
    public static ToolItem COPPER_PICKAXE = new com.renjunxi.mod.bettercopper.CopperPickAxe(com.renjunxi.mod.bettercopper.CopperMaterial.INSTANCE, 3, 2.0F, new Item.Settings().group(ItemGroup.TOOLS));

    public static final ArmorMaterial COPPER_ARMOR = new CopperArmor();
    public static final Item COPPER_ARMOR_HELMET = new ArmorItem(COPPER_ARMOR, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item COPPER_ARMOR_CHESTPLATE = new ArmorItem(COPPER_ARMOR, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item COPPER_ARMOR_LEGGINGS = new ArmorItem(COPPER_ARMOR, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item COPPER_ARMOR_BOOTS = new ArmorItem(COPPER_ARMOR, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));


    @Override
    public void onInitialize()
    {
        Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_axe"), COPPER_AXE);
        Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_sword"), COPPER_SWORD);
        Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_pickaxe"), COPPER_PICKAXE);

        Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_material_helmet"), COPPER_ARMOR_HELMET);
        Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_material_chestplate"), COPPER_ARMOR_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_material_leggings"), COPPER_ARMOR_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_material_boots"), COPPER_ARMOR_BOOTS);

        Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_bukkit"), COPPER_BUKKIT);
        Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_water_bukkit"), COPPER_WATER_BUKKIT);
        //Registry.register(Registry.ITEM, new Identifier("better_copper", "copper_lava_bukkit"), COPPER_LAVA_BUKKIT);
    }
}
