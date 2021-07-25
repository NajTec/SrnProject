package com.srn.secureserver.components.storage;

import java.util.List;

import com.srn.secureserver.toolbox.payload.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "Storage")
public class StorageController 
{
    private final StorageService storageService;

    @Autowired
    public StorageController(final StorageService storageService)
    {
        this.storageService = storageService;
    }

    @GetMapping
    public List<StorageERM> storages()
    {
        return this.storageService.storages();
    }

    @GetMapping(path = "Keys")
    public String readKeysFromStorage(@RequestBody final Data payload)
    {
        return this.storageService.readKeysFromStorage(payload);
    }

    @GetMapping(path = "Data")
    public String readDataFromStorage(@RequestBody final Data payload)
    {
        return this.storageService.readDataFromStorage(payload);
    }

    @DeleteMapping(path = "Data")
    public String deleteDataFromStorage(@RequestBody final Data payload)
    {
        return this.storageService.deleteDataFromStorage(payload);
    }

    @PostMapping
    public String saveToStorage(@RequestBody final Data payload)
    {
        return this.storageService.saveToStorage(payload);
    }
}
// TODO hier müssen daten noch geshared werden können zwischen den einzelnen benutzern!