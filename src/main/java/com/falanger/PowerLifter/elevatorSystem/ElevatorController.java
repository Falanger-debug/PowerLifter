package com.falanger.PowerLifter.elevatorSystem;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elevator")
public class ElevatorController {

    private final ElevatorService elevatorService;


    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @PostMapping("/initialize")
    public void initialize(@RequestParam int numberOfElevators) {
        elevatorService.initializeElevators(numberOfElevators);
    }

    @GetMapping("/status")
    public List<Object[]> getStatus() {
        return elevatorService.getStatus();
    }

    @PostMapping("/pickup")
    public void pickup(@RequestParam int floor, @RequestParam Direction direction) {
        elevatorService.pickup(floor, direction);
    }

    @PostMapping("/update")
    public void update(@RequestParam int elevatorId, @RequestParam int currentFloot, @RequestParam List<Integer> targetFloors){
        elevatorService.update(elevatorId, currentFloot, targetFloors);
    }

    @PostMapping("/step")
    public void step() {
        elevatorService.step();
    }
}
