package com.Cell_SINON.InstantHealMod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerPlayer;
import javax.annotation.Nonnull;

public class ItemInstantRecoveryagents extends Item {
    private long lastUsedTime = 0;

    public ItemInstantRecoveryagents(Properties properties) {
        super(properties.stacksTo(20));
    }

    @Override
    @Nonnull
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide && player instanceof ServerPlayer) {
            long currentTime = System.currentTimeMillis();
            long cooldownTime = 3000; // クールタイム3秒(ミリ秒)
            if (currentTime - lastUsedTime >= cooldownTime) {
                lastUsedTime = currentTime;
                player.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1));
                player.getCooldowns().addCooldown(this, (int) (cooldownTime / 50)); // クールダウンをティックに変換

                // アイテムの消費
                ItemStack itemstack = player.getItemInHand(hand);
                itemstack.shrink(1);

                return InteractionResultHolder.success(itemstack);
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }     @Override
    public Component getName(ItemStack pStack) {
        MutableComponent basename = super.getName(pStack).copy();

        int custmColor = 0x33FF69;

        Style customStyle = Style.EMPTY.withColor(custmColor);
        basename.withStyle(customStyle);
        return basename;

    }
}

