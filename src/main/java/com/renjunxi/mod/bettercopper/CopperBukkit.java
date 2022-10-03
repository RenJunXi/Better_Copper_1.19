package com.renjunxi.mod.bettercopper;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CopperBukkit extends BucketItem {
    public static final BucketItem COPPER_BUKKIT=new CopperBukkit(Fluids.EMPTY, new Item.Settings().group(ItemGroup.MISC));
    public static final BucketItem COPPER_WATER_BUKKIT=new CopperBukkit(Fluids.WATER, new Item.Settings().group(ItemGroup.MISC));
    private final Fluid fluid;

    public CopperBukkit(Fluid fluid, Item.Settings settings) {
        super(fluid,settings);
        this.fluid = fluid;
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world, user, this.fluid == Fluids.EMPTY ? RaycastContext.FluidHandling.SOURCE_ONLY : RaycastContext.FluidHandling.NONE);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(itemStack);
        } else {
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getSide();
            BlockPos blockPos2 = blockPos.offset(direction);
            if (world.canPlayerModifyAt(user, blockPos) && user.canPlaceOn(blockPos2, direction, itemStack)) {
                BlockState blockState;
                if (this.fluid == Fluids.EMPTY) {
                    blockState = world.getBlockState(blockPos);
                    if (blockState.getBlock() instanceof FluidDrainable) {
                        FluidDrainable fluidDrainable = (FluidDrainable)blockState.getBlock();
                        ItemStack itemStack2 = fluidDrainable.tryDrainFluid(world, blockPos, blockState);
                        if (!itemStack2.isEmpty()) {
                            user.incrementStat(Stats.USED.getOrCreateStat(this));
                            fluidDrainable.getBucketFillSound().ifPresent((sound) -> {
                                user.playSound(sound, 1.0F, 1.0F);
                            });
                            world.emitGameEvent(user, GameEvent.FLUID_PICKUP, blockPos);
                            //ItemStack itemStack3 = ItemUsage.exchangeStack(itemStack, user, itemStack2);
                            if (!world.isClient) {
                                Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity)user, itemStack2);
                            }

                            return TypedActionResult.success(new ItemStack(COPPER_WATER_BUKKIT), world.isClient());
                        }
                    }

                    return TypedActionResult.fail(itemStack);
                } else {
                    blockState = world.getBlockState(blockPos);
                    BlockPos blockPos3 = blockState.getBlock() instanceof FluidFillable && this.fluid == Fluids.WATER ? blockPos : blockPos2;
                    if (this.placeFluid(user, world, blockPos3, blockHitResult)) {
                        this.onEmptied(user, world, itemStack, blockPos3);
                        if (user instanceof ServerPlayerEntity) {
                            Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)user, blockPos3, itemStack);
                        }

                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        return TypedActionResult.success(!user.getAbilities().creativeMode ? new ItemStack(COPPER_BUKKIT) : itemStack, world.isClient());
                    } else {
                        return TypedActionResult.fail(itemStack);
                    }
                }
            } else {
                return TypedActionResult.fail(itemStack);
            }
        }
    }
}
