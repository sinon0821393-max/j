package com.Cell_SINON.InstantHealMod.item;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.Properties;


public class FoodEmeraldApple extends Item {
    public FoodEmeraldApple(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
       list.add(Component.translatable(this.getDescriptionId() + "desc1").withStyle(ChatFormatting.LIGHT_PURPLE));
        list.add(Component.translatable(this.getDescriptionId() + "desc2").withStyle(ChatFormatting.LIGHT_PURPLE));
    }
//    @Override
//    public Rarity getRarity(ItemStack pStack) {
//        return Rarity.UNCOMMON;
    @Override
    public Component getName(ItemStack pStack) {
    MutableComponent basename = super.getName(pStack).copy();

    int custmColor = 0x5555FF;

    Style customStyle = Style.EMPTY.withColor(custmColor);
    basename.withStyle(customStyle);
    return basename;

    }



}

