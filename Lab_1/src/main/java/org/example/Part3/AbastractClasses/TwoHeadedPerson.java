package org.example.Part3.AbastractClasses;

import org.example.Part3.Enums.Sides;

public abstract class TwoHeadedPerson {
    String name;

    public class LeftHead extends Head {

        public LeftHead(Sides side) {
            super(side);
        }

        @Override
        public void action() {
            System.out.println("но зато " + getName() + " улыбалась широко и непринужденно. ");
        }
    }

    public class RightHead extends Head {

        public RightHead(Sides side) {
            super(side);
        }

        @Override
        public void action() {
            System.out.println(getName() + ", казалось, была всецело занята этим.");
        }
    }

    public class Hand {

        Sides side;

        public Hand(Sides side) {
            this.side = side;
        }

        public Sides getSide() {
            return side;
        }
    }

    public class Feet {
        Sides side;

        public Feet() {}

        public String all() {
            return "Обе ноги";
        }
    }

    protected LeftHead leftHead;
    protected RightHead rightHead;

    public Hand leftHand;
    protected Hand rightHand;

    protected Feet feet;

    public abstract void leftHeadAction();
    public abstract void rightHeadAction();

    public abstract void leftHandAction();
    public abstract void rightHandAction();

    public abstract void feetAction();

    public TwoHeadedPerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LeftHead getLeftHead() {
        return leftHead;
    }

    public RightHead getRightHead() {
        return rightHead;
    }

    public Hand getLeftHand() {
        return leftHand;
    }

    public Hand getRightHand() {
        return rightHand;
    }

    public Feet getFeet() {
        return feet;
    }
}
