package com.lsq.service;

import com.lsq.service.dao.ItemDao;
import com.lsq.service.dao.PetDao;

/**
 * Created by Administrator on 2018/7/21.
 */
public class PetStoreService3 {
    private ItemDao itemDao;
    private PetDao petDao;
    private Integer version;

    public PetStoreService3(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public PetStoreService3(ItemDao itemDao, PetDao petDao) {
        this.itemDao = itemDao;
        this.petDao = petDao;
        this.version = -1;
    }

    public PetStoreService3(PetDao petDao, ItemDao itemDao, Integer version) {
        this.itemDao = itemDao;
        this.petDao = petDao;
        this.version = version;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public PetDao getPetDao() {
        return petDao;
    }

    public Integer getVersion() {
        return version;
    }
}
