package com.example.pokimone.model;

import java.util.ArrayList;

public class PokimoneWholeRes {

    private int count ;
    private String next , previous;
    private ArrayList<PokimaneCharacter> results;

    public PokimoneWholeRes(int count, String next, String previous, ArrayList<PokimaneCharacter> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<PokimaneCharacter> getResults() {
        return results;
    }

    public void setResults(ArrayList<PokimaneCharacter> results) {
        this.results = results;
    }
}
