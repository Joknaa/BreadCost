/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

public class UserModel {
    String name;
    String pass;
    private String room;

    public UserModel(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public UserModel(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    
    public void setRoom(String room) {
        this.room = room;
    }
    
    
}
