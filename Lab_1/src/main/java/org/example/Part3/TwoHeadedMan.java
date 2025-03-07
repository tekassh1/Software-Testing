package org.example.Part3;

import org.example.Part3.AbstractClasses.StoryCharacter;
import org.example.Part3.Enums.Actions;
import org.example.Part3.Enums.CharacterState;
import org.example.Part3.Enums.Location;
import org.example.Part3.Enums.Place;

public class TwoHeadedMan extends StoryCharacter {
    Place place;
    int smileCoefficient;

    public TwoHeadedMan(String name, LocationArea locationArea) {
        super(name, locationArea);
        place = Place.CHAIR;
        smileCoefficient = 1;
    }

    @Override
    public void act(Actions action) {
        if (action == Actions.LEAN_ON_CHAIR) {
            place = Place.CHAIR;
        }
        else if (action == Actions.PICK_NOSE && place == Place.CHAIR) {
            characterState = CharacterState.SMILING;
        }
        updateStates();
    }

    @Override
    public void changeLocationArea(LocationArea newLocationArea) {
        this.locationArea.removeCharacter(this);
        this.locationArea = newLocationArea;
        locationArea.addCharacter(this);

        updateStates();
    }

    @Override
    public void updateStates() {
        if (characterState == CharacterState.SMILING) {
            smileCoefficient *= 100;
        }
        else if (this.locationArea.getPlace() == Location.STREET) {
            smileCoefficient = 1;
        }
    }

    public Place getPlace() {
        return place;
    }

    public int getSmileCoefficient() {
        return smileCoefficient;
    }

    public LocationArea getLocationArea() {
        return locationArea;
    }
}