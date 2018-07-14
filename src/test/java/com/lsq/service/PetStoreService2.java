package com.lsq.service;

import com.lsq.service.dao.ItemDao;
import com.lsq.service.dao.PetDao;

/**
 * Created by Administrator on 2018/7/14.
 */
public class PetStoreService2 {
    private PetDao petDao;
    private ItemDao itemDao;
    private String owner;

    public PetDao getPetDao() {
        return petDao;
    }

    public void setPetDao(PetDao petDao) {
        this.petDao = petDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
