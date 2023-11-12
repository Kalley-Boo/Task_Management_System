package Models;

import Models.Contracts.Board;

public class BoardImpl implements Board {

    private String name;

    public BoardImpl(String name){
        setName(name);
    }

    private void setName(String name) {
        this.name = name;
    }
}
