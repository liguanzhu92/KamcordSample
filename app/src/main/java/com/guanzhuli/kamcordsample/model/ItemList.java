package com.guanzhuli.kamcordsample.model;

import java.util.ArrayList;

/**
 * Created by Guanzhu Li on 3/12/2017.
 * item information list for global control
 */
public class ItemList extends ArrayList<Item> {
    private static ItemList ourInstance = new ItemList();

    public static ItemList getInstance() {
        return ourInstance;
    }

    private ItemList() {
    }
}
