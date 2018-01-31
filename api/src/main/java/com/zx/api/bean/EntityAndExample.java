package com.zx.api.bean;

public class EntityAndExample<Entity,Example> {
    public Entity entity;
    public Example example;

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }
}
