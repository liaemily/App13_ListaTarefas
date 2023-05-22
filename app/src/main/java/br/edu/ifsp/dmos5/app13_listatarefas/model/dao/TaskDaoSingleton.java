package br.edu.ifsp.dmos5.app13_listatarefas.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.app13_listatarefas.model.entities.Task;

public class TaskDaoSingleton implements ITaskDao{

    private static TaskDaoSingleton instance = null;
    private List<Task> dataset;

    private TaskDaoSingleton() {
        dataset = new ArrayList<>();
    }

    public static TaskDaoSingleton getInstance(){
        if(instance == null)
            instance = new TaskDaoSingleton();
        return instance;
    }

    @Override
    public void create(Task task) {
        if(task != null){
            dataset.add(task);
        }
    }

    @Override
    public boolean update(String oldTitle, Task task) {
        Task inDataset;
        inDataset = dataset.stream()
                .filter(task1 -> task1.getTitle().equals(oldTitle))
                .findAny()
                .orElse(null);
        if(inDataset != null){
            inDataset.setTitle(task.getTitle());
            inDataset.setDescription(task.getDescription());
            inDataset.setFavorite(task.isFavorite());
            //inDataset.getTags().clear();
            //inDataset.getTags().addAll(task.getTags());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Task task) {
        return dataset.remove(task);
    }

    public void insertStart(Task task){
        dataset.remove(task);
        dataset.add(0, task);
    }

    public void insertEnd(Task task){
        dataset.remove(task);
        dataset.add(task);
    }

    @Override
    public Task findByTitle(String title) {
        return dataset.stream()
                .filter(task -> task.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }


    @Override
    public List<Task> findAll() {
        return dataset;
    }
}
