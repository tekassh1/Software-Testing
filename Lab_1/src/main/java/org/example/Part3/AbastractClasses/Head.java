package org.example.Part3.AbastractClasses;

import org.example.Part3.Enums.Sides;

public abstract class Head {
    Sides side;

    String name;

    public Head(Sides side) {
        this.side = side;
        name = side + " голова";
    }

    public String getName() {
        return name;
    }

    public abstract void action();
}
