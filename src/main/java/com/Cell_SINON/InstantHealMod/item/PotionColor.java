package com.Cell_SINON.InstantHealMod.item;

import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InstantHealMod.MOD_id, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class PotionColor {    @SubscribeEvent
public static void registerItemColors(RegisterColorHandlersEvent.Item event) {

    // あなたが設定したい液体の色を16進数(0xRRGGBB)で定義します
    int heroPotionColor = 0x36F2B5; // 例：エメラルドグリーン

    // アイテムの色を登録する処理
    event.getItemColors().register(
            // ↓ この部分が、ポーションの色を決定する処理本体です
            (itemStack, tintIndex) -> {


                if (tintIndex > 0){
                    return -1;
                }
                // ポーションの中身（液体）の種類を取得
                Potion potion = PotionUtils.getPotion(itemStack);

                // 中身が自作のHERO_POTIONか、LONG_HERO_POTIONの場合...
                if (potion == InstantHealModPotions.HEROPOTION.get()) {
                    // ...先ほど定義したカスタムカラーを返す
                    return heroPotionColor;
                }

                // それ以外のポーション（水ポーションや他のMODのポーションなど）の場合は、
                // バニラの通常の色計算に任せる
                return PotionUtils.getColor(itemStack);
            },
            // ↓ この色変更処理を、以下のアイテムに適用する
            Items.POTION

    );
}
}

