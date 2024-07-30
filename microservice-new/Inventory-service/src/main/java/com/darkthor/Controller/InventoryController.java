package com.darkthor.Controller;

import com.darkthor.Response.InventoryResponse;
import com.darkthor.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping
    public List<InventoryResponse> inStock(@RequestBody List<String> skuCodes){
        return inventoryService.isInStock(skuCodes);

    }

}
