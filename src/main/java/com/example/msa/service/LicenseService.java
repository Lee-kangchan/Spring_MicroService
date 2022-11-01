package com.example.msa.service;

import com.example.msa.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {

    @Autowired
    MessageSource messages;
    public License getLicense(String organizationId, String licenseId) {
        License license = new License();
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software Product");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage("license.create.message", null, locale), license.toString());
        }
        return responseMessage;
    }
    public String updateLicense(License license, String organizationId){
        String responseMessage = null;
        if (license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage("license.update.message", null, null), license.toString());
        }
        return responseMessage;
    }
    public String deleteLicense(String organizationId, String licenseId){
        String responseMessage = null;
        responseMessage = String.format("This is the delete and the object is: %s", licenseId);
        return responseMessage;
    }
}
