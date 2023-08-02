package com.sohosqared.omdb.kernel.client;


import java.util.EnumMap;

/**
 * Interface defining the auth to a client.
 * We don't use some framework, so we depend as few as possible on a concrete framework implmementation
 */
public interface ClientAuth {

    EnumMap<Datatype, Object> authData();

    enum Datatype {
        USER, PASSWORD, KEY, TOKEN
    }
}
