package com.example.jamesprijatna.pokeimg;

/**
 * Created by jamesprijatna on 19/9/16.
 */
public class Pokemon {


    private int img;
    private int ID;
    private String name;
    private String element;
    private String HP;
    private String AP;
    private String DP;
    private String total;
    private int bg;

    public Pokemon(int ID, int img, String name, String element){
        this.ID=ID;
        this.img=img;
        this.name=name;
        this.element=element;
    }

    public Pokemon(int ID, int img, String name, String element, String total, int bg){
        this.ID=ID;
        this.img=img;
        this.name=name;
        this.element=element;
        this.total=total;
        this.bg=bg;
    }

    public Pokemon(int ID, int img, String name, String element, String HP, String AP, String DP){
        this.ID=ID;
        this.img=img;
        this.name=name;
        this.element=element;
        this.HP=HP;
        this.AP=AP;
        this.DP=DP;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getHP() {
        return HP;
    }

    public void setHP(String HP) {
        this.HP = HP;
    }

    public String getAP() {
        return AP;
    }

    public void setAP(String AP) {
        this.AP = AP;
    }

    public String getDP() {
        return DP;
    }

    public void setDP(String DP) {
        this.DP = DP;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getTotal() { return total; }

    public void setTotal(String total){ this.total = total; }


}
