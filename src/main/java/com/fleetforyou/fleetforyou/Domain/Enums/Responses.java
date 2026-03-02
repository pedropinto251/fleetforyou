package com.fleetforyou.fleetforyou.Domain.Enums;

public interface Responses {
    enum Login implements Responses {
        LOGIN_SUCCESS,
        LOGIN_FAILED,
    }
    interface User extends Responses {
        enum Email implements Responses {
            EMAIL_EXISTS,
            EMAIL_NOT_EXISTS,
        }
        enum Find implements User {
            NOT_FOUND,
            FOUND,
        }

        enum Create implements User {
            CREATED_SUCCESS,
            CREATED_FAILED,
        }

        enum Update implements User {
            UPDATED_SUCCESS,
            UPDATE_FAILED,
        }

        enum Delete implements User {
            DELETED_SUCCESS,
            DELETED_FAILED,
        }
    }

    interface Stand extends Responses {
        enum Exists implements Stand {
            EXISTS,
            NOT_EXISTS
        }
        enum Find implements Stand {
            NOT_FOUND,
            FOUND,
        }

        enum Create implements Stand {
            CREATED_SUCCESS,
            CREATED_FAILED,
        }

        enum Update implements Stand {
            UPDATED_SUCCESS,
            UPDATE_FAILED,
        }

        enum Delete implements Stand {
            DELETED_SUCCESS,
            DELETED_FAILED,
        }
    }

    interface Vehicle extends Responses {
        enum Registration implements Responses {
            REGISTRATION_EXISTS,
            REGISTRATION_NOT_EXISTS,
        }
        enum Find implements Vehicle {
            NOT_FOUND,
            FOUND,
        }

        enum Create implements Vehicle {
            CREATED_SUCCESS,
            CREATED_FAILED,
        }

        enum Update implements Vehicle {
            UPDATED_SUCCESS,
            UPDATE_FAILED,
        }

        enum Delete implements Vehicle {
            DELETED_SUCCESS,
            DELETED_FAILED,
        }
    }

    interface Client extends Responses {
        enum Nif implements Responses{
            NIF_EXISTS,
            NIF_NOT_EXISTS;
        }
        enum Find implements Client {
            NOT_FOUND,
            FOUND,
        }

        enum Create implements Client {
            CREATED_SUCCESS,
            CREATED_FAILED,
        }

        enum Update implements Client {
            UPDATED_SUCCESS,
            UPDATE_FAILED,
        }

        enum Delete implements Client {
            DELETED_SUCCESS,
            DELETED_FAILED,
        }
    }
    interface Rental extends Responses {
        enum Find implements Rental {
            NOT_FOUND,
            FOUND,
        }

        enum Create implements Rental {
            CREATED_SUCCESS,
            CREATED_FAILED,
        }

        enum Update implements Rental {
            UPDATED_SUCCESS,
            UPDATE_FAILED,
        }

        enum Delete implements Rental {
            DELETED_SUCCESS,
            DELETED_FAILED,
        }
    }

    interface Form extends Responses {
        enum Find implements Form {
            NOT_FOUND,
            FOUND,
        }

        enum Create implements Form {
            CREATED_SUCCESS,
            CREATED_FAILED,
        }
    }
}
