package com.codeup.adlister.controllers;
import org.mindrot.jbcrypt.BCrypt;
public class HashTest {
    public static void main(String[] args){
        String password = "password123";
        String hash = BCrypt.hashpw(password, BCryptCrypt.gensalt());

        boolean passwardsMatch = BCrypt.checkpw(password,hash);
        system


    }
}
