package com.srn.secureserver.components.blackboard;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.srn.secureserver.components.customer.CustomerRepository;
import com.srn.secureserver.toolbox.payload.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BlackboardService 
{
    private final BlackboardRepository blackboardRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public BlackboardService(final BlackboardRepository blackboardRepository, 
                                final CustomerRepository customerRepository)
    {
        this.blackboardRepository = blackboardRepository;
        this.customerRepository = customerRepository;
    }

    public List<BlackboardERM> blackboard()
    {
        return this.blackboardRepository.findAll();
    }

    public String cleanUp(final String ownerHashMail)
    {
        this.blackboardRepository.deleteAllByOwnerHashMail(ownerHashMail);

        final String flag = "All entries for owner-hash-email: " + ownerHashMail + " deleted!";

        return flag;
    }

    @Transactional
    public String update(Blackboard board)
    {
        final String ownerHashMail = board.getOwnerHashMail();
        final String enquirerHashMail = board.getEnquirerHashMail();

        if(!this.blackboardRepository.findByOwnerHashMail(ownerHashMail).isPresent())
            throw new IllegalStateException("Hash-email : " + ownerHashMail + " is already taken!");

        if(!this.blackboardRepository.findByEnquirerHashMail(enquirerHashMail).isPresent())
            throw new IllegalStateException("Hash-email : " + enquirerHashMail + " is already taken!");

        BlackboardERM blackboardERM = this.blackboardRepository.findByOwnerHashMail(ownerHashMail).get();
        blackboardERM.setMessage(board.getMessage());
        blackboardERM.setResource(board.getResource());
        blackboardERM.setRequest(board.getRequest());
        blackboardERM.setApproval(board.getApproval());
        blackboardERM.setStartdate(board.getStartDate());
        blackboardERM.setEnddate(board.getEndDate());

        final String flag = "Blackboard updated!";

        return flag;
    }

    public String shareFile(final Data payload)
    {
        String flag = "Data: " + payload.getPath() + "/" + payload.getNameOfFile() + " shared!";

        if(!this.customerRepository.findByHashMail(payload.getHashMail()).isPresent())
            throw new IllegalStateException("Owner-hash-email: " + payload.getHashMail() + " does not exist!");

        if(!this.customerRepository.findByHashMail(payload.getEnquirerHashMail()).isPresent())
            throw new IllegalStateException("Enquirer-hash-email: " + payload.getEnquirerHashMail() + " does not exist!");

        final String ownerHashMail = payload.getHashMail();
        final String enquirerHashMail = payload.getEnquirerHashMail();
        final String message = payload.getMessage();
        final String path = payload.getPath();
        final String nameOfFile = payload.getNameOfFile();
        final Date startDate = payload.getStartTime();
        final Date endDate = payload.getStartTime();

        final String resource = path + "/" + nameOfFile + ".cm";
        final String request = "false";
        final String approval = "true";

        BlackboardERM entry = new BlackboardERM(ownerHashMail, enquirerHashMail, message, resource, 
                                                request, approval, startDate, endDate);

        this.blackboardRepository.save(entry);

        return flag;
    }

    public String enquireFile(final Data payload)
    {
        String flag = "Data: " + payload.getPath() + "/" + payload.getNameOfFile() + " shared!";

        if(!this.customerRepository.findByHashMail(payload.getHashMail()).isPresent())
            throw new IllegalStateException("Owner-hash-email: " + payload.getHashMail() + " does not exist!");

        if(!this.customerRepository.findByHashMail(payload.getEnquirerHashMail()).isPresent())
            throw new IllegalStateException("Enquirer-hash-email: " + payload.getEnquirerHashMail() + " does not exist!");

        final String ownerHashMail = payload.getHashMail();
        final String enquirerHashMail = payload.getEnquirerHashMail();
        final String message = payload.getMessage();
        final String path = payload.getPath();
        final String nameOfFile = payload.getNameOfFile();
        final Date startDate = payload.getStartTime();
        final Date endDate = payload.getStartTime();

        final String resource = path + "/" + nameOfFile + ".cm";
        final String request = "true";
        final String approval = "false";

        List<Optional<BlackboardERM>> entries = this.blackboardRepository.findAllByOwnerHashMail(ownerHashMail);

        if(entries.size() > 0)
        {
            int exists = 0;

            for(int index = 0; index < entries.size(); ++index)
                if(entries.get(index).get().getEnquirerHashMail().equals(enquirerHashMail) && entries.get(index).get().getResource().equals(resource))
                    ++exists;

            if(exists != 0)
                throw new IllegalStateException("Entry exist!");
        }

        BlackboardERM entry = new BlackboardERM(ownerHashMail, enquirerHashMail, message, resource, 
                                                request, approval, startDate, endDate);

        this.blackboardRepository.save(entry);

        return flag;
    }

    public String withdrawRights(final Data payload)
    {
        String flag = "Data: " + payload.getPath() + "/" + payload.getNameOfFile() + " shared!";

        if(!this.customerRepository.findByHashMail(payload.getHashMail()).isPresent())
            throw new IllegalStateException("Owner-hash-email: " + payload.getHashMail() + " does not exist!");

        if(!this.customerRepository.findByHashMail(payload.getEnquirerHashMail()).isPresent())
            throw new IllegalStateException("Enquirerer-hash-email: " + payload.getEnquirerHashMail() + " does not exist!");

        final String ownerHashMail = payload.getHashMail();
        final String enquirerHashMail = payload.getEnquirerHashMail();
        final String path = payload.getPath();
        final String nameOfFile = payload.getNameOfFile();

        final String resource = path + "/" + nameOfFile + ".cm";

        List<Optional<BlackboardERM>> entries = this.blackboardRepository.findAllByOwnerHashMail(ownerHashMail);
      
        for(int index = 0; index < entries.size(); ++index)
            if(entries.get(index).get().getEnquirerHashMail().equals(enquirerHashMail) && entries.get(index).get().getResource().equals(resource))
                entries.get(index).get().setApproval("false");

        return flag;
    }

    public String updateInterval(final Data payload)
    {
        String flag = "Data: " + payload.getPath() + "/" + payload.getNameOfFile() + " shared!";

        if(!this.customerRepository.findByHashMail(payload.getHashMail()).isPresent())
            throw new IllegalStateException("Owner-hash-email: " + payload.getHashMail() + " does not exist!");

        if(!this.customerRepository.findByHashMail(payload.getEnquirerHashMail()).isPresent())
            throw new IllegalStateException("Enquirerer-hash-email: " + payload.getEnquirerHashMail() + " does not exist!");

        final String ownerHashMail = payload.getHashMail();
        final String enquirerHashMail = payload.getEnquirerHashMail();
        final String path = payload.getPath();
        final String nameOfFile = payload.getNameOfFile();

        final String resource = path + "/" + nameOfFile + ".cm";

        List<Optional<BlackboardERM>> entries = this.blackboardRepository.findAllByOwnerHashMail(ownerHashMail);

        for(int index = 0; index < entries.size(); ++index)
            if(entries.get(index).get().getEnquirerHashMail().equals(enquirerHashMail) && entries.get(index).get().getResource().equals(resource))
                this.blackboardRepository.delete(entries.get(index).get());

        return flag;
    }

    public String deleteEntry(final Data payload)
    {
        String flag = "Entry with resource: " + payload.getPath() + "/" + payload.getNameOfFile() + " shared with enquirer-hash-email: !" + payload.getEnquirerHashMail() + " deleted!";

        if(!this.customerRepository.findByHashMail(payload.getHashMail()).isPresent())
            throw new IllegalStateException("Owner-hash-email: " + payload.getHashMail() + " does not exist!");

        if(!this.customerRepository.findByHashMail(payload.getEnquirerHashMail()).isPresent())
            throw new IllegalStateException("Enquirerer-hash-email: " + payload.getEnquirerHashMail() + " does not exist!");

        final String ownerHashMail = payload.getHashMail();
        final String enquirerHashMail = payload.getEnquirerHashMail();
        final String path = payload.getPath();
        final String nameOfFile = payload.getNameOfFile();
        final Date startDate = payload.getStartTime();
        final Date endDate = payload.getStartTime();

        final String resource = path + "/" + nameOfFile + ".cm";

        List<Optional<BlackboardERM>> entries = this.blackboardRepository.findAllByOwnerHashMail(ownerHashMail);

        if(entries.size() == 0)
        {
            int exists = 0;

            for(int index = 0; index < entries.size(); ++index)
                if(entries.get(index).get().getEnquirerHashMail().equals(enquirerHashMail) && entries.get(index).get().getResource().equals(resource))
                    ++exists;

            if(exists == 0)
                throw new IllegalStateException("No such entry exist!");
        }

        for(int index = 0; index < entries.size(); ++index)
        {
            if(entries.get(index).get().getEnquirerHashMail().equals(enquirerHashMail) && entries.get(index).get().getResource().equals(resource))
            {
                entries.get(index).get().setStartdate(startDate);
                entries.get(index).get().setStartdate(endDate);
            }
        }

        return flag;
    }
}
