package org.example.Part3;

import org.example.Part3.AbastractClasses.TwoHeadedPerson;
import org.example.Part3.Enums.Sides;

public class TwoHeadedMan extends TwoHeadedPerson {

    public TwoHeadedMan() {
        super("Двухголовый человек");
        this.leftHead = new LeftHead(Sides.LEFT);
        this.rightHead = new RightHead(Sides.RIGHT);
        this.leftHand = new Hand(Sides.LEFT);
        this.rightHand = new Hand(Sides.RIGHT);
        this.feet = new Feet();
    }

    @Override
    public void leftHeadAction() {
        leftHead.action();
    }

    @Override
    public void rightHeadAction() {
        rightHead.action();
    }

    @Override
    public void leftHandAction() {
        System.out.println("ковырял " + leftHand.getSide() + " рукой в зубах " + rightHead.getName());
    }

    @Override
    public void rightHandAction() {

    }

    @Override
    public void feetAction() {
        System.out.println("положил " + feet.all() + " на пуль управления");
    }

    public void relaxInChair(Chair chair) {
        chair.sit(this);
        feetAction();

        leftHandAction();
        rightHandAction();
        rightHeadAction();
        leftHeadAction();
    }
}
