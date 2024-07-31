package com.darkthor.Controller;

import com.darkthor.Response.InventoryResponse;
import com.darkthor.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> inStock(@RequestParam List<String> skuCode) {
        if (skuCode == null || skuCode.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<InventoryResponse> responses = inventoryService.isInStock(skuCode);
        if (responses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responses);
    }

}
