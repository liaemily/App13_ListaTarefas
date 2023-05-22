package br.edu.ifsp.dmos5.app13_listatarefas.model.dao;

import android.content.Context;

import java.util.List;

import br.edu.ifsp.dmos5.app13_listatarefas.model.entities.Task;

public interface ITaskDao {
    void create(Task task);

    boolean update(String oldTitle, Task task);

    boolean delete(Task task);

    Task findByTitle(String title);

    void insertStart(Task task);

    void insertEnd(Task task);

    List<Task> findAll();
}
