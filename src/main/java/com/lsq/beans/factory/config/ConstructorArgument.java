package com.lsq.beans.factory.config;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/21.
 */
public class ConstructorArgument {
    private List<ValueHolder> valueHolders = new LinkedList<ValueHolder>();

    public void addValueHolder(ValueHolder valueHolder) {
        valueHolders.add(valueHolder);
    }

    public List<ValueHolder> getArgumentValues() {
        return valueHolders;
    }

    public boolean hasArgs() {
        return valueHolders.size() != 0;
    }

    public static class ValueHolder {
        private Object value;
        private String type;
        private String name;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
