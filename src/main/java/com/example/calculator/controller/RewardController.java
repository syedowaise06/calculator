package com.example.calculator.controller;

import com.example.calculator.model.Reward;
import com.example.calculator.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @RequestMapping(value = "/rewards/{id}", produces = "application/json")
    public ResponseEntity<Reward> getRewardByCustomerId(@PathVariable String id) {
        return ResponseEntity.ok(rewardService.getRewardByCustomerId(Integer.parseInt(id)));
    }

    @RequestMapping(value = "/rewards", produces = "application/json")
    public ResponseEntity<List<Reward>> getAllRewards() {
        return ResponseEntity.ok(rewardService.getRewards());
    }
}
