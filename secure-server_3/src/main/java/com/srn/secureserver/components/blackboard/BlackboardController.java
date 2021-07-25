package com.srn.secureserver.components.blackboard;

import java.util.List;

import com.srn.secureserver.toolbox.payload.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "Blackboard")
public class BlackboardController 
{
    private final BlackboardService blackboardService;

    @Autowired
    public BlackboardController(final BlackboardService blackboardService)
    {
        this.blackboardService = blackboardService;
    }

    @GetMapping
    public List<BlackboardERM> blackboard()
    {
        return this.blackboardService.blackboard();
    }

    @PostMapping(path = "ShareFile")
    public String shareFile(@RequestBody final Data payload)
    {
        return this.blackboardService.shareFile(payload);
    }

    @PostMapping(path = "EnquireFile")
    public String enquireFile(@RequestBody final Data payload)
    {
        return this.blackboardService.enquireFile(payload);
    }

    @DeleteMapping(path = "DeleteFile")
    public String deleteEntry(@PathVariable("CustomerHashMail") final Data payload)
    {
        return this.blackboardService.deleteEntry(payload);
    }    

    @PutMapping(path = "UpdateFile")
    public String updateInterval(@PathVariable("CustomerHashMail") final Data payload)
    {
        return this.blackboardService.updateInterval(payload);
    }

    @PutMapping(path = "WithdrawRights")
    public String withdrawRights(@PathVariable("CustomerHashMail") final Data payload)
    {
        return this.blackboardService.withdrawRights(payload);
    }
}
