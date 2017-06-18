package org.josanfdez.Model;

import java.io.Serializable;
import java.util.Comparator;

public class Champion implements Serializable {

    private static final long serialVersionUID = 857957148515373922L;

    private String name; // Nombre del campeón.
    private String race; // Raza del campeón
    private String zone; // Zona donde vive el campeón.
    private String role; // Rol del campeón.

    //Constructores.

    //Constructor por defecto.
    public Champion() {
    }

    //Constructor con todos los datos.
    public Champion(String name, String race, String zone, String role) {
        this.name = name;
        this.race = race;
        this.zone = zone;
        this.role = role;
    }
    //Accesores


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals("")){
            this.name = "Incorrecto.";
            System.out.println("El nombre introducido es incorrecto.");
        }else{
            this.name = name;
        }
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        if (race.equals("")){
            this.race = "Incorrecta.";
            System.out.println("La raza introducida es incorrecta.");
        }else{
            this.race = race;
        }
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        if (zone.equals("")){
            this.zone = "Incorrecta.";
            System.out.println("La zona introducida es incorrecta.");
        }else{
            this.zone = zone;
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role.equals("")){
            this.role = "Incorrecto.";
            System.out.println("El rol introducido es incorrecto.");
        }else{
            this.role = role;
        }
    }

    /**
     * Método para imprimir campeón.
     * @return
     */
    @Override
    public String toString() {
        return "Champion{" +
                "name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", zone='" + zone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
    public static Comparator<Champion> orderChamp = new Comparator<Champion>() {
        @Override
        public int compare(Champion campeon1, Champion campeon2) {
            int res = campeon1.getName().compareToIgnoreCase(campeon2.getName());
            return res;
        }
    };
}