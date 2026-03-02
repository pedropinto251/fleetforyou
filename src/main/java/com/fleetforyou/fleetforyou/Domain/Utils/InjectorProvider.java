package com.fleetforyou.fleetforyou.Domain.Utils;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.ClientService;
import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.FormService.FormService;
import com.fleetforyou.fleetforyou.Application.Services.FormService.IFormCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.RentalService.RentalService;
import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.StandService.StandService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserLoginService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.UserService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.VehicleService;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.*;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.*;

public class InjectorProvider {
    // StandService
    private static IStandCRUDService standCRUDService;

    // UserService
    private static IUserLoginService userLoginService;
    private static IUserCRUDService userCRUDService;

    // VehicleService
    private static IVehicleCRUDService vehicleCRUDService;

    // ClientService
    private static IClientCRUDService clientCRUDService;

    // RentalService
    private static IRentalCRUDService rentalCRUDService;

    // FormService
    private static IFormCRUDService formCRUDService;

    public static void setUp() {
        // StandDAO
        IStandDAO standDAO = new StandDAO();
        // StandService
        standCRUDService = new StandService(standDAO);

        // UserDAO
        IUserDAO userDAO = new UserDAO();
        // UserService
        userLoginService = new UserService(userDAO);
        userCRUDService = new UserService(userDAO);

        // VehicleDAO
        IVehicleDAO vehicleDAO = new VehicleDAO();
        // VehicleService
        vehicleCRUDService = new VehicleService(vehicleDAO);

        // ClientDAO
        IClientDAO clientDAO = new ClientDAO();
        // ClientService
        clientCRUDService = new ClientService(clientDAO);

        // RentalDAO
        IRentalDAO rentalDAO = new RentalDAO();
        // RentalService
        rentalCRUDService = new RentalService(rentalDAO);

        // FormDAO
        IFormDAO formDAO = new FormDAO();
        // FormService
        formCRUDService = new FormService(formDAO);
    }

    public static IStandCRUDService getStandCRUDService() {
        return standCRUDService;
    }

    public static IUserLoginService getUserLoginService() {
        return userLoginService;
    }

    public static IUserCRUDService getUserCRUDService() {
        return userCRUDService;
    }

    public static IVehicleCRUDService getVehicleCRUDService() {
        return vehicleCRUDService;
    }

    public static IClientCRUDService getClientCRUDService() {
        return clientCRUDService;
    }

    public static IRentalCRUDService getRentalCRUDService(){
        return rentalCRUDService;
    }

    public static IFormCRUDService getFormCRUDService(){
        return formCRUDService;
    }
}