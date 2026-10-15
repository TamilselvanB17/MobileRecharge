
package com.example.mobilerecharge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mobilerecharge.model.Recharge;
import com.example.mobilerecharge.service.RechargeService;

@RestController
@RequestMapping("/api/recharges")
public class RechargeController {

    private final RechargeService service;

    public RechargeController(RechargeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> addRecharge(
            @RequestBody Recharge recharge) {

        if (!isValid(recharge)) {
            return ResponseEntity.badRequest()
                    .body("Enter a valid mobile number, "
                            + "operator and positive amount.");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addRecharge(recharge));
    }

    @GetMapping
    public List<Recharge> getAllRecharges() {
        return service.getAllRecharges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRechargeById(
            @PathVariable Long id) {

        if (id <= 0) {
            return ResponseEntity.badRequest()
                    .body("ID must be positive.");
        }

        return service.getRechargeById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecharge(
            @PathVariable Long id,
            @RequestBody Recharge recharge) {

        if (id <= 0 || !isValid(recharge)) {
            return ResponseEntity.badRequest()
                    .body("Invalid ID or recharge details.");
        }

        return service.updateRecharge(id, recharge)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecharge(
            @PathVariable Long id) {

        if (id <= 0) {
            return ResponseEntity.badRequest()
                    .body("ID must be positive.");
        }

        if (service.deleteRecharge(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private boolean isValid(Recharge r) {
        return r != null
                && r.getMobileNumber() != null
                && r.getMobileNumber().matches("[0-9]{10}")
                && r.getOperator() != null
                && !r.getOperator().isBlank()
                && Double.isFinite(r.getAmount())
                && r.getAmount() > 0;
    }
}

