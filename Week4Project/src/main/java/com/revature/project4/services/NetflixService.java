package com.revature.project4.services;

import com.revature.project4.daos.NetflixAccDaoPostgres;
import com.revature.project4.dtos.ResourceCreationResponse;
import com.revature.project4.models.NetflixAcc;
import com.revature.project4.util.InvalidRequestException;

public class NetflixService {
    private final NetflixAccDaoPostgres cardDAO;

    public NetflixService(NetflixAccDaoPostgres cardDAO) {
        this.cardDAO = cardDAO;
    }

    public ResourceCreationResponse createAcc(NetflixAcc newAcc) {

        // Validate the data provided from the web layer
        if (newAcc == null ||
                newAcc.getUsername() == null || newAcc.getUsername().equals("") ||
                newAcc.getPassword() == null || newAcc.getPassword().equals(""))

        {
            String msg = "Provided account data was invalid. Username and password text must not be null or empty!";
            // Logger.log(msg, LogLevel.ERROR);
            throw new InvalidRequestException(msg);
        }

        // If valid, persist to DB and return its result
        return new ResourceCreationResponse(cardDAO.save(newAcc).getId());

    }
}
