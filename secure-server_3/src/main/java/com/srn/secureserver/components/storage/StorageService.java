package com.srn.secureserver.components.storage;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.srn.secureserver.components.customer.CustomerERM;
import com.srn.secureserver.components.customer.CustomerRepository;
import com.srn.secureserver.components.signature.SignatureERM;
import com.srn.secureserver.components.signature.SignatureRepository;
import com.srn.secureserver.servercontroller.global.Global;
import com.srn.secureserver.toolbox.crypto.asymmetric.iRSA;
import com.srn.secureserver.toolbox.crypto.hash.iHash;
import com.srn.secureserver.toolbox.crypto.symmetric.iRijndael;
import com.srn.secureserver.toolbox.json.iJson;
import com.srn.secureserver.toolbox.payload.Data;
import com.srn.secureserver.toolbox.repository.iStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageService 
{
    private final StorageRepository storageRepository;
    private final CustomerRepository customerRepository;
    private final SignatureRepository signatureRepository;

    private final iRSA rsa;
    private final iRijndael aes;
    private final iJson json;
    private final iStorage storage;
    private final iHash hash;

    @Autowired
    public StorageService(final StorageRepository storageRepository, final CustomerRepository customerRepository,
                            final SignatureRepository signatureRepository, final iRSA rsa, final iRijndael aes, final iJson json, 
                            final iStorage storage, final iHash hash)
    {
        this.storageRepository = storageRepository;
        this.customerRepository = customerRepository;
        this.signatureRepository = signatureRepository;

        this.rsa = rsa;
        this.aes = aes;
        this.json = json;
        this.storage = storage;
        this.hash = hash;
    }

    public List<StorageERM> storages()
    {
        return this.storageRepository.findAll();
    }


    public boolean loadStorageByHashMmail(final String hashMail)
    {
        boolean flag = this.storageRepository.findByHashMail(hashMail).isPresent();

        return flag;
    }

    public String signUp(final Storage storage)
    {
        final String masterKeySignature = this.rsa.encrypt("public", Global.rsa.publicKey(), storage.getMasterkey());
        final String repository = storage.getEmail();
        final String hashMail = storage.getHashmail();
        final StorageERM storageERM = new StorageERM(hashMail, masterKeySignature, repository);

        this.storageRepository.save(storageERM);

        return hashMail;
    } 

    public String cleanUp(final String hashMail)
    {
        this.storageRepository.deleteByHashMail(hashMail);

        final String flag = "Storage-Table with hash-emeil: " + hashMail + " deleted!";

        return flag;
    }


    @Transactional
    public String update(final Storage storage)
    {
        final String hashMail = storage.getHashmail();

        if(!this.storageRepository.findByHashMail(hashMail).isPresent())
            throw new IllegalStateException("Hash-email : " + hashMail + " is already taken!");
        
        StorageERM storageERM = this.storageRepository.findByHashMail(hashMail)
                                                        .orElseThrow(() -> 
                                                            new IllegalStateException("customer with hash-mail : " + 
                                                                hashMail + " does not exist!"));

        if(storage.getMasterkey() != null && storage.getMasterkey().length() > 0 && !Objects.equals(storageERM.getMasterkeysignature(), storage.getMasterkey()))
        {
            final String masterKeySignature = this.rsa.encrypt("public", Global.rsa.publicKey(), storage.getMasterkey());
            storageERM.setMasterkeysignature(masterKeySignature);
        }

        final String flag = "Storage with hash-email: " + hashMail + " updated!";

        return flag;
    }


    public String readKeysFromStorage(final Data payload)
    {
        String data = "";

        if(!this.customerRepository.findByHashMail(payload.getHashMail()).isPresent())
            throw new IllegalStateException("Hash-email: " + payload.getHashMail() + " does not exist!");

        final CustomerERM customerERM = this.customerRepository.findByHashMail(payload.getHashMail()).get();
        final String customerMail = customerERM.getEmail();

        this.storage.setStorage(Global.storage.getStorage());
        this.storage.setMail(customerMail);

        final String sData = (String) this.storage.readFileFromDirectory("Key", "KeyBundle.ck");

        Optional<SignatureERM> optionalSignaturethis = this.signatureRepository.findByHashMail(payload.getHashMail());
        final String customerPublicKey = this.rsa.decrypt("private", Global.rsa.privateKey(), optionalSignaturethis.get().getSignature());
        String rsaEncryptedWithOwnerPublicKey = this.rsa.encrypt("public", customerPublicKey, sData);

        data = rsaEncryptedWithOwnerPublicKey;

        return data;
    }

    public String readDataFromStorage(final Data payload)
    {
        String data = "";

        if(!this.customerRepository.findByHashMail(payload.getHashMail()).isPresent())
            throw new IllegalStateException("Hash-email: " + payload.getHashMail() + " does not exist!");

        final CustomerERM customerERM = this.customerRepository.findByHashMail(payload.getHashMail()).get();
        final String customerMail = customerERM.getEmail();
        final String path = payload.getPath();
        final String nameOfFile = payload.getNameOfFile();

        final String dataDirectory = customerMail + "/" + path + "/" + nameOfFile + ".cm";

        this.storage.setStorage(Global.storage.getStorage());
        this.storage.setMail(customerMail);

        final String sData = (String) this.storage.readFileFromDirectory("Text", dataDirectory);

        data = sData;

        return data;
    }

    public String deleteDataFromStorage(final Data payload)
    {
        String flag = "Data: " + payload.getPath() + "/" + payload.getNameOfFile() + " deleted!";

        if(!this.customerRepository.findByHashMail(payload.getHashMail()).isPresent())
            throw new IllegalStateException("Hash-email: " + payload.getHashMail() + " does not exist!");

        final CustomerERM customerERM = this.customerRepository.findByHashMail(payload.getHashMail()).get();
        final String customerMail = customerERM.getEmail();
        final String path = payload.getPath();
        final String nameOfFile = payload.getNameOfFile();

        final String directory = customerMail + "/" + path + "/";
        final String jString = (String) this.storage.readFileFromDirectory("Key", "KeyBundle.ck");
        if(jString != null)
        {
            File file = new File(directory + nameOfFile + ".cm");

            this.storage.setStorage(Global.storage.getStorage());
            this.storage.setMail(customerERM.getEmail());

            final String oString = (String) this.storage.readFileFromDirectory("Text", directory + nameOfFile + ".cm");
            final String hash = this.hash.hash("SHA-256", oString);

            File restoreFile = new File(directory + nameOfFile + "__RESTORE__" + hash + ".cm");
            file.renameTo(restoreFile);

            try 
            {
                JsonNode jsonKeyBundle = new ObjectMapper().readTree(jString);
                ObjectNode oNode = (ObjectNode) jsonKeyBundle;
                oNode.set(directory + nameOfFile + "__RESTORE__" + hash + ".cm", oNode.get(directory + nameOfFile + ".cm"));
                oNode.remove(directory + nameOfFile + ".cm");
            } 
            catch (JsonProcessingException e) 
            {
                flag = "Data could not be deleted!";
            }
        }
        else
        {
            flag = "Data could not be deleted!";
        }

        return flag;
    }

    public String saveToStorage(final Data payload) 
    {
        String flag = "Data saved!";

        final String data = this.rsa.decrypt("private", Global.rsa.privateKey(), payload.getPayload());
        final Save store = new Save(this.json.getJson(data), payload.getCipherData());
        final String directory = payload.getPath() + "/";

        if(!this.customerRepository.findByHashMail(payload.getHashMail()).isPresent())
            throw new IllegalStateException("Hash-email: " + payload.getHashMail() + " does not exist!");

        final StorageERM storageERM = this.storageRepository.findByHashMail(payload.getHashMail()).get();
        
        String masterKey = this.rsa.decrypt("private", Global.rsa.privateKey(), storageERM.getMasterkeysignature());
        final CustomerERM customerERM = this.customerRepository.findByHashMail(payload.getHashMail()).get();
        final String signatureDataKey = this.rsa.encrypt("public", Global.rsa.publicKey(), 
                                                        this.aes.decrypt("AES/ECB/PKCS5Padding", masterKey, null, store.getCipherKey()));
        masterKey = "";

        this.storage.setStorage(Global.storage.getStorage());
        this.storage.setMail(customerERM.getEmail());
        this.storage.storeFileToDirectory("Text", directory + payload.getNameOfFile() + ".cm", store.getCipherData());
        this.storage.storeFileToDirectory("KeyNotary", directory + payload.getNameOfFile() + ".ckn", signatureDataKey);

        final String jString = (String) this.storage.readFileFromDirectory("Key", "KeyBundle.ck");
        
        if(jString != null)
        {
            try 
            {
                JsonNode jsonKeyBundle = new ObjectMapper().readTree(jString);
                ((ObjectNode) jsonKeyBundle).put(directory + payload.getNameOfFile() + ".cm", store.getCipherKey());

                final String nJString = jsonKeyBundle.toPrettyString();
                this.storage.storeFileToDirectory("Key", "KeyBundle.ck", nJString);
            } 
            catch (JsonProcessingException e) 
            {
                flag = "Data could not be saved!";
            }
        }
        else
        {
            JsonNode jsonKeyBundle = new ObjectMapper().createObjectNode();
            ((ObjectNode) jsonKeyBundle).put(directory + payload.getNameOfFile() + ".cm", store.getCipherKey());

            final String nJString = jsonKeyBundle.toPrettyString();
            this.storage.storeFileToDirectory("Key", "KeyBundle.ck", nJString);
        }

        return flag;
    }
}
