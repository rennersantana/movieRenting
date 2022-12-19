/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

//interface Data Access Object to be implemented the idea is to isolate the application/business layer from the persistence layer
import java.util.List;

/**
 *
 * @author rennersantana
 */
public interface IDAO<T> {
    
  void save(T t);

        void update(T T);

        void delete(int id);

        T findById (int id);

        List<T> findAll();
}

