package com.nbdev.startexgame.ItemsBar;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;

public class ItemsBar {
    private final int SLOTS_SPACE = 10;
    private final float SLOTS_MOVE_SPEED = 150f;
    private ArrayList<ItemsBarSlot> itemSlots;
    private float barTop;

    public ItemsBar() {
        this.itemSlots = new ArrayList<ItemsBarSlot>();
    }

    public void setTop(float top) {
        this.barTop = top;
    }

    public void addItem(SlotItem slotItem) {
        if(itemSlots.size() >= 5) {
           // removeItem();
        }
        float lastItemBottom = itemSlots.size() == 0 ? barTop : itemSlots.get(itemSlots.size() - 1).getBottom();

        ItemsBarSlot itemsBarSlot = new ItemsBarSlot();
        itemsBarSlot.setTop(lastItemBottom - SLOTS_SPACE);
        itemsBarSlot.setItem(slotItem);
        itemSlots.add(itemsBarSlot);
    }

    public void removeItem(SlotItem item) {
        for (ItemsBarSlot itemSlot : itemSlots) {
            if(itemSlot.getItem() == item) {
                itemSlot.hide();
            }
        }
    }

    public void update(float delta) {
        Iterator<ItemsBarSlot> iterator = itemSlots.iterator();
        while (iterator.hasNext()) {
            ItemsBarSlot itemSlot = iterator.next();
            itemSlot.update(delta);
            if (itemSlot.getState() == ItemsBarSlot.State.INVISIBLE) {
                iterator.remove();
            }
        }

        // сдвигаем слоты
        for (int i = itemSlots.size()-1; i >= 0; i--) {
            ItemsBarSlot slot = itemSlots.get(i);
            float slotTop = slot.getTop();
            float prevSlotBottom = (i == 0) ? barTop : itemSlots.get(i-1).getBottom();

            if(slotTop < prevSlotBottom - SLOTS_SPACE) {
                slot.setTop( slotTop + SLOTS_MOVE_SPEED * delta );
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (ItemsBarSlot itemSlot : itemSlots) {
            itemSlot.draw(batch);
        }
    }
}
