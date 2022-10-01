package com.renjunxi.mod.bettercopper;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class CopperArmor implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] {165, 240, 225, 195};
    private static final int[] PROTECTION_VALUES = new int[] {2, 5, 4, 2};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()];
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public SoundEvent getEquipSound() {
        return null;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }

    @Override
    public String getName() {
        return "copper";
    }

    @Override
    public float getToughness() {
        return 1.5F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }

    // 其中A是头盔，B是胸甲，C是护腿，D是靴子。
    // 例如，皮革使用{1, 2, 3, 1}，钻石和下界合金使用{3, 6, 8, 3}
}
