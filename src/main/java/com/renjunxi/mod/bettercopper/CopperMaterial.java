package com.renjunxi.mod.bettercopper;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;

public class CopperMaterial  implements ToolMaterial {
    public static CopperMaterial INSTANCE = new CopperMaterial();
    @Override
    public int getDurability() {
        return 200;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 7.0F;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }
}