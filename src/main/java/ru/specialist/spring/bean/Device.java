package ru.specialist.spring.bean;

public interface Device {

    default String getName() {
        return this.getClass().getSimpleName();
    }

}
