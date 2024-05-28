package com.falanger.PowerLifter.elevatorSystem;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ElevatorService {

    private List<Elevator> elevators;

    public ElevatorService(){
        this.elevators = new ArrayList<>();
    }

    public void initializeElevators(int numberOfElevators){
        for(int i = 0; i < numberOfElevators; i++){
            elevators.add(new Elevator(i));
        }
    }

    public List<Object[]> getStatus(){
        List<Object[]> status = new ArrayList<>();
        for(Elevator elevator : elevators){
            status.add(new Object[]{elevator.getId(), elevator.getCurrentFloor(), elevator.getDestinationFloors()});
        }
        return status;
    }

    public void pickup(int floor, Direction direction){
        Elevator nearestElevator = elevators.get(0);
        for(Elevator elevator : elevators){
            if (Math.abs(elevator.getCurrentFloor() - floor) < Math.abs(nearestElevator.getCurrentFloor() - floor)) {
                nearestElevator = elevator;
            }
        }
        nearestElevator.getDestinationFloors().add(floor);
    }

    public void update(int elevatorId, int currentFloor, List<Integer> targetFloors){
        Elevator elevator = elevators.get(elevatorId);
        elevator.setCurrentFloor(currentFloor);
        elevator.setDestinationFloors(targetFloors);
    }

    public void step(){
        for(Elevator elevator : elevators){
            if(elevator.getDestinationFloors().isEmpty()){
                continue;
            }
            if(elevator.getCurrentFloor() < elevator.getDestinationFloors().get(0)){
                elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
            } else if(elevator.getCurrentFloor() > elevator.getDestinationFloors().get(0)){
                elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
            } else {
                elevator.getDestinationFloors().remove(0);
            }
        }
    }
}
