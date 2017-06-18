package org.josanfdez.Controller;

import org.josanfdez.Model.Champion;

import java.io.*;
import java.util.*;

public class App{
    private ArrayList<Champion> championList= new ArrayList<Champion>();

    public App() {
        loadChamps();
    }

    // Aquí podemos controlar las opciones que puede elegir el usuario.

    public void run() {
        int option;

        while ((option = showMenu()) != 0) {
            switch (option) {
                case 1:
                    championList.add(addChamp());
                    break;
                case 2:
                    if (championList.size() > 0){
                        showChamp();
                    }
                    break;
                case 3:
                    if (championList.size() > 0){
                        orderChamp();
                    }
                    break;
                case 4:
                    if (championList.size() > 0){
                        modifyChamp();
                    }
                    break;
                case 5:
                    if (championList.size() > 0){
                        removeChamp();
                    }
                    break;
                default:
                    break;
            }
            saveChamps();
        }
    }

    public void saveChamps() {
        try {
            ObjectOutputStream saveChamps = new ObjectOutputStream(new FileOutputStream("fichero/champions.dat"));
            saveChamps.writeObject(championList);
            saveChamps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadChamps(){
        try {
            ObjectInputStream readChamps = new ObjectInputStream(new FileInputStream("fichero/champions.dat"));
            championList = (ArrayList<Champion>) readChamps.readObject();
            readChamps.close();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // Aquí podemos crear un campeón e indicar su nombre, raza, zona y rol.

    public Champion addChamp(){
        Scanner input = new Scanner(System.in);
        String name;
        String race;
        String zone;
        String role = "";
        boolean creado = false;
        int rol = 0;
        int position = 0;
        boolean datosCorrectos = false;

        Champion campeon;

        do {
            if (creado == true){
                System.out.println("Error el nombre ya está en uso, escriba otro.");
            }
            creado = false;
            System.out.println("Añada nombre del campeón: ");
            name = input.nextLine();
            if (name.matches("([A-Za-z]+|[A-Za-z]+ [A-Za-z]+)")) {
                datosCorrectos = true;
            }else {
                System.out.println("Introduzca el nombre correctamente.");
            }

            for (Champion champion: championList) {
                if (champion.getName().equals(name)){
                    creado = true;
                }
            }
        }while (name.equals("") || creado == true || datosCorrectos == false);
        datosCorrectos = false;

        do {
            System.out.println("Añada raza del campeón: ");
            race = input.nextLine();
            if (race.matches("([A-Za-z]+|[A-Za-z]+ [A-Za-z]+)")) {
                datosCorrectos = true;
            }else {
                System.out.println("Introduzca la raza correctamente.");
            }
        }while (race.equals("") || datosCorrectos == false);

        do {
            System.out.println("Añada zona del campeón: ");
            zone = input.nextLine();
            if (zone.matches("([A-Za-z]+|[A-Za-z]+ [A-Za-z]+)")) {
                datosCorrectos = true;
            }else {
                System.out.println("Introduzca la zona correctamente.");
            }
        }while (zone.equals("") || datosCorrectos == false);
        input.nextLine();

        do {
            try {
                System.out.println("Indique el rol del campeón: ");
                System.out.println("Tanque(1), Asesino(2), Mago(3), Support(4), Jungla(5)");
                rol = input.nextInt();

                if (rol == 1) {
                    role = "Tanque";
                } else if (rol  == 2) {
                    role = "Asesino";
                } else if (rol  == 3) {
                    role = "Mago";
                } else if (rol  == 4) {
                    role = "Support";
                } else if (rol  == 5) {
                    role = "Jungla";
                }
            }catch (InputMismatchException e) {
                System.out.println("No es correcto, introduzca un valor válido.");
                input.nextLine();
            }
        }while (rol < 1 || rol > 5);
        //role = String.valueOf(rol);

        input.nextLine();

        campeon = new Champion(name, race, zone, role);
        return campeon;
    }

    public void showChamp( ){
        for (Champion list: championList) {
            System.out.println(championList.indexOf(list) + " - " + list);
        }
    }

    public void orderChamp(){
        Collections.sort(championList, Champion.orderChamp);
        for (Champion temp: championList) {
            System.out.println(temp);
        }
    }

    public Champion modifyChamp(){
        Champion deleteChamp;
        int name = 0;
        int index = 0;
        int indice = 0;
        boolean numero;

        Scanner input = new Scanner(System.in);

        for (Champion champion: championList) {
            System.out.println(championList.indexOf(champion) + " - " + champion);
        }

        do {
            do {
                numero = true;

                System.out.println();
                System.out.printf("Introduzca el número del campeón a editar: ");

                try{
                    name = input.nextInt();
                }catch(InputMismatchException e){

                    numero = false;
                    System.out.println("Introduzca un número: ");
                }
            }while(numero == false);

            Iterator<Champion> thisChamp = championList.iterator();

            while (thisChamp.hasNext()) {
                Champion champion = thisChamp.next();

                if (indice == name ){
                    deleteChamp = champion;
//                    thisChamp.remove();
//                    addChamp();
                    championList.set(indice, addChamp());

                    index++;
                    return deleteChamp;
                }
                indice++;
            }

            if (index == 0) {
                System.out.println("Introduzca bien el nombre del campeón.");
            }
        }while (index==0);
        return championList.get(0);
    }

    public void removeChamp(){
        int index;
        Scanner input = new Scanner(System.in);

        for ( Champion champion: championList ) {
            System.out.println(championList.indexOf(champion) + " - " + champion);
        }

        System.out.println("\nSelecciona el campeón a borrar: ");
        index = input.nextInt();

        if (index < championList.size()){
            championList.remove(index);
        }
    }
    // Aquí mostramos el menú y preguntamos al usuario que opción quiere elegir.

    public int showMenu() {
        Scanner input = new Scanner(System.in);
        int option = 0;

        try {
            System.out.println("********************************");
            System.out.println("*           LoL App            *");
            System.out.println("********************************");
            System.out.println("* 1 - Añadir Campeón           *");
            if (championList.size() > 0) {
                System.out.println("* 2 - Mostrar Campeónes        *");
                System.out.println("* 3 - Ordenar Campeones        *");
                System.out.println("* 4 - Modificar Campeón        *");
                System.out.println("* 5 - Borrar Campeón           *");
            }
            System.out.println("* 0 - Salir                    *");
            System.out.println("********************************");
            System.out.println("Opción: ");

            option = input.nextInt();

        }catch (InputMismatchException e){
            System.out.println("Opción incorrecta.");
            run();
        }
        return option;
    }
}