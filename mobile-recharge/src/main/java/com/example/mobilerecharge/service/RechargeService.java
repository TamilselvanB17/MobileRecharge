
package com.example.mobilerecharge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mobilerecharge.model.Recharge;

@Service
public class RechargeService {

    private final List<Recharge> recharges = new ArrayList<>();
    private long nextId = 1;

    public synchronized Recharge addRecharge(Recharge recharge) {
        recharge.setId(nextId++);
        recharge.setStatus("RECORDED");
        recharges.add(recharge);
        return recharge;
    }

    public synchronized List<Recharge> getAllRecharges() {
        return new ArrayList<>(recharges);
    }

    public synchronized Optional<Recharge> getRechargeById(Long id) {
        return recharges.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public synchronized Optional<Recharge> updateRecharge(
            Long id, Recharge updated) {

        for (Recharge r : recharges) {
            if (r.getId().equals(id)) {
                r.setMobileNumber(updated.getMobileNumber());
                r.setOperator(updated.getOperator());
                r.setAmount(updated.getAmount());
                return Optional.of(r);
            }
        }

        return Optional.empty();
    }

    public synchronized boolean deleteRecharge(Long id) {
        return recharges.removeIf(r -> r.getId().equals(id));
    }
}
