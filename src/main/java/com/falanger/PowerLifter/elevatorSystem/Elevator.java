package com.falanger.PowerLifter.elevatorSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Elevator {
    private final int id;
    private int currentFloor = 0;
    private List<Integer> destinationFloors = new ArrayList<>();
}
