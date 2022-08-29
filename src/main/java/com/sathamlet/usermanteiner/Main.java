package com.sathamlet.usermanteiner;

import com.sathamlet.usermanteiner.model.User;
import com.sathamlet.usermanteiner.operations.ICrud;
import com.sathamlet.usermanteiner.repository.UserRepository;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int option = 0;

        Map<String, Integer> operaciones = new HashMap<>();
        operaciones.put("Crear", 1);
        operaciones.put("Actualizar", 2);
        operaciones.put("Listar", 3);
        operaciones.put("Borrar",4);
        operaciones.put("Salir", 5);
        Object[] opArray = operaciones.keySet().toArray();
        ICrud<User> repoMan = new UserRepository();

        do{
            Object opcionS = JOptionPane.showInputDialog(null
                    , "Seleccione una operacion", "Mantenedor de Usuarios"
                    , JOptionPane.INFORMATION_MESSAGE,null,opArray,opArray[0]);
            option = operaciones.get(opcionS.toString());
            if(opcionS == null){
                JOptionPane.showMessageDialog(null, "Debes seleccionar una operacion");
            } else {
                switch (option) {
                    case 1, 2 -> {
                        User u = createUser(option);
                        if(u.getId() != null && repoMan.save(u)) JOptionPane.showMessageDialog(null,
                                "El Usuario fue actualizado satisfactoriamente");
                        else if (repoMan.save(u)) JOptionPane.showMessageDialog(null,
                                "El Usuario se agrego satisfactoriamente");

                        else JOptionPane.showMessageDialog(null,
                                "No se pudo agregar revisa los logs");
                    }
                    case 3 ->
                            JOptionPane.showMessageDialog(null, repoMan.listing());
                    case 4 -> {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID del usuario que quiere borrar"));
                        if (repoMan.delete(id))
                            JOptionPane.showMessageDialog(null,
                                    "EL usuario fue borrado satisfactoriamente");
                        else JOptionPane.showMessageDialog(null,
                                "El usuario que desas eliminar no existe");
                    }
                    case 5 -> option = 5;
                }
            }

        } while (option != 5);
    }
    private static User createUser(int pOpcion){
        User u = new User();
        u.setUserName(JOptionPane.showInputDialog("Ingresa el nombre de usuario"));
        u.setPassword(JOptionPane.showInputDialog("Ingresa la contraseña"));
        u.setEmail(JOptionPane.showInputDialog("Ingresa el correo electronico"));
        if(pOpcion == 2){
            u.setId(Integer.valueOf(JOptionPane.showInputDialog("Ingresa el ID que quieres modificar")));
        }
        return u;
    }
}
