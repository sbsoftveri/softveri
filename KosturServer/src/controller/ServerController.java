/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Administrator;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Stefan
 */
public class ServerController {

    private static ServerController instance;
    private List<Administrator> admini = new ArrayList<>();
    private List<Administrator> trenutnoUlogovani = new ArrayList<>();

    private ServerController() {

        Administrator a1 = new Administrator("Stefan", "Stefan1");
        Administrator a2 = new Administrator("Marko", "Marko1");
        Administrator a3 = new Administrator("Petar", "Petar1");
        Administrator a4 = new Administrator("Milos", "Milos1");

        admini.add(a1);
        admini.add(a2);
        admini.add(a3);
        admini.add(a4);

    }

    public List<Administrator> getTrenutnoUlogovani() {
        return trenutnoUlogovani;
    }

    public void setTrenutnoUlogovani(List<Administrator> trenutnoUlogovani) {
        this.trenutnoUlogovani = trenutnoUlogovani;
    }

    public static ServerController getInstance() {

        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {

        for (Administrator a : admini) {

            if (a.equals(administrator)) {
                if (trenutnoUlogovani.contains(a)) {
                    throw new Exception("Administrator je vec ulogovan!!!");
                } else {
                    trenutnoUlogovani.add(administrator);
                }
                return a;
            }
        }
        throw new Exception("Niste uneli dobro korisnicko ime ili lozinku!!!!");
    }

    public void signOutAdministrator(Administrator administrator) {

        trenutnoUlogovani.remove(administrator);
    }

  

}
